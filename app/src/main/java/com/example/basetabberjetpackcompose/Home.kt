package com.example.basetabberjetpackcompose

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Home() {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
//            .background(Color.Red) // Set background color to red
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Text with padding from the top and left
            Text(
                text = "Home Page",
                fontSize = 30.sp,
                color = colorResource(id = R.color.black),
                modifier = Modifier
                    .padding(top = 40.dp, start = 20.dp)
                    .align(Alignment.Start)
            )

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
fun HomePreview() {
    Home()
}