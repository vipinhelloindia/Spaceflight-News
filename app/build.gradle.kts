plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.serializationkotlin)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
}

android {
    namespace = AndroidConfig.app_namespace
    compileSdk = AndroidConfig.compileSdkVersion

    defaultConfig {
        applicationId = AndroidConfig.applicationId
        minSdk = AndroidConfig.minSdkVersion
        targetSdk = AndroidConfig.targetSdkVersion
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    flavorDimensions += FlavorsConfig.flavorDimensions
    productFlavors {
        create(FlavorsConfig.devFlavor) {
            dimension = FlavorsConfig.defaultDimension
            applicationIdSuffix = FlavorsConfig.applicationIdSuffix
            versionNameSuffix = FlavorsConfig.versionNameSuffix
        }
        create(FlavorsConfig.prodFlavor) {
            dimension = FlavorsConfig.defaultDimension
        }
    }
    compileOptions {
        sourceCompatibility = CompilerConfig.sourceCompatibility
        targetCompatibility = CompilerConfig.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = CompilerConfig.jvmTarget
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":design"))
    implementation(project(":features:listing:presentation"))
    implementation(project(":features:details:presentation"))

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)

    implementation(libs.androidx.material3)

    implementation(libs.hilt.android.core)
    implementation(libs.navigation)
    kapt(libs.hilt.compiler)

    implementation(libs.kotlinx.serialization.json)

    debugImplementation(libs.androidx.ui.tooling)

}

