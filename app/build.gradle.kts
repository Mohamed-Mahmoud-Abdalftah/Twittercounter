plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinCompose)
    id(libs.plugins.daggerHilt.get().toString())
    id("kotlin-kapt")
}

android {
    namespace = "com.app.twittercounter"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.app.twittercounter"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerVersion.get()
    }

}


dependencies {

    implementation(project(":navigation"))
    implementation(project(":core"))
    implementation(project(":twitter"))
    implementation(project(":data"))
    implementation(project(":domain"))
    // just home Compose Screen



    //region D.I Dependencies
    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
    //endregion

    //region Compose Dependencies
    implementation(libs.compose.activity)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.compose.ui.material)
    androidTestImplementation(platform(libs.compose.bom))
    //endregion

    //region Core Dependencies
    implementation(libs.appcompat)
    implementation(libs.android.core)
    //endregion

    implementation(libs.lifecycle.ktx)


    implementation(libs.runtime)
}