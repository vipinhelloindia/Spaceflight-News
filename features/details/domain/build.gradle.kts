plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)

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
}

dependencies {

    implementation(project(":common"))

    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android.core)
    implementation(libs.android.hilt.navigation)


    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    testImplementation(libs.strikt.core)
}