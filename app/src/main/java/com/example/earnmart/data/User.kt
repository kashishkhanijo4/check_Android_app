package com.example.earnmart.data

data class User(
    val firstname:String,
    val lastName:String,
    val email:String,
    var imagePath:String = ""
){
    constructor():this("","","","")
}
