package com.example.firebasedemo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebasedemo.domain.model.User
import com.example.firebasedemo.domain.usecases.AddUserUserCase
import com.example.firebasedemo.domain.usecases.DeleteUserUserCase
import com.example.firebasedemo.domain.usecases.GetUserUserCase
import com.example.firebasedemo.domain.usecases.UpdateUserUserCase
import com.example.firebasedemo.presentation.components.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewmodel @Inject constructor(
    val addUserUserCase: AddUserUserCase,
    val getUserUserCase: GetUserUserCase,
    val updateUserUserCase: UpdateUserUserCase,
    val deleteUserUserCase: DeleteUserUserCase
) : ViewModel() {

    private val _users = MutableStateFlow<UIState>(UIState.Initial)

    val users = _users.asStateFlow()

    init {
        getAllUsers()
    }
    fun addUsers(user: User) {
        _users.value = UIState.Loading
        viewModelScope.launch {
            addUserUserCase(user)
        }
    }

    fun getAllUsers() {

        viewModelScope.launch {
            getUserUserCase(
                onSuccess = {
                    _users.value = UIState.Success(it)
                },
                onFailure = {
                    _users.value = UIState.Error(it)
                })
        }

    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            updateUserUserCase(user)
        }
    }

    fun deleteUser(userId: String) {
        //Log.d("UserViewModel", "deleteUser: $userId ")
        viewModelScope.launch(Dispatchers.IO) {
            deleteUserUserCase(userId)
        }
    }


}