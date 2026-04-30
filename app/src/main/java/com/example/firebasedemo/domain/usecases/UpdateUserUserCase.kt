package com.example.firebasedemo.domain.usecases

import com.example.firebasedemo.domain.Repository.UserRepo
import com.example.firebasedemo.domain.model.User

class UpdateUserUserCase(val userRepo: UserRepo) {
    suspend operator fun invoke(user: User){
        userRepo.updateUser(user)
    }
}