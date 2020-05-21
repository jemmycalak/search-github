package com.jemmycalak.remote.utils

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.jemmycalak.remote.utils.Constants.API_DATA
import java.io.IOException

class DataTypeAdapterFactory : TypeAdapterFactory {

    override fun <T : Any?> create(gson: Gson?, type: TypeToken<T>?): TypeAdapter<T> {
        val lDelegate: TypeAdapter<T> = gson!!.getDelegateAdapter(this, type)
        val lElementAdapter: TypeAdapter<JsonElement> = gson.getAdapter(JsonElement::class.java)
        return object : TypeAdapter<T>() {

            @Throws(IOException::class)
            override fun write(out: JsonWriter?, value: T) {
                lDelegate.nullSafe().write(out, value)
            }

            override fun read(input: JsonReader?): T {
                var lElement = lElementAdapter.read(input)
                if (lElement.isJsonObject) {
                    val lObject = lElement.asJsonObject
                    if (lObject.has(API_DATA)) {
                        lElement = lObject.get(API_DATA)
                    }
                }
                return lDelegate.fromJsonTree(lElement)
            }
        }
    }
}