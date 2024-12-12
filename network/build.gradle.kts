plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = AndroidConfig.network_namespace
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
}

dependencies {
    implementation(project(":common"))
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.okhttp3)
    debugImplementation(libs.okhttp3.logging.interceptor)
    implementation(libs.retrofit2.kotlinx.serialization.converter)

    implementation(libs.hilt.android.core)
    kapt(libs.hilt.compiler)

    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.junit)
    testImplementation(libs.strikt.core)

}
kapt {
    correctErrorTypes = true
}