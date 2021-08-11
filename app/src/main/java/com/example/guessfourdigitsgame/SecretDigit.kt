package com.example.guessfourdigitsgame

import android.util.Log

class SecretDigit {

    private var secret = (0..9).toList()

    var fourDigit = secret.shuffled().take(4).joinToString(separator = "")

    private var count = 0

    private val TAG = "Ray_Secret_Number"

    constructor() {
        Log.d(TAG, "The secret number is: " + secret)
    }

    fun validate(guess: String): String {
        count++
        return "4A0B"
    }
}