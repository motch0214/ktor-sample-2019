package com.eighthours.sample.domain

import com.google.gson.TypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import org.seasar.doma.Domain

@Domain(valueType = String::class)
@JsonAdapter(StringIdAdapter::class)
data class StringId<E>(val value: String) {

    override fun toString(): String = value
}


private class StringIdAdapter : TypeAdapter<StringId<*>>() {

    override fun write(writer: JsonWriter, value: StringId<*>?) {
        if (value == null) {
            writer.nullValue()
        } else {
            writer.value(value.value)
        }
    }

    override fun read(reader: JsonReader): StringId<*>? {
        return if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            null
        } else {
            val value = reader.nextString()
            StringId<Any>(value)
        }
    }
}
