plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    id("jacoco")
}

android {
    namespace = "com.jhoglas.infrastructure"
    compileSdk = 34

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            enableUnitTestCoverage = true
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    testOptions {
        useLibrary("org.apache.http.legacy")
        animationsDisabled = true
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
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
}

jacoco {
    toolVersion = "0.8.12"
}

tasks.withType<Test> {
    extensions.configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = false
        excludes = listOf("jdk.internal.*")
    }
    finalizedBy(tasks.named("jacocoTestReport"))
}

tasks.register<JacocoReport>("jacocoTestReport") {
    group = "reporting"
    description = "Generate Jacoco code coverage report for unit tests"

    val reportXmlPath = "$buildDir/reports/jacoco/test/jacocoTestReport.xml"
    val reportHtmlPath = "$buildDir/reports/jacoco/jacocoHtml"

    reports {
        xml.required = true
        xml.outputLocation = file(reportXmlPath)
        html.required = true
        html.outputLocation = file(reportHtmlPath)
        csv.required = false
    }

    val fileFilter = listOf("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*", "**/*Test*.*")

    val javaDebugTree = fileTree("${buildDir}/intermediates/javac/debug") {
        exclude(fileFilter)
    }
    val kotlinDebugTree = fileTree("${buildDir}/tmp/kotlin-classes/debug") {
        exclude(fileFilter)
    }
    val mainSrc = "${project.projectDir}/src/main/java"

    sourceDirectories.setFrom(files(mainSrc))
    classDirectories.setFrom(files(javaDebugTree, kotlinDebugTree))

    executionData.from(fileTree(mapOf(
        "dir" to "${buildDir}/outputs/unit_test_code_coverage/debugUnitTest",
        "includes" to listOf("testDebugUnitTest.exec")
    )))
}