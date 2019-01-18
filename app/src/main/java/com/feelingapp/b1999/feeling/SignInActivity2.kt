package com.feelingapp.b1999.feeling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat

class SignInActivity2 : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in2)
        email_mode()
    }

    public fun email_mode() {
        var layout: ConstraintLayout = findViewById(R.id.sign_in_activity)
        layout.background = ResourcesCompat.getDrawable(resources, R.color.mainBlue, null)
        textView.text = "Email"
    }

    private fun password_mode() {
        var layout: ConstraintLayout = findViewById(R.id.sign_in_activity)
        layout.background = ResourcesCompat.getDrawable(resources, R.color.mainBlue, null)
        textView.text = "Email"
    }

}
