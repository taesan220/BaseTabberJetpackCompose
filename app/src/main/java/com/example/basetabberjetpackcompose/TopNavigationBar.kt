package com.example.basetabberjetpackcompose

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppCenterTopBar(
    title: String,
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = colorResource(id = R.color.black),
                    fontSize = 20.sp
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = colorResource(id = R.color.black)
                )
            }
        },
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = colorResource(id = R.color.black)
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colorResource(id = R.color.top_navigation_bar)
        )
    )
}

//@Composable
//fun AppTopBar(context: Context) {
//    TopAppBar(
//        title = {
//            Text(
//                text = "My App",
//                color = colorResource(id = R.color.white)
//            )
//        },
//        navigationIcon = {
//            IconButton(onClick = {
//                Toast.makeText(context, "Menu clicked", Toast.LENGTH_SHORT).show()
//            }) {
//                Icon(
//                    imageVector = Icons.Filled.Menu,
//                    contentDescription = "Menu",
//                    tint = colorResource(id = R.color.white)
//                )
//            }
//        },
//        actions = {
//            IconButton(onClick = { Toast.makeText(context, "Search button pressed", Toast.LENGTH_SHORT).show() }) {
//                Icon(
//                    imageVector = Icons.Filled.Search,
//                    contentDescription = "Search",
//                    tint = colorResource(id = R.color.white)
//                )
//            }
//        },
//        colors = TopAppBarDefaults.mediumTopAppBarColors(
//            containerColor = colorResource(id = R.color.tabbar)
//        )
//    )
//}
