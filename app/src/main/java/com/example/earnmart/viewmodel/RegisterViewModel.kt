package com.example.earnmart.viewmodel

import androidx.lifecycle.ViewModel
import com.example.earnmart.data.User
import com.example.earnmart.util.Constants.USER_COLLECTION
import com.example.earnmart.util.RegisterFieldState
import com.example.earnmart.util.RegisterValidation
import com.example.earnmart.util.Resource
import com.example.earnmart.util.validateEmail
import com.example.earnmart.util.validatePassword
import com.example.earnmart.util.validatefirstName
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val db: FirebaseFirestore
) : ViewModel() {

    private val _register = MutableStateFlow<Resource<User>>(Resource.Unspecified())
    val register: Flow<Resource<User>> = _register

    private val _validation = Channel<RegisterFieldState>()
    val validation = _validation.receiveAsFlow()

    fun createAccountWithEmailAndPassword(firstname:String, user: User, password: String) { // changed
        if (checkValidation(firstname,user, password)) { //changed

            runBlocking {
                _register.emit(Resource.Loading())
            }
            firebaseAuth.createUserWithEmailAndPassword(user.email, password)
                .addOnSuccessListener {
                    it.user?.let {
                        saveUserInfo(it.uid, user)
                    }

                }.addOnFailureListener {
                    _register.value = Resource.Error(it.message.toString())


                }
        }else {
            // Added first name validation result in RegisterFieldState
            val registerFieldState = RegisterFieldState(
                firstName = validatefirstName(firstname),  // Validates first name
                Email = validateEmail(user.email),
                password = validatePassword(password)
            )
            runBlocking {
                _validation.send(registerFieldState)
            }
        }
//        else {
//            val registerFieldState = RegisterFieldState(
//                validatefirstName(firstname)  // changed
//                validateEmail(user.email),
//                validatePassword(password)
//            )
//            runBlocking {
//                _validation.send(registerFieldState)
//            }
//        }
    }

    private fun saveUserInfo(userUid: String, user: User) {
        db.collection(USER_COLLECTION)
            .document(userUid)
            .set(user)
            .addOnSuccessListener {
                _register.value = Resource.Success(user)
            }
            .addOnFailureListener {
                _register.value = Resource.Error(it.message.toString())

            }
    }

    private fun checkValidation(firstname: String, user: User, password: String): Boolean {
        val firstNameValidation = validatefirstName(firstname) // changed
        val emailValidation = validateEmail(user.email)
        val passwordValidation = validatePassword(password)
        val shouldRegister = firstNameValidation is RegisterValidation.Success && // changed
                emailValidation is RegisterValidation.Success &&
                passwordValidation is RegisterValidation.Success

        return shouldRegister
    }
}