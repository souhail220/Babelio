// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    id("org.jetbrains.kotlin.android") version "2.2.10" apply false
    alias(libs.plugins.compose.compiler)
    id("com.google.gms.google-services") version "4.4.4" apply false
}
