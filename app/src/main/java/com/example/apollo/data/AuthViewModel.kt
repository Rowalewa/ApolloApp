@file:Suppress("DEPRECATION")

package com.example.apollo.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.apollo.models.User
import com.example.apollo.navigation.ROUTE_HOME
import com.example.apollo.navigation.ROUTE_LOGIN
import com.example.apollo.navigation.ROUTE_REGISTER
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

// back-end part of the application (view model)
class AuthViewModel (
    var navController: NavController,
    var context: Context
) {
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var progress: ProgressDialog = ProgressDialog(context)

    init {
        progress.setTitle("Loading...\uD83D\uDEE0\uFE0F ")
        progress.setMessage("Please wait for a moment")
    }

    fun signup(
        email: String,
        pass: String,
        confpass: String,
    ) {
        progress.show()
        if (email.isBlank() || pass.isBlank() || confpass.isBlank()) {
            progress.dismiss()
            Toast.makeText(context, "Email and Password should not be blank", Toast.LENGTH_LONG).show()
            return
        }else if (pass != confpass) {
            progress.dismiss()
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
            navController.navigate(ROUTE_REGISTER)
            return
        }else {
            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userdata = User(email, pass, mAuth.currentUser!!.uid)
                    val regRef = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.currentUser!!.uid)
                    regRef.setValue(userdata).addOnCompleteListener { dataTask ->
                        if (dataTask.isSuccessful) {
                            progress.dismiss()
                            Toast.makeText(context, "Registered Successfully", Toast.LENGTH_LONG).show()
                            navController.navigate(ROUTE_HOME)
                        } else {
                            progress.dismiss()
                            Toast.makeText(context, "${dataTask.exception!!.message}", Toast.LENGTH_LONG).show()
//                            navController.navigate(ROUTE_LOGIN)
                        }
                    }
                } else {
                    progress.dismiss()
                    val errorMessage = task.exception?.message ?: "Could not Register, Retry"
                    if (errorMessage.contains("email address is already in use")) {
                        Toast.makeText(context, "Email address is already registered", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                    }
                    navController.navigate(ROUTE_REGISTER)
                }
            }
        }
    }


    fun login(
        email: String,
        pass: String
    ){
        progress.show()
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context,"Successfully logged in", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_HOME)
            } else {
                Toast.makeText(context,"${it.exception!!.message}", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_LOGIN)
            }
        }
    }

    fun logout(){
        mAuth.signOut()
        navController.navigate(ROUTE_LOGIN)
        Toast.makeText(context, "Successfully logged out", Toast.LENGTH_LONG).show()
    }

    fun isloggedin(): Boolean{
        return mAuth.currentUser != null
    }

}