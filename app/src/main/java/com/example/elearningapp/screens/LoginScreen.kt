package com.example.elearningapp.Screens

import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.elearningapp.AuthorizationWithEmail.LogInUser
import com.example.elearningapp.GoogleSignInUtils
import com.example.elearningapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope

    @Composable
    fun LoginScreen(auth: FirebaseAuth,openHomeScreen:()->Unit,scope: CoroutineScope,launcher: ManagedActivityResultLauncher<Intent, ActivityResult>?) {
        val context = LocalContext.current
        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Welcome back!\nSign in to continue!",
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                fontSize = 26.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top=10.dp, bottom = 60.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, bottom = 15.dp)
                    .height(60.dp)
                    .clickable(onClick = {

                    }),
                colors = CardDefaults.cardColors(Color(0xFFDBDEDE)),
                shape = RoundedCornerShape(5.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize().clickable(
                        onClick = {
                            //login with google
                            GoogleSignInUtils.doGoogleSignIn(
                                context = context,
                                scope = scope,
                                launcher = launcher,
                                login = {
                                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                                    openHomeScreen()
                                }
                            )
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp),
                        alignment = Alignment.CenterStart
                    )
                    Text(
                        text = "Log in with Google",
                        modifier = Modifier.padding(start = 10.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Text(text = "or",color = Color.Gray)

            OutlinedTextField(
                value = email, onValueChange = {
                    email = it
                },
                label = { Text("email") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                ),
                modifier = Modifier.padding(top=15.dp)
            )

            OutlinedTextField(
                value = password, onValueChange = {
                    password = it
                },
                label = { Text("password") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                )
            )

            Button(
                onClick = {
                        LogInUser(auth,email,password,context,openHomeScreen)
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(start = 35.dp, end = 35.dp, top = 70.dp)
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF265AE8),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Log in", fontSize = 16.sp)
            }
        }
    }