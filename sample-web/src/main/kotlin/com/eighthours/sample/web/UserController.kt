package com.eighthours.sample.web

import com.eighthours.sample.domain.StringId
import com.eighthours.sample.service.TransactionManager
import com.eighthours.sample.system.domain.Role
import com.eighthours.sample.system.domain.User
import com.eighthours.sample.system.domain.UserRepository
import com.eighthours.sample.system.domain.UserStatus
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class UserController(kodein: Kodein) {

    private val repository: UserRepository by kodein.instance()

    private val transaction: TransactionManager by kodein.instance()

    data class Create(val id: StringId<User>, val name: String, val roles: List<StringId<Role>>)

    fun create(request: Create): MessageResponse = transaction.apply {
        val user = repository.create(request.id, request.name, request.roles)
        MessageResponse("The user has been created successfully: ${user.id}")
    }

    data class Find(val id: StringId<User>)

    data class FindResponse(
        val id: StringId<User>, val name: String, val status: UserStatus, val roles: List<StringId<Role>>
    )

    fun find(request: Find): FindResponse = transaction.apply {
        val user = repository.find(request.id) ?: throw BusinessException("The user not exists: ${request.id}")
        FindResponse(user.id, user.name, user.status, user.roleIds ?: emptyList())
    }
}
