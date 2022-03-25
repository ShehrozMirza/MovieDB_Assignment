package com.example.movie.modules

import android.app.Application
import com.example.movie.data.remote.MovieDBApiService
import com.example.movie.data.repository.MovieDBRepository
import com.example.movie.data.repository.MovieDBRepositoryImpl
import com.example.movie.utils.StringUtils
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
