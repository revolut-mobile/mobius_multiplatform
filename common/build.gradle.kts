import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("kotlinx-serialization")
    kotlin("multiplatform") //version("1.3.50")
}

kotlin {
    //select iOS target platform depending on the Xcode environment variables
    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
            if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
                ::iosArm64
            else
                ::iosX64

    iOSTarget("ios") {
        binaries {
            framework {
                baseName = "SharedCode"
            }
        }
    }

    jvm("android")

    sourceSets["commonMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${project.extra["coroutines_version"]}")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${project.extra["serialization_version"]}")

        implementation("io.ktor:ktor-client-core:${project.extra["ktor_version"]}")
        implementation("io.ktor:ktor-client-serialization:${project.extra["ktor_version"]}")
        implementation("io.ktor:ktor-client-json:${project.extra["ktor_version"]}")
    }

    sourceSets["iosMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")

        implementation("io.ktor:ktor-client-ios:${project.extra["ktor_version"]}")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:${project.extra["serialization_version"]}")
        implementation("io.ktor:ktor-client-serialization-iosx64:${project.extra["ktor_version"]}")
    }

    sourceSets["androidMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        implementation("io.ktor:ktor-client-json-jvm:${project.extra["ktor_version"]}")
        implementation("io.ktor:ktor-client-serialization-jvm:${project.extra["ktor_version"]}")
        implementation("io.ktor:ktor-client-android:${project.extra["ktor_version"]}")
    }
    sourceSets["iosMain"].languageSettings.useExperimentalAnnotation("kotlin.Experimental")
}

val packForXcode by tasks.creating(Sync::class) {
    val targetDir = File(buildDir, "xcode-frameworks")

    /// selecting the right configuration for the iOS
    /// framework depending on the environment
    /// variables set by Xcode build
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets
            .getByName<KotlinNativeTarget>("ios")
            .binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)

    from({ framework.outputDirectory })
    into(targetDir)

    /// generate a helpful ./gradlew wrapper with embedded Java path
    doLast {
        val gradlew = File(targetDir, "gradlew")
        gradlew.writeText("#!/bin/bash\n"
                + "export 'JAVA_HOME=${System.getProperty("java.home")}'\n"
                + "cd '${rootProject.rootDir}'\n"
                + "./gradlew \$@\n")
        gradlew.setExecutable(true)
    }
}

tasks.getByName("build").dependsOn(packForXcode)