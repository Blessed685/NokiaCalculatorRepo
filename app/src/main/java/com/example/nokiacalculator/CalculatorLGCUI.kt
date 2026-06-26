package com.example.nokiacalculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CalcScreenAndButton(){
    Column(Modifier.fillMaxSize()){
        Column(Modifier.fillMaxWidth()
            .fillMaxHeight(0.55f)
        ){
            Row(Modifier.fillMaxSize()
                .weight(3f)
            ){
                // Display -> operator on the left side, input on the right side
            }
            Column(Modifier.fillMaxSize()
                .weight(6f)
            ){
                // Center circle with each of the four operators on each edge
            }
            Row(Modifier.fillMaxSize()
                .weight(2f)
            ){
                // "Equals" on the left side, "Clear" on the right side
            }
            Column(Modifier.fillMaxSize()
                .weight(2f)
            ){
                // Input field which allows only numbers, decimal and negative (-) input
            }
        }
        Box(Modifier.fillMaxSize())
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCalculator(){
    CalcScreenAndButton()
}