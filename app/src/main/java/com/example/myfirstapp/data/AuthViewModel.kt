package com.example.myfirstapp.data

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.myfirstapp.navigation.ROUTE_DASHBOARD
import com.example.myfirstapp.navigation.ROUTE_LOGIN
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel(var navController: NavHostController, var context: Context) {

    val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    fun signup(name: String, email: String, pass: String, confpass: String) {
        if (name.isBlank() || email.isBlank() || pass.isBlank() || confpass.isBlank()) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confpass) {
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val currentUser = mAuth.currentUser
                val userId = currentUser?.uid
                val userData = User(name, email, userId ?: "")
                val reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId!!)
                reference.setValue(userData).addOnCompleteListener { task2 ->
                    if (task2.isSuccessful) {
                        Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
                        navController.navigate(ROUTE_LOGIN)
                    } else {
                        Toast.makeText(context, task2.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun login(email: String, pass: String) {
        if (email.isBlank() || pass.isBlank()) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                navController.navigate(ROUTE_DASHBOARD)
            } else {
                Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun logout() {
        mAuth.signOut()
        navController.navigate(ROUTE_LOGIN)
    }

    fun getCurrentUserName(onResult: (String) -> Unit) {
        val userId = mAuth.currentUser?.uid
        if (userId != null) {
            val reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId)
            reference.get().addOnSuccessListener {
                val name = it.child("name").value.toString()
                onResult(name)
            }.addOnFailureListener {
                onResult("Error fetching name")
            }
        } else {
            onResult("Not logged in")
        }
    }
}

data class User(
    var name: String = "",
    var email: String = "",
    var userid: String = ""
)
