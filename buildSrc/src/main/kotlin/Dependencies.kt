object Dependencies {

    //Android Support and AppCompat
    const val androidxCoreKtx = "androidx.core:core-ktx:${Versions.androidxCoreKtx}"
    const val androidxAppcompat = "androidx.appcompat:appcompat:${Versions.androidxAppcompat}"
    const val androidxMaterial = "com.google.android.material:material:${Versions.androidxMaterial}"
    const val androidxConstraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.androidxConstraintLayout}"

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

}