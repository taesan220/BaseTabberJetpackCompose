import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.basetabberjetpackcompose.R
import com.example.basetabberjetpackcompose.new_screen.NewScreen

@Composable
fun Home() {
    val context = LocalContext.current
    var showNewScreen by remember { mutableStateOf(false) }

    // 최상위 Box로 감싸서 전체 화면을 덮을 수 있도록 처리
    Box(modifier = Modifier.fillMaxSize()) {

        // 기존 화면
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp), // 하단 탭바 고려
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Home Page",
                fontSize = 30.sp,
                color = colorResource(id = R.color.black),
                modifier = Modifier.padding(top = 40.dp, start = 20.dp)
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                FloatingActionButton(onClick = {
                    Toast.makeText(context, "Open new screen", Toast.LENGTH_SHORT).show()
                    showNewScreen = true // 새로운 화면 표시
                }) {
                    Icon(Icons.Default.Add, contentDescription = null, tint = colorResource(id = R.color.tabbar))
                }
            }
        }

        // 새로운 화면이 전체 화면을 덮도록 처리
        if (showNewScreen) {
            Box(
                modifier = Modifier
                    .fillMaxSize() // 전체 화면을 덮음
                    .padding(bottom = 0.dp) // 하단 탭바 위로 올라오도록 설정
            ) {
                // 애니메이션 처리
                AnimatedVisibility(
                    visible = showNewScreen,
                    enter = slideInHorizontally(initialOffsetX = { it }),
                    exit = slideOutHorizontally(targetOffsetX = { it }),
                    modifier = Modifier.fillMaxSize() // 전체 화면을 덮도록 설정
                ) {
                    NewScreen(onBack = { showNewScreen = false }) // '뒤로 가기' 핸들러 추가
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home()
}
