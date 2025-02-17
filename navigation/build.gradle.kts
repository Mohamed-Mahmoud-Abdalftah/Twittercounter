plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinCompose)
    id("kotlin-kapt")
    id ("org.jetbrains.kotlin.plugin.serialization")

}

android {
    namespace = "com.twitter.navigation"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":core"))
    //region D.I
    implementation(libs.hilt.core)
    implementation(project(":domain"))
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    //endregion
    implementation("androidx.compose.runtime:runtime:1.7.6")
    implementation(libs.compose.navigation)
    implementation(libs.kotlinx.serialization.json)


}