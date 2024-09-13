package com.example.basetabberjetpackcompose.tabbar_screen

sealed class Screens (val screen: String) {

    data object Home: Screens("Home")
    data object ItemList: Screens("Item List")
    data object Setting: Screens("Setting")
}