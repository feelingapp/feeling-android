package com.feelingapp.b1999.feeling

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //var signInFragment: Fragment = findViewById(R.id.sign_in_fragment)
        var emailSignInButton: Button = findViewById(R.id.email_login_button)
        emailSignInButton.setOnClickListener {
            emailSignIn()
        }

        var facebookSignInButton: Button = findViewById(R.id.facebook_login_button)
        facebookSignInButton.setOnClickListener {
            facebookSignIn()
        }

    }

    private fun emailSignIn() {
        var intent = Intent(this, SignInActivity2::class.java)
        startActivity(intent)
    }

    private fun facebookSignIn() {}


}
