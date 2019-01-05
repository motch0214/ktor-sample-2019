package com.eighthours.sample.system.domain

import com.eighthours.sample.domain.StringId

interface UserRepository {

    fun find(id: StringId<User>): User?

    fun create(id: StringId<User>, name: String, roleIds: List<StringId<Role>>): User

    fun update(user: User, roleIds: List<StringId<Role>>? = null): User
}
