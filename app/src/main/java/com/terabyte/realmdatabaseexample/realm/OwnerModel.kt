package com.terabyte.realmdatabaseexample.realm

import io.realm.RealmObject

open class OwnerModel(): RealmObject() {
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