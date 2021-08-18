package com.example.guessfourdigitsgame

import android.util.Log
import java.util.*

class SecretDigit {

    var answer = ""

    var input = ""

    var guess_count = 0

    private var a = 0
    private var b = 0

//    產生四位數字
    fun generateSecret() {
       answer = (0..9)
           .toMutableList()
           .shuffled()
           .take(4)
           .joinToString("")

        Log.d("SecretNumber", "generateSecret: $answer")
    }

//    return xAxB
    fun validate(): String {

        val match = answer.filter {it in input}
        a = 0
        b = 0
        guess_count++

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
            if (answer.indexOf(it) == input.indexOf(it))
                a++
            else
                b++
        }
    }
}