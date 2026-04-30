package com.example.firebasedemo.domain.usecases

import com.example.firebasedemo.domain.Repository.UserRepo
import com.example.firebasedemo.domain.model.User

class GetUserUserCase(val userRepo: UserRepo) {
    suspend operator fun invoke(onSuccess:(List<User>)-> Unit, onFailure:(String)-> Unit){
        userRepo.getAllUser(onSuccess,onFailure)
    }
}