plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false
    id("jacoco")
    id("com.github.nbaztec.coveralls-jacoco") version "1.2.20"
}

buildscript {
    repositories {
        mavenCentral()
    }
}

tasks {
    register<JacocoReport>("jacocoRootReport") {
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

        // Define o caminho onde o relatório XML será salvo
        val reportXmlPath = "$buildDir/reports/jacoco/test/jacocoRootReport.xml"
        val reportHtmlPath = "$buildDir/reports/jacoco/jacocoHtml"

        reports {
            xml.required = true
            xml.outputLocation = file(reportXmlPath)
            html.required = true
            html.outputLocation = file(reportHtmlPath)
            csv.required = false
        }

        val fileFilter = listOf(
            "**/R.class",
            "**/R$*.class",
            "**/BuildConfig.*",
            "**/Manifest*.*",
            "**/*Test*.*"
        )

        val allClassDirs = subprojects.flatMap { subproject ->
            listOf(
                subproject.fileTree("${subproject.buildDir}/intermediates/javac/debug") {
                    exclude(
                        fileFilter
                    )
                },
                subproject.fileTree("${subproject.buildDir}/tmp/kotlin-classes/debug") {
                    exclude(
                        fileFilter
                    )
                }
            )
        }
        classDirectories.setFrom(files(allClassDirs))

        val allExecutionData = subprojects.flatMap { subproject ->
            listOf(
                fileTree(
                    mapOf(
                        "dir" to "${subproject.buildDir}/outputs/code_coverage/debugAndroidTest/connected",
                        "includes" to listOf("**/*.ec")
                    )
                ),
                fileTree(
                    mapOf(
                        "dir" to "${subproject.buildDir}/outputs/unit_test_code_coverage/debugUnitTest",
                        "includes" to listOf("testDebugUnitTest.exec")
                    )
                )
            )
        }
        executionData.setFrom(files(allExecutionData))

        // Configuração do Coveralls
        coverallsJacoco {
            // Define o caminho para o relatório XML gerado
            reportPath = reportXmlPath
        }
    }
}