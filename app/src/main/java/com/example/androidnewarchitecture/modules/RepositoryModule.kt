package com.example.androidnewarchitecture.modules

import android.app.Application
import com.example.androidnewarchitecture.data.remote.MovieDBApiService
import com.example.androidnewarchitecture.data.repository.MovieDBRepository
import com.example.androidnewarchitecture.data.repository.MovieDBRepositoryImpl
import com.example.androidnewarchitecture.utils.StringUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideStringUtils(app: Application): StringUtils {
        return StringUtils(app)
    }

    @Singleton
    @Provides
    fun provideImagineRepository(stringUtils: StringUtils, apiService: MovieDBApiService): MovieDBRepository {
        return MovieDBRepositoryImpl(stringUtils, apiService)
    }
}
