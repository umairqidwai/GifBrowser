package com.mobimeo.gifbrowser.presentation.di

import com.mobimeo.gifbrowser.data.repo.GiphyRepositoryImpl
import com.mobimeo.gifbrowser.domain.repo.GiphyRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun provideRepository(giphyRepositoryImpl: GiphyRepositoryImpl) : GiphyRepository

}