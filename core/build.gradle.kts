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

    //OkHttp
    api(libs.okhttp)
    api(libs.logging.interceptor)
    api(platform(libs.okhttp.bom))

    //Retrofit
    api(libs.retrofit)
    api(libs.converter.gson)
    api(libs.gson)

    // Paging3 Common
    implementation(libs.androidx.paging.common)

    // Javax inject
    implementation(libs.javax.inject)

    // Coroutines core
    api(libs.kotlinx.coroutines.core)

    testImplementation(libs.turbine)
}