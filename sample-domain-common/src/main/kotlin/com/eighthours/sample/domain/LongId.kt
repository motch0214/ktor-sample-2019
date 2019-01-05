package com.eighthours.sample.domain

import org.seasar.doma.Domain

@Domain(valueType = Long::class)
data class LongId<E>(val value: Long) {

    override fun toString(): String = "$value"
}
