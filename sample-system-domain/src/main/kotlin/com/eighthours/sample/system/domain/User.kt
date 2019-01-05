package com.eighthours.sample.system.domain

import com.eighthours.sample.domain.LongId
import com.eighthours.sample.domain.StringId
import org.seasar.doma.*

@Entity(immutable = true)
@Table(name = "APP_USER")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val sk: LongId<User>? = null,

    val id: StringId<User>,

    val name: String,

    val status: UserStatus,

    val metaInfo: MetaInfo,

    @Version
    val version: Int? = null
) {
    @Transient
    var roleIds: List<StringId<Role>>? = null
}


enum class UserStatus {
    ACTIVE,
}
