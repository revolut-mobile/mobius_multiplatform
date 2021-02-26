plugins {
    id("com.android.application")
    kotlin("android")
}

val coroutinesVersion: String by project

dependencies {
    implementation(project(":common"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("com.revolut.recyclerkit:delegates:1.0.10")
    implementation("com.revolut.recyclerkit:animators:1.0.11")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.example.mobiusapp"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        viewBinding = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}