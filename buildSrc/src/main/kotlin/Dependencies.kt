object Dependencies {

    //Android Support and AppCompat
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
    const val androidxCoreKtx = "androidx.core:core-ktx:${Versions.androidxCoreKtx}"
    const val androidxAppcompat = "androidx.appcompat:appcompat:${Versions.androidxAppcompat}"
    const val androidxMaterial = "com.google.android.material:material:${Versions.androidxMaterial}"
    const val androidxConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidxConstraintLayout}"
    const val androidXRecyclerView = "androidx.recyclerview:recyclerview:${Versions.androidXRecyclerView}"
    const val androidXRecyclerViewSelection = "androidx.recyclerview:recyclerview-selection:${Versions.androidXRecyclerViewSelection}"

    //Networking
    const val googleGson = "com.google.code.gson:gson:${Versions.googleGson}"
    const val squareRetrofit = "com.squareup.retrofit2:retrofit:${Versions.squareRetrofit}"
    const val squareRetrofitConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.squareRetrofit}"
    const val okHttpInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpInterceptor}"

    //Hilt
    const val androidHilt = "com.google.dagger:hilt-android:${Versions.androidHilt}"
    const val androidHiltKaptCompiler =
        "com.google.dagger:hilt-android-compiler:${Versions.androidHilt}"

    //Navigation Component
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragmentKtx}"
    const val navigationUIKtx =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigationUIKtx}"

    const val imageCoil = "io.coil-kt:coil:${Versions.coil}"

    const val paging3 = "androidx.paging:paging-runtime-ktx:${Versions.paging3}"
}