plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "com.example.demo.mediation"
    compileSdk = 33

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    publishing {
        multipleVariants ("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }

    buildTypes {

        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}


afterEvaluate {
    publishing {
        publications {
            val debug by publications.registering(MavenPublication::class) {
                from(components["debug"])
//                artifact(sourcesJar.get())
//                artifactId = "mediation"
//                groupId = "com.github.aszini"
//                version = "1.5"
            }

            val release by publications.registering(MavenPublication::class) {
                from(components["release"])
//                artifact(sourcesJar.get())
//                artifactId = "mediation"
//                groupId = "com.github.aszini"
//                version = "1.5"
            }
        }

        repositories {
            maven {
                name = "BuildFolder"
                setUrl("${project.buildDir}/repository")
//                url = "${project.buildDir}/repository"
            }
        }

    }
}

