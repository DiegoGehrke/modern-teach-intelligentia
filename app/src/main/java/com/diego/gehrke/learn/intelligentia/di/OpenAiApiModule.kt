package com.diego.gehrke.learn.intelligentia.di

import com.diego.gehrke.learn.intelligentia.data.remote.OpenAiApiBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class OpenAiApiModule {
    @Provides
    fun providesOpenApiBuilder(): OpenAiApiBuilder {
        return OpenAiApiBuilder()
    }
}