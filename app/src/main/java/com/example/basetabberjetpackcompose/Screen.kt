package com.example.basetabberjetpackcompose

sealed class Screens (val screen: String) {

    data object Home: Screens("Home")
    data object ItemList: Screens("Item List")
    data object Setting: Screens("Setting")
}