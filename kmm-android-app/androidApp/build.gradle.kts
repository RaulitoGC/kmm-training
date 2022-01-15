plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.rguzmanc.friends.android"
        minSdk = 26
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))

    implementation(AndroidX.core)
    implementation(AndroidX.viewModel)
    kapt(AndroidX.viewModelCompiler)
    implementation(AndroidX.material)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.constraintLayout)

    implementation(Ktor.android)
}