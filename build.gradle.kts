import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("dev.hydraulic.conveyor") version "1.3"
}

group = "com.nordmoen"
version = "1.0.5"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.6.4")
    linuxAmd64(compose.desktop.linux_x64)
    macAmd64(compose.desktop.macos_x64)
    macAarch64(compose.desktop.macos_arm64)
    windowsAmd64(compose.desktop.windows_x64)
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "stopwatch-desktop"
            packageVersion = "1.0.5"
            macOS {
                iconFile.set(project.file("icons/icon.icns"))
            }
            windows {
                iconFile.set(project.file("icons/icon.ico"))
            }
        }
    }
}

// region Work around temporary Compose bugs.
configurations.all {
    attributes {
        // https://github.com/JetBrains/compose-jb/issues/1404#issuecomment-1146894731
        attribute(Attribute.of("ui", String::class.java), "awt")
    }
}

// Force override the Kotlin stdlib version used by Compose to 1.7, as otherwise we can end up with a mix of 1.6 and 1.7 on our classpath.
dependencies {
    val v = "1.7.10"
    for (m in setOf("linuxAmd64", "macAmd64", "macAarch64", "windowsAmd64")) {
        m("org.jetbrains.kotlin:kotlin-stdlib:$v")
        m("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$v")
        m("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$v")
    }
}
// endregion

