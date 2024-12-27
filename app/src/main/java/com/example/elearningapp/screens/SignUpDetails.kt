package com.example.elearningapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elearningapp.R
import com.example.elearningapp.AuthorizationWithEmail.SignUpUser
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SignUpDetailsScreen(auth: FirebaseAuth,signSuccessGoToLoginScreen:()->Unit,goToBackSignUpScreen:()->Unit) {
    val context = LocalContext.current
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 50.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, bottom = 40.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = null,
                modifier = Modifier.size(20.dp).clickable(onClick = {
                    goToBackSignUpScreen()
                })
            )
        }
        Text(
            text = "Enter Your details",
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 30.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            placeholder = {
                Text("Email address")
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            modifier = Modifier.padding(start = 20.dp)
        )
        HorizontalDivider(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 15.dp),
            thickness = 1.dp,
            color = Color.Gray
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            placeholder = {
                Text("password")
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            modifier = Modifier.padding(start = 20.dp)
        )
        HorizontalDivider(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 15.dp),
            thickness = 1.dp,
            color = Color.Gray
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
            },
            placeholder = {
                Text("Confirm Password")
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            modifier = Modifier.padding(start = 20.dp)
        )
        HorizontalDivider(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 100.dp),
            thickness = 1.dp,
            color = Color.Gray
        )

        Button(
            onClick = {
                SignUpUser(auth,email,password,context,signSuccessGoToLoginScreen)
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 0.dp)
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF265AE8),
                contentColor = Color.White
            )
        ) {
            Text(text = "Continue", fontSize = 16.sp)
        }

    }
}