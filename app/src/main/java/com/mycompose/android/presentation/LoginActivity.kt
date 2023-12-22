package com.mycompose.android.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.mycompose.android.presentation.base.BaseActivity
import com.mycompose.app.R

class LoginActivity : BaseActivity() {

    @SuppressLint("UnrememberedMutableState")
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.bg_blue)),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(100.dp))

                var emailLength by remember { mutableIntStateOf(0) }

                /* if (emailLength == 0)
                 else if (emailLength.)
                 else*/

                val emailId = remember { mutableStateOf(TextFieldValue()) }
                val password = remember { mutableStateOf(TextFieldValue()) }
                var passwordVisible = rememberSaveable(mutableStateOf(false)) {}
                var passwordFocus = false

                Spacer(modifier = Modifier.height(20.dp))

                //Email id
                OutlinedTextField(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 40.dp, vertical = 10.dp)
                    .onFocusChanged {
                        if (it.isFocused) {
                            emailLength = 1
                        }
                    },
                    label = { Text(text = getString(R.string.app_name)) },
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.white),
                        unfocusedBorderColor = colorResource(id = R.color.white),
                        cursorColor = colorResource(id = R.color.white)
                    ),
                    value = emailId.value,
                    onValueChange = {
                        emailId.value = it
                    })


                //Password id
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 40.dp, vertical = 10.dp)
                        .onFocusChanged {
                            if (it.isFocused) {
                                passwordFocus = true
                                emailLength = 60
                            }
                        },
                    label = {Text(text = getString(R.string.app_name))},
                    placeholder ={Text(getString(R.string.password))},
                    singleLine = true,
                    value = password.value,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.white),
                        unfocusedBorderColor = colorResource(id = R.color.white),
                        cursorColor = colorResource(id = R.color.white),
                        /*textColor = colorResource(id = R.color.white),
                        placeholderColor = colorResource(id = R.color.white)*/
                    ),

                    onValueChange = {
            password.value = it
                    })


            }
        }


    }

}