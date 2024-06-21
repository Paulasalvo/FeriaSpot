package com.namkuzo.feriaspot.di

import android.content.Context
import com.namkuzo.feriaspot.data.source.CountryRegionProvider
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SourceModule {
    @Singleton
    @Provides
    fun provideCountryRegionProvider(
        @ApplicationContext context : Context,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
    ): CountryRegionProvider = CountryRegionProvider(
        context = context,
        moshi = Moshi.Builder().build(),
        ioDispatcher = coroutineDispatcher
    )
}
