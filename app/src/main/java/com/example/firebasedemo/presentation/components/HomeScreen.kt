package com.example.firebasedemo.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.firebasedemo.domain.model.User
import com.example.firebasedemo.presentation.viewmodel.UserViewmodel
import com.example.firebasedemo.ui.theme.FirebaseDemoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewmodel: UserViewmodel = hiltViewModel()) {


    val showDialog = remember { mutableStateOf(false) }
    val name = remember { mutableStateOf("") }
    val age = remember { mutableStateOf("") }
    val address = remember { mutableStateOf("") }
    val profession = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val selectedUser = remember { mutableStateOf<User?>(null) }


    val uiState = viewmodel.users.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("FireBaseDemo") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog.value = true }
            ) { Text("Add") }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (uiState.value) {
                is UIState.Error -> {

                }

                UIState.Initial -> {

                }

                UIState.Loading -> {

                }

                is UIState.Success -> {
                    val users = (uiState.value as UIState.Success)
                    LazyColumn {
                        items(users.users.size) {
                            //UserDisplayCard(users.users[it])
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp)
                            ) {
                                Row(modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text(text = "Name:${users.users[it].name}", fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp))
                                    IconButton(
                                        onClick = {
                                            showDialog.value = true
                                            selectedUser.value = users.users[it]

                                            name.value = users.users[it].name
                                            age.value = users.users[it].age
                                            address.value = users.users[it].address
                                            profession.value = users.users[it].profession
                                            email.value = users.users[it].email
                                            Log.d("Select user", "$selectedUser")
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Edit,
                                            contentDescription = "Edit",
                                            tint = Color.Blue
                                        )
                                    }
                                    IconButton(
                                        onClick = {
                                            selectedUser.value = users.users[it]
                                            Log.d("check", "HomeScreen: $selectedUser")
                                            viewmodel.deleteUser(
                                                selectedUser.value?.id ?:""
                                            )
                                        }
                                    ){
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Delete",
                                            tint = Color.Red

                                        )
                                    }

                                }

                                Spacer(modifier = Modifier.padding(3.dp))
                                Text(text = "Age: ${users.users[it].age}",Modifier.padding(8.dp))
                                Spacer(modifier = Modifier.padding(3.dp),)
                                Text("Address: ${users.users[it].address}",Modifier.padding(8.dp))
                                Spacer(modifier = Modifier.padding(3.dp))
                                Text("Profession: ${users.users[it].profession}",Modifier.padding(8.dp))
                                Spacer(modifier = Modifier.padding(3.dp))
                                Text("Email: ${users.users[it].email}",Modifier.padding(8.dp))
                                Spacer(modifier = Modifier.padding(3.dp))
                            }
                        }
                    }
                }
            }
        }
    }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Add User") },
            text = {
                Column {
                    OutlinedTextField(
                        value = name.value,
                        onValueChange = { name.value = it },
                        placeholder = { Text("Enter Name") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = age.value,
                        onValueChange = { age.value = it },
                        placeholder = { Text("Enter Age") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = address.value,
                        onValueChange = { address.value = it },
                        placeholder = { Text("Enter Address") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = profession.value,
                        onValueChange = { profession.value = it },
                        placeholder = { Text("Enter Profession") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = email.value,
                        onValueChange = { email.value = it },
                        placeholder = { Text("Enter Email") }
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {

                        val user = selectedUser.value

                        if (user != null && user.id.isNotEmpty()) {
                            viewmodel.updateUser(
                                user.copy(
                                    name = name.value,
                                    age = age.value,
                                    address = address.value,
                                    profession = profession.value,
                                    email = email.value
                                )
                            )
                        }
                        else{
                            viewmodel.addUsers(
                                User(
                                    name = name.value,
                                    age = age.value,
                                    address = address.value,
                                    profession = profession.value,
                                    email = email.value
                                )
                            )
                        }
                        showDialog.value = false
                    }
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog.value = false }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    FirebaseDemoTheme {
        HomeScreen()
    }
}