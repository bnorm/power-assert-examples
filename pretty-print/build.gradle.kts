@file:OptIn(ExperimentalWasmDsl::class, ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.plugin.powerassert)
}

kotlin {
    jvmToolchain(21)

    compilerOptions {
        optIn.add("kotlinx.powerassert.ExperimentalPowerAssert")
    }

    jvm()

    js {
        browser()
        nodejs()
    }

    // TODO(KT-85089) bug in power-assert IR generation for Wasm
    // wasmJs {
    //     nodejs()
    // }
    //
    // wasmWasi {
    //     nodejs()
    // }

    // Tier 1
    macosArm64()
    iosSimulatorArm64()
    iosArm64()

    // Tier 2
    linuxX64()
    linuxArm64()
    watchosSimulatorArm64()
    watchosArm32()
    watchosArm64()
    tvosSimulatorArm64()
    tvosArm64()

    // Tier 3
    androidNativeArm32()
    androidNativeArm64()
    androidNativeX86()
    androidNativeX64()
    mingwX64()
    watchosDeviceArm64()
    @Suppress("DEPRECATION") macosX64()
    @Suppress("DEPRECATION") iosX64()
    @Suppress("DEPRECATION") watchosX64()
    @Suppress("DEPRECATION") tvosX64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.powerassert)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

powerAssert {
    includedSourceSets = provider { kotlin.sourceSets.map { it.name } }
}
