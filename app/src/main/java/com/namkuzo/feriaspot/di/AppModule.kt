package com.namkuzo.feriaspot.di

import com.namkuzo.feriaspot.data.repository.SpotRepository
import com.namkuzo.feriaspot.data.repository.SpotRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindEmployeeRepository(repository: SpotRepositoryImpl) : SpotRepository
}
