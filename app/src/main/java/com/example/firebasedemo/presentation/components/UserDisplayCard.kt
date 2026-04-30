package com.example.firebasedemo.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firebasedemo.domain.model.User
import com.example.firebasedemo.ui.theme.FirebaseDemoTheme

@Composable
fun UserDisplayCard(user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Name:${user.name}", modifier = Modifier.padding(5.dp))
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit"
            )
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete"
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))
        Text("Age: ${user.age}")
        Spacer(modifier = Modifier.padding(8.dp),)
        Text("Address: ${user.address}")
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Profession: ${user.profession}")
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Email: ${user.email}")
        Spacer(modifier = Modifier.padding(8.dp))
    }
}
@Preview
@Composable
fun UserDisplayCardPreview() {
    FirebaseDemoTheme {
        UserDisplayCard(User())
    }
}