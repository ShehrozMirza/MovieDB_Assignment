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

    testOptions {
        unitTests.isReturnDefaultValues = true
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

    //Navigation Component
    implementation(Dependencies.navigationFragmentKtx)
    implementation(Dependencies.navigationUIKtx)

    //Hilt
    implementation(Dependencies.androidHilt)
    kapt(Dependencies.androidHiltKaptCompiler)
    implementation(Dependencies.imageCoil)

    //Paging3
    implementation(Dependencies.paging3)

    //Networking
    implementation(Dependencies.googleGson)
    implementation(Dependencies.squareRetrofit)
    implementation(Dependencies.squareRetrofitConverter)
    implementation(Dependencies.okHttpInterceptor)

    //Zoom ImageView
    implementation(Dependencies.chrisBaneImageView)

    //Android Testing Module
    testImplementation(Testing.junit)
    androidTestImplementation(Testing.androidxJUnit)
    androidTestImplementation(Testing.espressoCore)
    androidTestImplementation(Testing.androidHiltTesting)
    implementation(Testing.androidxTestCore)
    kaptAndroidTest(Testing.hiltAndroidCompiler)
    debugImplementation(Testing.fragmentTesting)
    androidTestImplementation(Testing.espressoIdling)
    androidTestImplementation(Testing.espressoContrib)
    androidTestImplementation(Testing.mockk)
    androidTestImplementation(Testing.mockitoAndroid)
    androidTestImplementation(Testing.mockitoCore)

    //Testing Module
    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
    testImplementation("io.mockk:mockk:1.12.3")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.3")
}


