package com.example.earnmart.util

import android.util.Patterns

fun validatefirstName(firstname:String):RegisterValidation{ // changed
    if (firstname.isEmpty()){
        return RegisterValidation.Failed("First Name cannot be Empty")
    }
    return RegisterValidation.Success
}

fun validateEmail(email:String):RegisterValidation{
    if (email.isEmpty()){
        return RegisterValidation.Failed("Email cannot be Empty")
    }
    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
        return RegisterValidation.Failed("Wrong Email")
    }
    return RegisterValidation.Success
}

fun validatePassword(password:String):RegisterValidation{
    if (password.isEmpty()){
        return RegisterValidation.Failed("Password cannot be Empty")
    }
    if (password.length<6){
        return RegisterValidation.Failed("Password should contains  6 char")
    }
    return RegisterValidation.Success
}