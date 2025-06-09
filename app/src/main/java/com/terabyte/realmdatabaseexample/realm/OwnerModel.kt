package com.terabyte.realmdatabaseexample.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class OwnerModel(): RealmObject() {
    @PrimaryKey
    var id = 0

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