// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false
    id("jacoco")
}

tasks.register<JacocoReport>("jacocoRootReport") {
    group = "reporting"
    description = "Generate a combined Jacoco report for all modules"

    subprojects.forEach { subproject ->
        dependsOn(subproject.tasks.named("jacocoTestReport"))

        subproject.plugins.withType<JacocoPlugin> {
            subproject.tasks.withType<JacocoReport>().configureEach {
                executionData.from(this.executionData)
                sourceDirectories.from(this.sourceDirectories)
                classDirectories.from(this.classDirectories)
            }
        }
    }

    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }

    val fileFilter = listOf("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*", "**/*Test*.*")

    val allClassDirs = subprojects.flatMap { subproject ->
        listOf(
            subproject.fileTree("${subproject.buildDir}/intermediates/javac/debug") { exclude(fileFilter) },
            subproject.fileTree("${subproject.buildDir}/tmp/kotlin-classes/debug") { exclude(fileFilter) }
        )
    }
    classDirectories.setFrom(files(allClassDirs))

    val allExecutionData = subprojects.flatMap { subproject ->
        listOf(
            fileTree(mapOf(
                "dir" to "${subproject.buildDir}/outputs/code_coverage/debugAndroidTest/connected",
                "includes" to listOf("**/*.ec")
            )),
            fileTree(mapOf(
                "dir" to "${subproject.buildDir}/outputs/unit_test_code_coverage/debugUnitTest",
                "includes" to listOf("testDebugUnitTest.exec")
            ))
        )
    }
    executionData.setFrom(files(allExecutionData))
}

tasks.register("testLocalReport") {
    dependsOn("jacocoRootReport")
    group = "reporting"
    doLast {
        println("Combined report available at: ${buildDir}/reports/jacoco/jacocoRootReport/html/index.html")
    }
}