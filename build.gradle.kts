plugins {
    application
    java
    id("org.openjfx.javafxplugin") version "0.0.9"
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    testImplementation("junit", "junit", "4.12")
}

javafx {
    version = "11.0.2"
    modules("javafx.controls", "javafx.fxml")
}
