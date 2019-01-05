package com.eighthours.sample.system.domain

import com.eighthours.sample.domain.StringId
import org.seasar.doma.Entity
import org.seasar.doma.Id
import org.seasar.doma.Transient

@Entity(immutable = true)
data class Role(
    @Id
    val id: StringId<Role>,

    val description: String
) {
    @Transient
    var authorityIds: List<StringId<Authority>>? = null
}


object Authority
