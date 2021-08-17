package com.example.guessfourdigitsgame

import android.util.Log
import java.util.*

class SecretDigit {

    private var secret = ""

    var input = ""

    var a = 0
    var b = 0

//    產生四位數字
    fun generateSecret() {
        val numbers = (0..9).toMutableList()

        (0..3).forEach { _ ->
            val number = Random().nextInt(numbers.size)
            secret += numbers.removeAt(number).toString()
        }

        Log.d("SecretNumber", "generateSecret: $secret")
    }

//    return xAxB
    fun validate(): String {

        val match = secret.filter {it in input}
        a = 0
        b = 0

        when(match.length) {
            4 -> calc(match)
            3 -> calc(match)
            2 -> calc(match)
            1 -> calc(match)
            else -> {
                a = 0
                b = 0
            }
        }

        return "${a}A${b}B"
    }

    private fun calc(match: String) {
        match.forEach {
            if (secret.indexOf(it) == input.indexOf(it))
                a++
            else
                b++
        }
    }
}