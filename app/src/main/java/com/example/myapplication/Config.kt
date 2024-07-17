package com.example.myapplication


data class Config(
    var rotation: Int = 0,
    var isCAP: Boolean = true,
    var margin: Int = 5,
    var weight: Float = 80f,
    var height: Float = 80f,
    var enableBorder: Boolean = false
)