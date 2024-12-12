import org.gradle.api.JavaVersion

object AndroidConfig {
    const val minSdkVersion = 28
    const val compileSdkVersion = 34
    const val targetSdkVersion = 34
    const val applicationId = "com.spaceflight.news"
    const val versionCode = 1
    const val versionName = "1.0"

    const val app_namespace = "com.spaceflight.news"
    const val common_namespace = "com.spaceflight.common"
    const val design_namespace = "com.spaceflight.design"
    const val feature_detail_namespace = "com.feature.details"
    const val feature_listing_namespace = "com.feature.listing"
    const val network_namespace = "com.spaceflight.network"
}

object CompilerConfig {
    val sourceCompatibility = JavaVersion.VERSION_17
    val targetCompatibility = JavaVersion.VERSION_17
    val jvmTarget = "17"
}

object FlavorsConfig {
    const val devFlavor = "dev"
    const val prodFlavor = "prod"
    const val applicationIdSuffix = ".demo"
    const val versionNameSuffix = "-demo"
    val flavorDimensions = listOf("default")
    const val defaultDimension = "default"
}
