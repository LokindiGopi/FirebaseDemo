package com.example.firebasedemo.di.module

import com.example.firebasedemo.data.remote.Repository.UserRepoImpl
import com.example.firebasedemo.domain.Repository.UserRepo
import com.example.firebasedemo.domain.usecases.AddUserUserCase
import com.example.firebasedemo.domain.usecases.DeleteUserUserCase
import com.example.firebasedemo.domain.usecases.GetUserUserCase
import com.example.firebasedemo.domain.usecases.UpdateUserUserCase
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun providesDatabaseObject(): FirebaseDatabase = FirebaseDatabase.getInstance()

    @Provides
    @Singleton
    fun providesRepository(firebaseDatabase: FirebaseDatabase): UserRepo =
        UserRepoImpl(firebaseDatabase)


    @Provides
    @Singleton
    fun providesAddUserUserCase(userRepo: UserRepo): AddUserUserCase = AddUserUserCase(userRepo)

    @Provides
    @Singleton
    fun providesGetUserUserCase(userRepo: UserRepo): GetUserUserCase = GetUserUserCase(userRepo)

    @Provides
    @Singleton
    fun providesUpdateUserUserCase(userRepo: UserRepo): UpdateUserUserCase = UpdateUserUserCase(userRepo)

    @Provides
    @Singleton
    fun providesDeleteUserUserCase(userRepo: UserRepo): DeleteUserUserCase = DeleteUserUserCase(userRepo)

}