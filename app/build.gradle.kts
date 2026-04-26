plugins {
    alias(libs.plugins.android.application)
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.mim.babelio"

    compileSdk = 36

    buildFeatures {
        compose = true
    }

    defaultConfig {
        applicationId = "com.mim.babelio"
        minSdk = 24
        targetSdk = 36
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
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)


    // Compose BOM (manages all Compose versions)
    implementation(platform("androidx.compose:compose-bom:2026.04.01"))

    // Compose UI libraries
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Activity + Compose bridge (setContent)
    implementation("androidx.activity:activity-compose:1.8.2")

    // Debug tools
    debugImplementation("androidx.compose.ui:ui-tooling")

    // View Model
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation("androidx.compose.runtime:runtime-livedata")

    // BOM manages all compose versions — no version needed on individual compose libs
    implementation(platform(libs.compose.bom))

    implementation(libs.compose.ui)

    implementation(libs.compose.runtime.livedata)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.viewmodel.compose)
}