package com.eighthours.sample.data.system

import com.eighthours.sample.domain.LongId
import com.eighthours.sample.domain.StringId
import com.eighthours.sample.system.domain.*
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import java.time.LocalDateTime

class UserRepositoryImpl(kodein: Kodein) : UserRepository {

    private val dao: UserDao by kodein.instance()

    override fun find(id: StringId<User>): User? {
        val user = dao.selectById(id) ?: return null
        user.roleIds = dao.selectRoleRelations(user.sk!!)
        return user
    }

    override fun create(id: StringId<User>, name: String, roleIds: List<StringId<Role>>): User {
        val (user, _) = dao.insert(
            User(
                id = id,
                name = name,
                status = UserStatus.ACTIVE,
                metaInfo = MetaInfo(
                    createdUserSk = LongId(0),
                    createdDatetime = LocalDateTime.now(),
                    updatedUserSk = LongId(0),
                    updatedDatetime = LocalDateTime.now()
                )
            )
        )

        dao.insertRoleRelations(roleIds.map {
            UserDao.UserRoleRelation(user.sk!!, it)
        })

        return user.also { it.roleIds = roleIds }
    }

    override fun update(user: User, roleIds: List<StringId<Role>>?): User {
        val (updated, _) = dao.update(
            user.copy(
                metaInfo = user.metaInfo.copy(
                    updatedUserSk = LongId(0),
                    updatedDatetime = LocalDateTime.now()
                )
            )
        )

        if (roleIds == null) {
            return updated.also { it.roleIds = user.roleIds }
        }

        dao.deleteRoleRelations(user.sk!!)
        dao.insertRoleRelations(roleIds.map { UserDao.UserRoleRelation(user.sk!!, it) })

        return updated.also { it.roleIds = roleIds }
    }
}
