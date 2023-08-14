package com.diego.gehrke.learn.intelligentia.di

import com.diego.gehrke.learn.intelligentia.domain.repository.UserRepository
import com.diego.gehrke.learn.intelligentia.data.remote.UserRepositoryImpl
import com.diego.gehrke.learn.intelligentia.domain.usecase.CreateUserUseCase
import com.diego.gehrke.learn.intelligentia.viewmodel.SignupWithEmailViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object SignupViewModelModule {

    @Provides
    fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl()
    }

    @Provides
    fun provideCreateUserCase(userRepositoryImpl: UserRepositoryImpl): CreateUserUseCase {
        return CreateUserUseCase(userRepositoryImpl)
    }

    @Provides
    fun provideSignupWithEmailViewModel(
        createUserUseCase: CreateUserUseCase
    ): SignupWithEmailViewModel {
        return SignupWithEmailViewModel(createUserUseCase)
    }
}