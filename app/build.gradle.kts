plugins {
    id(AppPlugins.androidApplication)
    id(AppPlugins.kotlinAndroid)
    id(AppPlugins.kotlinKapt)
    id(AppPlugins.androidHilt)
    id(AppPlugins.parcelize)
    id(AppPlugins.navigationSafeArg)
}

android {

    defaultConfig {
        applicationId = AndroidConfig.DefaultConfig.applicationId
        compileSdk = AndroidConfig.DefaultConfig.compileSdkVersion
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
    implementation(Dependencies.androidxCoreKtx)
    implementation(Dependencies.androidxAppcompat)
    implementation(Dependencies.androidxMaterial)
    implementation(Dependencies.androidxConstraintLayout)
    implementation(Dependencies.androidxMaterial)
    implementation(Dependencies.androidxConstraintLayout)

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

    //Testing
    testImplementation(Testing.junit)
    androidTestImplementation(Testing.androidxJUnit)
    androidTestImplementation(Testing.espressoCore)

}


