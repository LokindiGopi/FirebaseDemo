package com.example.firebasedemo.domain.usecases

import com.example.firebasedemo.domain.Repository.UserRepo
import com.example.firebasedemo.domain.model.User

class DeleteUserUserCase  (val userRepo: UserRepo){

    suspend operator fun invoke(userId: String){
        userRepo.deleteUser(userId)
    }

}