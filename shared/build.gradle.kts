import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.devtoolsKsp)
    alias(libs.plugins.mockmp)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
            // Accessible by the iOS app
            export(project(":store"))
            export(project(":data:common"))
            export(project(":data:label"))
            export(project(":data:todo"))
            export(project(":feature:common"))
            export(project(":feature:addLabel"))
            export(project(":feature:labelList"))
            export(project(":feature:todoList"))
            export(project(":feature:addTodo"))
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            api(project(":store"))
            api(project(":data:common"))
            api(project(":data:label"))
            api(project(":data:todo"))
            api(project(":feature:common"))
            api(project(":feature:addLabel"))
            api(project(":feature:labelList"))
            api(project(":feature:todoList"))
            api(project(":feature:addTodo"))

            api(libs.koin.core)

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.sqlDelight.runtime)
        }
        androidMain.dependencies {
            api(libs.koin.android)
            implementation(libs.sqlDelight.android.driver)
        }
        iosMain.dependencies {
            implementation(libs.sqlDelight.native.driver)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}

android {
    namespace = "com.github.sikv.habitsplus.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

sqldelight {
    databases {
        create("ActivitiesDatabase") {
            srcDirs("src/commonMain/sqldelight/activities")
            packageName.set("com.github.sikv.habitsplus.database.activities")
        }
    }
}

mockmp {
    onTest {
        withHelper()
    }
}
