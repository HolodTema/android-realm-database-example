package com.terabyte.realmdatabaseexample.realm

import io.realm.RealmObject

open class DogModel: RealmObject() {
    var id = 0;
    var name: String? = null;
    var age: Int? = null;
    var type: Int? = null;
}