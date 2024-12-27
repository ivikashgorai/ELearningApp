package com.example.elearningapp.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elearningapp.R

@Composable
fun OnBoardingScreen(goToLoginScreen: () -> Unit, goToSignUpScreen: () -> Unit,goToHome:()->Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 30.dp, bottom = 20.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Text(
                text = "Skip",
                color = Color(0xFF265AE8),
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
                modifier = Modifier.clickable(onClick = {
                        goToHome()
                })
            )
        }

        ScrollablePager()

        Button(
            onClick = {
                goToSignUpScreen()
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 15.dp)
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF265AE8),
                contentColor = Color.White
            )
        ) {
            Text(text = "Register", fontSize = 16.sp)
        }

        Button(
            onClick = {
                goToLoginScreen()
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 25.dp)
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0xFF265AE8),
                containerColor = Color.White
            ),
            border = BorderStroke(width = 0.2.dp, color = Color.Gray)
        ) {
            Text(text = "Log in")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollablePager() {
    val images = listOf(
        // Replace with your actual drawable resource IDs
        R.drawable.onboard2,
        R.drawable.onboard1,
        R.drawable.onboard3
    )
    val headings = listOf(
        "Better way to learning\nis calling you!",
        "Learn anytime, anywhere\nat your own pace!",
        "Expand your skills\nwith top courses!"
    )
    val descriptions = listOf(
        "Immerse yourself in a vibrant learning experience that empowers you to grow.",
        "Get access to high-quality resources and a personalized learning experience.",
        "Join a community of learners and unlock new opportunities."
    )
    val pagerState = rememberPagerState(pageCount = {
        3
    })
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) { page ->
        // Our page content
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = "onboard1",
                modifier = Modifier
                    .width(210.dp)
            )

            Text(
                text = headings[page],
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                modifier = Modifier.padding(top = 40.dp)
            )

            Text(
                text = descriptions[page],
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(300.dp)
                    .padding(top = 20.dp),
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(top = 0.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) Color.Gray else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(6.dp)
            )
        }
    }
}