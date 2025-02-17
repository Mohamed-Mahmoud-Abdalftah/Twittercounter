plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinCompose)
    id("kotlin-kapt")


}

android {
    namespace = "com.twitter.core"
    compileSdk = 35
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerVersion.get()
    }

}

dependencies {

    implementation(libs.retrofit.core)

    //region D.I Dependencies
    implementation(libs.hilt.core)
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    //endregion

    //region Presentation Dependencies
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.hilt.navigation)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.navigation)
    implementation(libs.compose.ui.material)
    implementation(libs.compose.activity)
    implementation(libs.coil)
    implementation("androidx.compose.runtime:runtime:1.7.6")
    //endregion
}