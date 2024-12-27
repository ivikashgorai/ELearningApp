package com.example.elearningapp

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.elearningapp.Screens.HomeScreen
import com.example.elearningapp.Screens.LoginScreen
import com.example.elearningapp.Screens.OnBoardingScreen
import com.example.elearningapp.Screens.SignUpDetailsScreen
import com.example.elearningapp.Screens.SignUpScreen
import com.example.elearningapp.ViewModels.ViewModelFactory
import com.example.elearningapp.ViewModels.CourseViewModel
import com.example.elearningapp.database.Course
import com.example.elearningapp.database.CourseRepo
import com.example.elearningapp.entity.CourseDatabase
import com.google.firebase.auth.FirebaseAuth
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val CourseDatabase by lazy {
                Room.databaseBuilder(this.applicationContext, CourseDatabase::class.java, "course_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            val CourseRepo by lazy{
                CourseRepo(CourseDatabase.CourseDAO())
            }
            val CourseViewModel : CourseViewModel by viewModels{ // pass the factory we created
                ViewModelFactory(CourseRepo)
            }
            MyApp(CourseViewModel)
        }
    }
}

@Composable
fun MyApp(viewModel: CourseViewModel) {
    val navController = rememberNavController()
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
        GoogleSignInUtils.doGoogleSignIn(
            context = context,
            scope = scope,
            launcher = null,
            login = {
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
            }
        )

    }
//    using Room data base here
    var startDes: String = "onboarding"
    if (auth.currentUser != null) {
        startDes = "home"
    }

    NavHost(navController = navController, startDestination = startDes) {
        composable("home") {
            HomeScreen(auth,SignOutGoToOnBoarding = {
                navController.navigate("onboarding") {
                    popUpTo(0)
                }
            },
                viewModel)
        }
        composable("onboarding") {
            OnBoardingScreen(goToLoginScreen = {
                navController.navigate("login")
            },
                goToSignUpScreen = {
                    navController.navigate("signup")
                },
                goToHome = {
                    navController.navigate("home")
                }
            )
        }
        composable("login") {
            LoginScreen(auth, openHomeScreen = {
                navController.navigate("home"){
                    popUpTo(0)
                }
            },
                scope,launcher)
        }
        composable("signup") {
            SignUpScreen(goToHomeScreen = {
                navController.navigate("home"){
                    popUpTo(0)
                }
            },goToLoginScreen = {
                navController.navigate("login")
            },
                goToSignUpDetails = {
                    navController.navigate("signupdetails")
                },
                scope,launcher)
        }
        composable("signupdetails") {
            SignUpDetailsScreen(auth, signSuccessGoToLoginScreen = {
                navController.navigate("login") {
                    popUpTo(0)
                }
            },
                goToBackSignUpScreen = {
                    navController.navigate("signup") {
                        popUpTo(0)
                    }
                })
        }
    }
}

@Composable
fun CourseCard(course: Course) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 40.dp, start = 10.dp, end = 10.dp), shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)) {
            Image(
                painter = painterResource(course.courseImage),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(start = 10.dp, end = 10.dp, top = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.visit),
                    contentDescription = null,
                )
                Text(text = course.interested, fontSize = 12.sp)
            }
            Row(
                modifier = Modifier
                    .border(
                        width = 0.5.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(3.dp)
                    )
                    .padding(end = 5.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(bottom = 5.dp),
                )
                Text(text = course.rating)
            }
        }
        Column(modifier = Modifier.background(Color.White)) {
            Text(
                text = course.courseName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                color = Color.Black
            )
            Row(modifier = Modifier.padding(start = 10.dp, top = 30.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.course_leve),
                    contentDescription = null,
                    Modifier
                        .padding(top = 2.dp)
                        .size(12.dp)
                )
                Text(
                    text = course.courseLevel,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .padding(bottom = 25.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(red = 128, green = 185, blue = 142, alpha = 35))
                    .padding(top = 20.dp, bottom = 20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 13.dp)
                        .border(
                            width = 2.dp,
                            color = Color(0xff275e23),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .clickable(
                            onClick = {

                            }
                        )
                        .padding(vertical = 10.dp), contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Explore",
                        color = Color(0xff275e23),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

    }
}