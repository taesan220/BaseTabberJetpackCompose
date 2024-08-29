package com.example.basetabberjetpackcompose

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemList() {
    val context = LocalContext.current
    val textFieldText = remember { mutableStateOf("") } // State to hold the text field input

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 상단에서 40dp 떨어지게, 왼쪽에서 20dp Text 배치
            Text(
                text = "Item List",
                fontSize = 30.sp,
                color = colorResource(id = R.color.black),
                modifier = Modifier
                    .padding(top = 40.dp, start = 20.dp)
                    .align(Alignment.Start) // Align text to the left
            )

            // TextField for input
            OutlinedTextField(
                value = textFieldText.value,
                onValueChange = { textFieldText.value = it },
                label = { Text("Enter text here") },
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                singleLine = true
            )

            // Center button that clears the TextField
            Button(
                onClick = {
                    textFieldText.value = "" // Clear the text field when button is pressed
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.CenterHorizontally) // Center the button
            ) {
                Text("Clear Text")
            }

            Spacer(modifier = Modifier.height(16.dp)) // Adds space between elements

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                FloatingActionButton(onClick = {
                    Toast.makeText(
                        context,
                        "Open bottom sheet",
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                    Icon(Icons.Default.Add, contentDescription = null, tint = colorResource(id = R.color.tabbar))
                }
            }
        }
    }
}



@Preview(showBackground = true, device = Devices.PHONE)
@Composable
fun ItemListPreview() {
    ItemList()
}