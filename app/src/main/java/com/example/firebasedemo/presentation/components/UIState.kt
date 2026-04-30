package com.example.firebasedemo.presentation.components

import com.example.firebasedemo.domain.model.User

sealed  class UIState {

    data class Success(val users:List<User>): UIState()
    data class Error(val message: String): UIState()
    object Loading: UIState()
    object Initial: UIState()
}

