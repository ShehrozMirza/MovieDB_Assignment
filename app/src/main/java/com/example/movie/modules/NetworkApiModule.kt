package com.example.movie.modules

import com.example.movie.data.remote.ApiResponseCallAdapterFactory
import com.example.movie.data.remote.MovieDBApiService
import com.example.movie.utils.AppConstants.BASE_URL
import com.example.movie.utils.AppConstants.TIME_OUT_OKHTTP_REQUEST
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkApiModule {

    @Provides
    @Singleton
    fun providesBaseUrl(): String {
        return BASE_URL
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .callTimeout(TIME_OUT_OKHTTP_REQUEST, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT_OKHTTP_REQUEST, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_OKHTTP_REQUEST, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT_OKHTTP_REQUEST, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request()
                val newRequest = request.newBuilder().header("Content-Type", "application/json")
                chain.proceed(newRequest.build())
            }
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient,baseUrl:String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun providesMovieDBApiService(retrofit: Retrofit): MovieDBApiService {
        return retrofit.create(MovieDBApiService::class.java)
    }
}
