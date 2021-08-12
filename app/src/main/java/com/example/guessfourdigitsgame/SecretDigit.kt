package com.example.guessfourdigitsgame

import android.util.Log

class SecretDigit {

    private var secret = (0..9).toList()

    var fourDigit = secret.shuffled().take(4).joinToString(separator = "")

    private var count = 0

    private val TAG = "Ray_Secret_Number"

    constructor() {
        Log.d(TAG, "The secret number is: " + fourDigit)
    }

    fun getSecret():String {
        return fourDigit
    }

    fun getCount(): Int{
        return count
    }

    fun validate(guess: String): String {
        var a: Int = 0
        var b: Int = 0

        count++
        for(i in 0..3) {
            if (guess[i] in fourDigit){
                if (guess[i] == fourDigit[i]) {
                    a += 1
                } else {
                    b += 1
                }
            }

        }
        return "${a}A${b}B"
    }
}