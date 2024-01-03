package com.mycompose.android.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.mycompose.android.presentation.base.BaseActivity
import com.mycompose.android.presentation.product.ProductListActivity
import com.mycompose.app.R
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity() {

    @SuppressLint("UnrememberedMutableState")
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
                //
            val scope = rememberCoroutineScope()
            val snackbarHostState = remember { SnackbarHostState() }
            Scaffold(
                snackbarHost = {
                    SnackbarHost(hostState = snackbarHostState)
                }) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(colorResource(id = R.color.white)),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    val currentContext = LocalContext.current



                    Spacer(modifier = Modifier.height(100.dp))

                    var emailLength by remember { mutableIntStateOf(0) }

                    /* if (emailLength == 0)
                     else if (emailLength.)
                     else*/

                    val emailId = rememberSaveable() { mutableStateOf(TextFieldValue()) }
                    val password = remember { mutableStateOf(TextFieldValue()) }
                    var passwordVisible by rememberSaveable {mutableStateOf(false)}
                    var passwordFocus = false

                    Spacer(modifier = Modifier.height(20.dp))

                    //Email id
                    OutlinedTextField(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp, vertical = 10.dp)
                        .onFocusChanged {
                            if (it.isFocused) {
                                emailLength = 1
                            }
                        },
                        label = { Text(text = getString(R.string.app_name)) },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = colorResource(id = R.color.background),
                            unfocusedBorderColor = colorResource(id = R.color.background),
                            cursorColor = colorResource(id = R.color.background)
                        ),
                        value = emailId.value,
                        onValueChange = {
                            emailId.value = it
                            emailLength = it.text.length
                        })


                    //Password id
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 40.dp, vertical = 10.dp)
                            .onFocusChanged {
                                if (it.isFocused) {
                                    passwordFocus = true
                                    emailLength = 200
                                }
                            },
                        label = { Text(text = getString(R.string.password)) },
                        placeholder = { Text(getString(R.string.password)) },
                        singleLine = true,
                        value = password.value,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = colorResource(id = R.color.background),
                            unfocusedBorderColor = colorResource(id = R.color.background),
                            cursorColor = colorResource(id = R.color.background)
                        ),
                        onValueChange = { password.value = it },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            val image: ImageVector
                            val description: String
                            if (passwordVisible) {
                                image = Icons.Filled.Visibility
                                description = "Hide password"
                                if (!passwordFocus) {
                                    passwordFocus = true
                                    emailLength = 0
                                }

                            } else {
                                image = Icons.Filled.VisibilityOff
                                description = "Show password"
                                if (passwordFocus) {
                                    passwordFocus = false
                                    emailLength = 200
                                }
                            }
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(imageVector = image, description)
                            }
                        }
                    )

                    //
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 20.dp),
                        onClick ={

                            if(emailLength<5) {
                                scope.launch {
                                    snackbarHostState
                                        .showSnackbar(message = "Enter valid email",
                                            actionLabel = "Action", duration = SnackbarDuration.Indefinite)
                                }
                            }
                            else if (password.value.text.length<4) {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "Password should be less than 4 characters",
                                        duration = SnackbarDuration.Long)
                                }
                            } else {
                                startActivity(Intent(currentContext, ProductListActivity::class.java))
                                finish()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(colorResource(id =R.color.background)),
                        shape = RoundedCornerShape(50.dp),
                    ){
                        Text(text = getString(R.string.emailId),
                            color = colorResource(id = R.color.white))
                    }


                }

                //

                /////////////////////
            }
            //
        }
//
    }

}






