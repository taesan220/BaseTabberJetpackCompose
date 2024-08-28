package com.example.basetabberjetpackcompose

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Setting(){

    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 상단에서 40dp 떨어지게, 왼쪽에서 20dp Text 배치
            Text(
                text = "Setting Page",
                fontSize = 30.sp,
                color = colorResource(id = R.color.black),
                modifier = Modifier.padding(top = 40.dp, start = 20.dp).align(Alignment.Start) // 상단 패딩 추가
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
                    Icon(Icons.Default.Add, contentDescription = null, tint = colorResource(id = R.color.tabbar)) // 수정된 부분
                }
            }
        }
    }
}