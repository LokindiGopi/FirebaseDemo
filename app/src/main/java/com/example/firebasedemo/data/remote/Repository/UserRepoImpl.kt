package com.example.firebasedemo.data.remote.Repository

import com.example.firebasedemo.data.remote.model.UserDto
import com.example.firebasedemo.domain.Repository.UserRepo
import com.example.firebasedemo.domain.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class UserRepoImpl(val database: FirebaseDatabase) : UserRepo {

    val userRef = database.getReference("users")

    override suspend fun addUser(user: User) {
        val userId = userRef.push().key ?: return
        userRef.child(userId).setValue(
            user.copy(
                id = userId
            )
        )

    }

    override suspend fun updateUser(user: User) {
        if (user.id.isBlank()) return
        userRef.child(user.id).setValue(user)
    }

    override suspend fun deleteUser(user: User) {

    }

    override suspend fun getAllUser(onSuccess: (List<User>) -> Unit, onError: (String) -> Unit) {
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.children.mapNotNull {
                    it.getValue(User::class.java)
                }
                onSuccess(user)
            }

            override fun onCancelled(error: DatabaseError) {
                onError(error.message)
            }

        })

    }
}