plugins {
    kotlin("js") version "1.7.21"
    id("org.ajoberstar.git-publish") version "2.1.3"
}

version = "1.0"

repositories {
    mavenCentral()
}

kotlin {
    js {
        browser()
        binaries.executable()
    }
}

fun kotlinw(target: String): String =
    "org.jetbrains.kotlin-wrappers:kotlin-$target"

dependencies {
    implementation(enforcedPlatform(kotlinw("wrappers-bom:1.0.0-pre.454")))
    implementation(kotlinw("react"))
    implementation(kotlinw("react-dom"))
    implementation(kotlinw("react-router-dom"))
    implementation(kotlinw("styled-next"))

    implementation(npm("css-in-js-utils", "3.1.0"))
    implementation(npm("inline-style-prefixer", "7.0.0"))
    implementation(npm("styled-components", "5.3.6"))
    implementation(npm("core-js", "3.26.1"))
}

gitPublish {
    repoUri.set("git@github.com:KodeinKoders/CVs.git")
    branch.set("gh-pages")
    contents {
        from("$buildDir/distributions")
    }
}

tasks["gitPublishCopy"].dependsOn("browserDistribution")
