package com.example.myfirstapp.screens.demo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapp.R
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button

@Composable
fun firstScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to my app.",
            fontSize = 32.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "App image"
        )

        //row with two buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Button(onClick = {}) {
                Text(
                    text = "LOGIN"
                )
            }
            Button(onClick = {}) {
                Text(
                    text = "REGISTER"
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun firstscreenpreview(){
    firstScreen()
}