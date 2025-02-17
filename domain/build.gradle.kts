plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinCompose)
    id("kotlin-kapt")
    id ("org.jetbrains.kotlin.plugin.serialization")

}

android {
    namespace = "com.twitter.domain"
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


    //region Data Dependencies
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.runtime)
    implementation (libs.paging.runtime.ktx)
    implementation(libs.kotlinx.serialization.json)

    //endregion


}