plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
dependencies {
    implementation(project(":core"))
    
    // Paging3 Common
    implementation(libs.androidx.paging.common)

    api(libs.junit)
    api(libs.mockito.core)
    api(libs.mockito.kotlin)
    api(libs.kotlinx.coroutines.test)

}
