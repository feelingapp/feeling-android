package com.feelingapp.b1999.feeling

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        super.onCreate(savedInstanceState)
        return inflater.inflate(R.layout.sign_in_fragment, container, false)
    }
}