#### This code is from tutorial
https://www.kodeco.com/25768145-realm-database-on-android-getting-started

### How to configure Gradle to add Realm database
1. you need to add kapt plugin to project

2. add such code in your project build.gradle.kts file. 
Version of Realm must be more than 10.5.1, because only
newer versions of Realm can work with Gradle 8.
```
buildscript {
    dependencies {
        classpath("io.realm:realm-gradle-plugin:10.17.1")
    }
}
```

3. add this line into your app-module build.gradle.kts file:
```
apply(plugin = "realm-android")
```

