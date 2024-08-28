@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.basetabberjetpackcompose

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.basetabberjetpackcompose.R.*
import com.example.basetabberjetpackcompose.ui.theme.BottomBarJCTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomBarJCTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.white) // 수정된 부분
                ) {
                    Text(text = "Setting", fontSize = 30.sp, color = Color.Black)

                    MyBottomAppBar()
                }
            }
        }
    }
}

@Composable
fun MyBottomAppBar() {
    val navigationController = rememberNavController()
    val context = LocalContext.current
    val selected = remember { mutableStateOf(Icons.Default.Home) }
    val title = remember { mutableStateOf("Home") }

    // selectedTabIndex를 상태로 관리
    val selectedTabIndex = remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            AppCenterTopBar(context, title.value)
        },

        bottomBar = {
            BottomAppBar(
                containerColor = colorResource(id = R.color.tabbar)
            ) {
                IconButton(
                    onClick = {
                        selectedTabIndex.value = 0 // 상태로 관리되는 selectedTabIndex
                        selected.value = Icons.Default.Home
                        navigationController.navigate(Screens.Home.screen) {
                            popUpTo(navigationController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                        title.value = "Home"
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    val iconResId = if (selectedTabIndex.value == 0) {
                        R.drawable.icon_home
                    } else {
                        R.drawable.icon_untapped_home
                    }
                    Image(
                        painter = painterResource(id = iconResId),
                        contentDescription = null,
                        modifier = Modifier.size(26.dp)
                    )
                }

                IconButton(
                    onClick = {
                        selectedTabIndex.value = 1
                        selected.value = Icons.Default.Search
                        navigationController.navigate(Screens.ItemList.screen) {
                            popUpTo(navigationController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                        title.value = "Item List"
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    val iconResId = if (selectedTabIndex.value == 1) {
                        R.drawable.icon_list
                    } else {
                        R.drawable.icon_untapped_list
                    }
                    Image(
                        painter = painterResource(id = iconResId),
                        contentDescription = null,
                        modifier = Modifier.size(26.dp)
                    )
                }

                IconButton(
                    onClick = {
                        selectedTabIndex.value = 2
                        selected.value = Icons.Default.Settings
                        navigationController.navigate(Screens.Setting.screen) {
                            popUpTo(navigationController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                        title.value = "Setting"
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    val iconResId = if (selectedTabIndex.value == 2) {
                        R.drawable.icon_setting
                    } else {
                        R.drawable.icon_untapped_setting
                    }
                    Image(
                        painter = painterResource(id = iconResId),
                        contentDescription = null,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navigationController,
            startDestination = Screens.Home.screen,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screens.Home.screen) { Home() }
            composable(Screens.ItemList.screen) { ItemList() }
            composable(Screens.Setting.screen) { Setting() }
        }
    }
}

@Preview
@Composable
fun MyBottomBarPreview() {
    BottomBarJCTheme {
        MyBottomAppBar()
    }
}

@Composable
fun AppTopBar(context: Context) {
    TopAppBar(
        title = { Text(text = "My App",
                color = colorResource(id = R.color.white) // 제목 텍스트 색상 변경
        ) },
        navigationIcon = {
            IconButton(onClick = {
                // 액션 버튼 클릭 시 수행할 작업
                Toast.makeText(context, "Menu clicked", Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = colorResource(id = R.color.white) // 아이콘 색상 변경
                )
            }
        },
        actions = {
            IconButton(onClick = { Toast.makeText(context, "Search button pressed", Toast.LENGTH_SHORT).show() }) {
                Icon(Icons.Filled.Search, contentDescription = "Search",
                    tint = colorResource(id = R.color.white) // 아이콘 색상 변경
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colorResource(id = R.color.tabbar) // 배경색 설정
        )
    )
}

@Composable
fun AppCenterTopBar(context: Context, title: String) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center // 텍스트를 중앙에 배치
            ) {
                Text(
                    text = title,
                    color = Color.White, // 제목 텍스트 색상 변경
                    fontSize = 20.sp // 폰트 크기 설정
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                // 액션 버튼 클릭 시 수행할 작업
                Toast.makeText(context, "Menu clicked", Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = Color.White // 아이콘 색상 변경
                )
            }
        },
        actions = {
            IconButton(onClick = { Toast.makeText(context, "Search button pressed", Toast.LENGTH_SHORT).show() }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.White // 아이콘 색상 변경
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colorResource(id = R.color.tabbar) // 배경색 설정
        )
    )
}