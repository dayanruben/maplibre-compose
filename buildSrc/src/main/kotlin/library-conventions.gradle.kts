plugins {
  id("module-conventions")
  id("org.jetbrains.kotlin.multiplatform")
  id("org.jetbrains.kotlin.plugin.serialization")
  id("org.jetbrains.dokka")
  id("maven-publish")
}

group = "com.dayanruben.maplibre-compose"
version = "0.6.22"

kotlin {
  explicitApi()

  jvmToolchain(properties["jvmToolchain"]!!.toString().toInt())

  compilerOptions {
    allWarningsAsErrors = false // TODO re-enable after Compose 1.8.1
    freeCompilerArgs.addAll("-Xexpect-actual-classes", "-Xconsistent-data-class-copy-visibility")
  }
}

dokka {
  dokkaSourceSets {
    configureEach {
      includes.from("MODULE.md")
      sourceLink {
        remoteUrl("https://github.com/dayanruben/maplibre-compose/tree/${project.ext["base_tag"]}/")
        localDirectory.set(rootDir)
      }
      externalDocumentationLinks {
        create("spatial-k") { url("https://dellisd.github.io/spatial-k/api/") }
        create("maplibre-native") {
          url("https://maplibre.org/maplibre-native/android/api/")
          packageListUrl(
            "https://maplibre.org/maplibre-native/android/api/-map-libre%20-native%20-android/package-list"
          )
        }
      }
    }
  }
}

publishing {
  repositories {
    maven {
      name = "GitHubPackages"
      setUrl("https://maven.pkg.github.com/dayanruben/maplibre-compose")
      credentials {
        username = project.properties["githubUser"]?.toString()
        password = project.properties["githubToken"]?.toString()
      }
    }
  }
}
