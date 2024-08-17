// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false
    id("jacoco")
}

jacoco {
    toolVersion = "0.8.7"
}

tasks.register("jacocoRootReport", JacocoReport::class) {
    dependsOn(subprojects.map { it.tasks.named("jacocoTestReport") })

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    val fileFilter = listOf("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*", "**/*Test*.*")

    subprojects.forEach { subproject ->
        sourceDirectories.from(files("${subproject.projectDir}/src/main/java"))
        classDirectories.from(files("${subproject.buildDir}/tmp/kotlin-classes/debug").map {
            fileTree(it) {
                exclude(fileFilter)
            }
        })
        executionData.from(
            fileTree(subproject.buildDir).include(
                "jacoco/testDebugUnitTest.exec",
                "outputs/code-coverage/connected/*coverage.ec"
            )
        )
    }
}