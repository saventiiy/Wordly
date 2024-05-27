plugins {
    id("convention.android-application")
    id("convention.compose")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = Config.packageName

    defaultConfig {
        applicationId = Config.packageName
        versionCode = Config.versionCode
        versionName = Config.versionName
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
        }
        release {
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
//    https://patilshreyas.github.io/compose-report-to-html/use/using-gradle-plugin/#__tabbed_1_2
    apply(plugin = "dev.shreyaspatil.compose-compiler-report-generator")


    val composeBom = platform("androidx.compose:compose-bom:2024.03.00")
    implementation(composeBom)

    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")

    implementation(Dependencies.Android.material)
    implementation(Dependencies.Android.material)
    implementation(Dependencies.Android.constraint)
    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.lifecycleViewmodelKtx)
    implementation(Dependencies.Android.activityCompose)
    implementation(Dependencies.Android.fragmentKtx)

    implementation(Dependencies.Navigation.navigationCompose)

    implementation(Dependencies.Lifecycle.viewModelCompose)
    implementation(Dependencies.Lifecycle.runtimeCompose)

    implementation(Dependencies.Lottie.core)

    implementation(Dependencies.Hilt.hilt)
    kapt(Dependencies.Hilt.hiltCompiler)
    kapt(Dependencies.Hilt.hiltLifecycleCompiler)
    implementation(Dependencies.Hilt.hiltNavigation)

    implementation(Dependencies.OkHttp3.loggingInterceptor)

    api(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.gsonConverter)

    implementation(Dependencies.Room.roomRuntime)
    implementation(Dependencies.Room.roomKtx)
    kapt(Dependencies.Room.roomCompiler)

    implementation(Dependencies.Coil.core)

    implementation(Dependencies.Glance.glance)
    implementation(Dependencies.Glance.glanceWidget)

    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("androidx.hilt:hilt-work:1.2.0")
}