package com.example.firebasedemo.domain.Repository

import com.example.firebasedemo.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepo {

    suspend fun addUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun getAllUser(onSuccess:(List<User>)-> Unit,onError:(String)->Unit)

}