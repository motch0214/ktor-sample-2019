package com.eighthours.sample.data.system

import com.eighthours.sample.domain.StringId
import com.eighthours.sample.system.domain.User
import org.seasar.doma.*
import org.seasar.doma.jdbc.Result

@Dao
interface UserDao {

    @Script
    fun create()

    @Select
    fun selectById(id: StringId<User>): User?

    @Insert
    fun insert(user: User): Result<User>

    @Update
    fun update(user: User): Result<User>

    @Delete
    fun delete(user: User): Result<User>
}
