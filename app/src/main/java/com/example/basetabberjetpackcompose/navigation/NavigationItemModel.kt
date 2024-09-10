package com.example.basetabberjetpackcompose.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItemModel (
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val badgecount: Int? = null
)
