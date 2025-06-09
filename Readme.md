#### This code is from tutorial
https://www.kodeco.com/25768145-realm-database-on-android-getting-started

### How to configure Gradle to add Realm database
1. you need to add kapt plugin to project

2. add such code in your project build.gradle.kts file:
```
buildscript {
    dependencies {
        classpath("io.realm:realm-gradle-plugin:10.6.0")
    }
}
```

3. add this line into your app-module build.gradle.kts file:
```
apply(plugin = "realm-android")
```

4. And you need to prefer project repositories over settings repositories. 
So, you need to delete this lines from settings.gradle:
```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
```
---------------------------------
