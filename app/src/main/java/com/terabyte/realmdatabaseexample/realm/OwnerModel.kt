package com.terabyte.realmdatabaseexample.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.UUID

open class OwnerModel(): RealmObject() {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()

    var name: String? = null
    var type: Int? = null

    companion object {
        fun createDefault(): OwnerModel {
            val result = OwnerModel()
            result.name = "George"
            result.type = 1
            return result
        }
    }
}