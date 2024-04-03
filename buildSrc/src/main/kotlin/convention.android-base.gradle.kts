import com.android.build.gradle.BaseExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.kotlin

plugins {
    kotlin("android")
}

configure<BaseExtension> {
    compileSdkVersion(34)

    buildFeatures.viewBinding = true

    defaultConfig {
        minSdk = 29
        targetSdk = 34
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    lintOptions {
        isAbortOnError = false
        disable("UseCompoundDrawables")
    }
}
