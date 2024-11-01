package ru.netology.binlist.repo

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepoBinReqModule {
    @Singleton
    @Binds
    fun bindsRepoBinReqImpl(impl: RepoBinReqImpl): RepoBinReq
}