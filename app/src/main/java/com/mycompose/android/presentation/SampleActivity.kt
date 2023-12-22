package com.mycompose.android.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class SampleActivity :AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val booleanPayment: Boolean = showMessage(true)
        // returning boolean value
        val stringMessage: String = showMessage(true)
        //returning string value
        println("Your Payment: $booleanPayment \nMessage: $stringMessage")


    }


    inline fun <reified T : Any> String.convertToObject(): T? {
        return Gson()?.fromJson(this, T::class.java)
    }


    // Without reified keyword and inline function
    inline fun <reified T> showMessage(payment: Boolean): T {
        return when (T::class) {
            Boolean::class -> payment as T
            String::class -> "Sorry!! You don’t have enough balance to proceed" as T
            else -> "Please enter valid type" as T
        }
    }


}