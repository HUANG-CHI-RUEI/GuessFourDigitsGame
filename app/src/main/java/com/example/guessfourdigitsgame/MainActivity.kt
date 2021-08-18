package com.example.guessfourdigitsgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(){

    var secretNumber = SecretDigit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_main)
        secretNumber.generateSecret()
    }


}

