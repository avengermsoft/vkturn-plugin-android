import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.IOException

plugins {
    id("com.android.application")
    kotlin("android")
}

val minSdkVersion = 23

android {
    val javaVersion = JavaVersion.VERSION_1_8
    namespace = "com.avnmsoft.shadowsocks.plugin.vkturn"
    compileSdk = 36
    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    defaultConfig {
        applicationId = "com.avnmsoft.shadowsocks.plugin.vkturn"
        minSdk = minSdkVersion
        targetSdk = 36
        versionCode = 1
        versionName = (findProperty("versionName") as String?) ?: "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    ndkVersion = "27.2.12479018"
    packagingOptions.jniLibs.useLegacyPackaging = true
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_1_8)
    }
}

dependencies {
    implementation("androidx.preference:preference:1.2.1")
    implementation("com.github.shadowsocks:plugin:2.0.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test:runner:1.7.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
}
