plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.serializationkotlin)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = AndroidConfig.feature_detail_namespace
    compileSdk = AndroidConfig.compileSdkVersion

    defaultConfig {
        minSdk = AndroidConfig.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += FlavorsConfig.flavorDimensions
    productFlavors {
        create(FlavorsConfig.devFlavor) {
            dimension = FlavorsConfig.defaultDimension
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
    implementation(project(":common"))
    implementation(project(":design"))
    implementation(project(":features:details:domain"))
    implementation(project(":features:details:data"))

    implementation(libs.androidx.material3)

    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android.core)
    implementation(libs.android.hilt.navigation)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.tooling.preview)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)

    testImplementation(libs.strikt.core)
}