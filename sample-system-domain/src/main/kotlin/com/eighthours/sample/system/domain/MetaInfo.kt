package com.eighthours.sample.system.domain

import com.eighthours.sample.domain.LongId
import org.seasar.doma.Embeddable
import java.time.LocalDateTime

@Embeddable
data class MetaInfo(

    val createdUserSk: LongId<User>,

    val createdDatetime: LocalDateTime,

    val updatedUserSk: LongId<User>,

    val updatedDatetime: LocalDateTime
)
