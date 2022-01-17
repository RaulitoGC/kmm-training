plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")

    kotlin(KotlinPlugins.serialization) version Kotlin.version
}

version = "1.0"

kotlin {
    android()
    ios()
//    iosSimulatorArm64()
//  iosSimulatorArm64()

//    iosX64()
//    iosArm64()
//    iosSimulatorArm64() sure all ios dependencies support this target


    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = false
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Ktor.core)
                implementation(Ktor.clientSerialization)
            }
        }
//        val commonTest by getting {
//            dependencies {
//                implementation(kotlin("test-common"))
//                implementation(kotlin("test-annotations-common"))
//            }
//        }

        val androidMain by getting {
            dependencies {
                implementation(Ktor.android)
            }
        }
//        val androidTest by getting {
//            dependencies {
//                implementation(kotlin("test-junit"))
//                implementation("junit:junit:4.13.2")
//            }
//        }

//        val iosX64Main by getting
//        val iosArm64Main by getting
//      val iosSimulatorArm64Main by getting
        val iosMain by getting {
//            dependsOn(commonMain)
//          iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation(Ktor.ios)
            }
        }

//        val iosSimulatorArm64Main by getting {
//            dependsOn(iosMain)
//        }


//        val iosX64Test by getting
//        val iosArm64Test by getting
//        //val iosSimulatorArm64Test by getting
//        val iosTest by creating {
//            dependsOn(commonTest)
//            iosX64Test.dependsOn(this)
//            iosArm64Test.dependsOn(this)
//            //iosSimulatorArm64Test.dependsOn(this)
//        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 26
        targetSdk = 32
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}