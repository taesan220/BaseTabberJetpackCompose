package com.example.sidemenupractice2.navigationDrawer

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import com.example.basetabberjetpackcompose.navigation.NavigationItemModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomModalNavigationDrawer(
    drawerState: DrawerState,
    //items: List<NavigationItemModel>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerContent = {
            CustomNavigationDrawer(
                drawerState = drawerState,
                selectedItemIndex = selectedItemIndex,
                onItemSelected = onItemSelected
            )
        },
        drawerState = drawerState,
        content = content
    )
}