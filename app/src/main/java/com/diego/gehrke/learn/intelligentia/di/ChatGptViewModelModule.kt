package com.diego.gehrke.learn.intelligentia.di

import com.diego.gehrke.learn.intelligentia.data.remote.ConversationRepositoryImpl
import com.diego.gehrke.learn.intelligentia.data.remote.MessageRepositoryImpl
import com.diego.gehrke.learn.intelligentia.domain.repository.ConversationRepository
import com.diego.gehrke.learn.intelligentia.domain.repository.MessageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ChatGptViewModelModule {
    @Binds
    abstract fun conversationRepository(
        repo: ConversationRepositoryImpl
    ): ConversationRepository

    @Binds
    abstract fun messageRepository(
        repo: MessageRepositoryImpl
    ): MessageRepository

}