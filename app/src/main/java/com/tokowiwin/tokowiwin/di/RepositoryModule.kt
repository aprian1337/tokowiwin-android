package com.cap0097.ahuahuapp.di

import com.tokowiwin.tokowiwin.domain.repository.Repository
import com.tokowiwin.tokowiwin.domain.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun provideRepository(repository: RepositoryImpl) : Repository
}