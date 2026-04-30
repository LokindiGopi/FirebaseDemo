package com.example.firebasedemo.domain.usecases

import com.example.firebasedemo.domain.Repository.UserRepo
import com.example.firebasedemo.domain.model.User

class AddUserUserCase (val userRepo: UserRepo){

    suspend operator fun invoke(user: User){
        userRepo.addUser(user)
    }

}