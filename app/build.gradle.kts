plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.burak.btcturkcase"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.burak.btcturkcase"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
            excludes += "META-INF/gradle/incremental.annotation.processors"
        }
    }
}

dependencies {
    implementation(libs.compose.runtime)
    implementation(libs.compose.ui)
    implementation(libs.activity.compose)
    implementation(libs.compose.material3)
    implementation(libs.compose.graphics)
    implementation(libs.compose.tooling)
    implementation(libs.compose.navigation)
    implementation(libs.compose.coil)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.tooling.preview)
    implementation(libs.compose.test.manifest)
    implementation(libs.compose.test.junit4)
    implementation(libs.compose.hilt.navigation)
    implementation(libs.accompanist.pager.layouts)
    implementation(libs.accompanist.pager.indicators)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.retrofit.gson)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.okhttp)
    implementation(libs.okhttp.loggingInterceptor)
    implementation(libs.core.ktx)
    implementation(libs.timber)
    implementation(libs.junit)
    implementation(libs.espresso.core)
    implementation(libs.hilt.android)
    implementation(libs.hilt.compiler)
    implementation(libs.vico.compose.m3)
    implementation(libs.mockk)
    implementation(libs.assertj.core)

}