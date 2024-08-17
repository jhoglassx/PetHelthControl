// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false

    jacoco
}

jacoco {
    toolVersion = "0.8.7"
}

subprojects {
    apply(plugin = "jacoco")

    tasks.withType<Test> {
        finalizedBy(tasks.named("jacocoTestReport"))
    }


    tasks.register<JacocoReport>("jacocoTestReport") {
        dependsOn("test") // Garante que os testes sejam executados antes de gerar o relatório

        val fileFilter = listOf(
            "**/R.class",
            "**/R$*.class",
            "**/BuildConfig.*",
            "**/Manifest*.*",
            "**/*Test*.*"
        )

        val debugTree = fileTree("${project.buildDir}/classes/java/main") {
            exclude(fileFilter)
        }

        sourceDirectories.setFrom(files("src/main/java"))
        classDirectories.setFrom(files(debugTree))
        executionData.setFrom(fileTree("${project.buildDir}") {
            include("jacoco/*.exec")
        })

        reports {
            xml.required.set(true)
            html.required.set(true)
            html.outputLocation.set(file("${buildDir}/reports/jacoco/jacocoTestReport/html"))
        }
    }
}