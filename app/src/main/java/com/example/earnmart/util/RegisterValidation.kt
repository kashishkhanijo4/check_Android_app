package com.example.earnmart.util

sealed class RegisterValidation {
    object Success:RegisterValidation()
    data class Failed(val message : String): RegisterValidation()

}

data class RegisterFieldState(
    val firstName:RegisterValidation, // changed
    val Email:RegisterValidation,
    val password:RegisterValidation
)