@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.basetabberjetpackcompose
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomBarJCTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.white)
                ) {
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
                containerColor = colorResource(id = R.color.tabbar),

            ) {
                // Home Button
                IconButton(
                    onClick = {
                        selectedTabIndex.value = 0
                        selected.value = Icons.Default.Home
                        navigationController.navigate(Screens.Home.screen) {
                            popUpTo(navigationController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                        title.value = "Home"
                    },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight() // IconButton을 BottomAppBar의 높이에 맞게 설정
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center, // Column의 수직 정렬을 중앙으로 설정
                        modifier = Modifier.fillMaxHeight().fillMaxWidth() // Column이 IconButton의 전체 높이, 전체 넓이를 차지하도록 설정
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
                        Text(
                            text = "Home",
                            fontSize = 12.sp,
                            color = if (selectedTabIndex.value == 0) Color.White else Color.Gray
                        )
                    }
                }

                // Item List Button
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
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight() // IconButton을 BottomAppBar의 높이에 맞게 설정
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center, // Column의 수직 정렬을 중앙으로 설정
                        modifier = Modifier.fillMaxHeight().fillMaxWidth() // Column이 IconButton의 전체 높이, 전체 넓이를 차지하도록 설정
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
                        Text(
                            text = "Item List",
                            fontSize = 12.sp,
                            color = if (selectedTabIndex.value == 1) Color.White else Color.Gray
                        )
                    }
                }

                // Settings Button
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
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight() // IconButton을 BottomAppBar의 높이에 맞게 설정
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center, // Column의 수직 정렬을 중앙으로 설정
                        modifier = Modifier.fillMaxHeight() // Column이 IconButton의 전체 높이를 차지하도록 설정
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
                        Text(
                            text = "Setting",
                            fontSize = 12.sp,
                            color = if (selectedTabIndex.value == 2) Color.White else Color.Gray
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navigationController,
            startDestination = Screens.Home.screen,
            modifier = Modifier
                .fillMaxSize().padding(paddingValues)
                .padding(bottom = 56.dp) // BottomAppBar 높이만큼 여백 추가
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
        title = {
            Text(
                text = "My App",
                color = colorResource(id = R.color.white)
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                Toast.makeText(context, "Menu clicked", Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = colorResource(id = R.color.white)
                )
            }
        },
        actions = {
            IconButton(onClick = { Toast.makeText(context, "Search button pressed", Toast.LENGTH_SHORT).show() }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = colorResource(id = R.color.white)
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colorResource(id = R.color.tabbar)
        )
    )
}

@Composable
fun AppCenterTopBar(context: Context, title: String) {
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
            IconButton(onClick = {
                Toast.makeText(context, "Menu clicked", Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = colorResource(id = R.color.black)
                )
            }
        },
        actions = {
            IconButton(onClick = { Toast.makeText(context, "Search button pressed", Toast.LENGTH_SHORT).show() }) {
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