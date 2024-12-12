pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SpaceFlight-News"
include(":app")
include(":common")
include(":design")
include(":network")

include(":features:details:presentation")
include(":features:details:domain")
include(":features:details:data")

include(":features:listing:presentation")
include(":features:listing:domain")
include(":features:listing:data")

