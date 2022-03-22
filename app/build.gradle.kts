plugins {
    id(AppLevelPlugins.androidApplication)
    id(AppLevelPlugins.kotlinAndroid)
    id(AppLevelPlugins.kotlinKapt)
    id(AppLevelPlugins.androidHilt)
    id(AppLevelPlugins.parcelize)
    id(AppLevelPlugins.navigationSafeArg)
}

android {
    compileSdk = AndroidConfig.DefaultConfig.compileSdkVersion

    defaultConfig {
        applicationId = AndroidConfig.DefaultConfig.applicationId
        minSdk = AndroidConfig.DefaultConfig.minSdkVersion
        targetSdk = AndroidConfig.DefaultConfig.targetSdkVersion
        versionCode = AndroidConfig.DefaultConfig.versionCode
        versionName = AndroidConfig.DefaultConfig.versionName

        testInstrumentationRunner = AndroidConfig.DefaultConfig.androidTestInstrumentationRunner
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

    buildFeatures {
        viewBinding = true
    }

    kotlinOptions {
        jvmTarget = AndroidConfig.jvmTarget
    }
}

dependencies {

    //Android Support and AppCompat
    implementation(Dependencies.legacySupport)
    implementation(Dependencies.androidxCoreKtx)
    implementation(Dependencies.androidxAppcompat)
    implementation(Dependencies.androidxMaterial)
    implementation(Dependencies.androidxConstraintLayout)
    implementation(Dependencies.androidxMaterial)
    implementation(Dependencies.androidxConstraintLayout)
    implementation(Dependencies.androidXRecyclerView)
    implementation(Dependencies.androidXRecyclerViewSelection)

    //Networking
    implementation(Dependencies.googleGson)
    implementation(Dependencies.squareRetrofit)
    implementation(Dependencies.squareRetrofitConverter)
    implementation(Dependencies.okHttpInterceptor)

    //Navigation Component
    implementation(Dependencies.navigationFragmentKtx)
    implementation(Dependencies.navigationUIKtx)

    //Hilt
    implementation(Dependencies.androidHilt)
    kapt(Dependencies.androidHiltKaptCompiler)
    implementation(Dependencies.imageCoil)

    //Testing
    testImplementation(Testing.junit)
    androidTestImplementation(Testing.androidxJUnit)
    androidTestImplementation(Testing.espressoCore)

}


