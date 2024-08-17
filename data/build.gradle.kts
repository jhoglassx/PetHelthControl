plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    id("kotlin-parcelize")
    id("jacoco")
}

android {
    namespace = "com.jhoglas.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    //Koin
    implementation(libs.koin.compose)

    //OKHttp
    implementation(libs.okhttp)

    //Coroutines
    implementation(libs.coroutines.core)

    //Module
    implementation(project(":domain"))
    implementation(project(":infrastructure"))
}

jacoco {
    toolVersion = "0.8.11"
}

tasks.withType<Test> {
    useJUnitPlatform()

    finalizedBy(tasks.named("jacocoTestReport"))
}

tasks.register("jacocoTestReport", JacocoReport::class) {
    dependsOn("testDebugUnitTest", "createDebugCoverageReport")

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    val fileFilter = listOf("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*", "**/*Test*.*")

    sourceDirectories.setFrom(files("$projectDir/src/main/java"))
    classDirectories.setFrom(files("$buildDir/tmp/kotlin-classes/debug").map {
        fileTree(it) {
            exclude(fileFilter)
        }
    })

    executionData.setFrom(
        files(
            "$buildDir/jacoco/testDebugUnitTest.exec",
            "$buildDir/outputs/jacoco/connected/*coverage.ec"
        )
    )
}
