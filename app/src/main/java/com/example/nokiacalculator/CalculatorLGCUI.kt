package com.example.nokiacalculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CalcScreenAndButton(){
    Column(Modifier.fillMaxWidth()
        .fillMaxHeight(0.55f)
    ){
        Column(Modifier.fillMaxSize()
            .weight(3f)
        ){

        }
        Column(Modifier.fillMaxSize()
            .weight(6f)
        ){

        }
        Column(Modifier.fillMaxSize()
            .weight(2f)
        ){

        }
        Column(Modifier.fillMaxSize()
            .weight(2f)
        ){

        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCalculator(){
    Column {
        CalcScreenAndButton()
        Box(Modifier.fillMaxSize())
    }
}