package com.example.elearningapp.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.elearningapp.CourseCard
import com.example.elearningapp.ViewModels.CourseViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(auth: FirebaseAuth, SignOutGoToOnBoarding:()->Unit, viewModel:CourseViewModel){

        // Screen content
        val courseList = viewModel.courseList.collectAsState()
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                items(courseList.value) { course ->
                    CourseCard(course)
                }
            }
            Box(modifier = Modifier.fillMaxSize().padding(end=20.dp, bottom = 30.dp), contentAlignment = Alignment.BottomEnd) {
                Button(onClick = {
                    auth.signOut()
                    SignOutGoToOnBoarding()
                }) {
                    Text(text = "Sign Out")
                }
            }
        }
}
