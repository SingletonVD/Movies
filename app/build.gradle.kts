plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.singletonvd.movies"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.singletonvd.movies"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.rxjava)
    implementation(libs.rxandroid)
    implementation(libs.room.runtime)
    implementation(libs.room.rxjava3)
    implementation(libs.glide)
    implementation(libs.retrofit)
    implementation(libs.retrofit.rxjava3)
    implementation(libs.retrofit.gson)
    annotationProcessor(libs.room.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}