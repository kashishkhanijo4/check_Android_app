plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.earnmart"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.earnmart"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation(libs.firebase.auth.ktx)
    val nav_version = "2.8.2"
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("com.github.leandroborgesferreira:loading-button-android:2.3.0")
    // https://mvnrepository.com/artifact/com.github.bumptech.glide/glide
    implementation("com.github.bumptech.glide:glide:5.0.0-rc01")
    // https://mvnrepository.com/artifact/de.hdodenhof/circleimageview
    implementation("de.hdodenhof:circleimageview:3.1.0")
    // https://mvnrepository.com/artifact/io.github.vejei.viewpagerindicator/viewpagerindicator
//    implementation("io.github.vejei.viewpagerindicator:viewpagerindicator:1.0.0-alpha.1") // Creating Error //
    implementation("androidx.viewpager2:viewpager2:1.1.0") // in replacement of above
    // https://mvnrepository.com/artifact/com.shuhart.stepview/stepview
//    implementation("com.shuhart.stepview:stepview:1.5.1") //creating problem // still looking for replacement//

    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

//kapt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

//firebase
    implementation("com.google.firebase:firebase-analytics:22.1.2")

//firebase google auth...
    implementation ("com.google.firebase:firebase-auth:23.0.0")

//firestore
    implementation("com.google.firebase:firebase-firestore:25.1.0")

}
kapt {
    correctErrorTypes = true
}