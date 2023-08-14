package com.diego.gehrke.learn.intelligentia.di

import android.content.Context
import android.content.SharedPreferences
import com.diego.gehrke.learn.intelligentia.data.local.UserPreferences
import com.diego.gehrke.learn.intelligentia.data.local.UserPreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideUserPreferences(sharedPreferences: SharedPreferences): UserPreferences {
        return UserPreferencesImpl(sharedPreferences)
    }
}