package com.example.nokiacalculator

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalcScreenAndButton(){
    var input by remember {
        mutableStateOf("")
    }
    var firstNumber by remember {
        mutableStateOf("")
    }
    var selectedOperator by remember {
        mutableStateOf("")
    }
    Column(Modifier.fillMaxSize()){
        Column(Modifier.fillMaxWidth()
            .fillMaxHeight(0.55f)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedOperator,
                    fontSize = 24.sp
                )
                Text(
                    text = if (input.isEmpty()) "0" else input,
                    fontSize = 30.sp
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(6f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "+",
                    fontSize = 30.sp,
                    modifier = Modifier.clickable {
                        // Don't allow an empty first number.
                        if (input.isNotEmpty()) {
                            // Remember the first number.
                            firstNumber = input
                            // Remember the chosen operator.
                            selectedOperator = "+"
                            // Clear the input field so the user can type
                            // the second number.
                            input = ""
                        }
                    }
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "÷",
                        fontSize = 30.sp,
                        modifier = Modifier.clickable {
                            if (input.isNotEmpty()) {
                                firstNumber = input
                                selectedOperator = "÷"
                                input = ""
                            }
                        }
                    )
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .size(80.dp)
                            .border(2.dp, Color.Black, CircleShape)
                    )
                    Text(
                        text = "×",
                        fontSize = 30.sp,
                        modifier = Modifier.clickable {
                            if (input.isNotEmpty()) {
                                firstNumber = input
                                selectedOperator = "×"
                                input = ""
                            }
                        }
                    )
                }
                Text(
                    text = "-",
                    fontSize = 30.sp,
                    modifier = Modifier.clickable {
                        if (input.isNotEmpty()) {
                            firstNumber = input
                            selectedOperator = "-"
                            input = ""
                        }
                    }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    /*onClick = {
                        // Make sure we have everything we need.
                        if (
                            firstNumber.isNotEmpty() &&
                            input.isNotEmpty() &&
                            selectedOperator == "+"
                        ) {
                            // Convert the text into numbers.
                            val number1 = firstNumber.toDouble()
                            val number2 = input.toDouble()
                            // Perform the calculation.
                            val answer = number1 + number2
                            // Show the answer.
                            input = answer.toString()
                            // Forget the previous calculation.
                            firstNumber = ""
                            selectedOperator = ""
                        }
                    }*/
                    onClick = {
                        // Make sure we have enough information.
                        if (
                            firstNumber.isNotEmpty() &&
                            input.isNotEmpty()
                        ) {
                            // Convert the text into numbers.
                            val number1 = firstNumber.toDouble()
                            val number2 = input.toDouble()
                            var answer = 0.0
                            // Decide which calculation to perform.
                            if (selectedOperator == "+") {
                                answer = number1 + number2
                            }
                            else if (selectedOperator == "-") {
                                answer = number1 - number2
                            }
                            else if (selectedOperator == "×") {
                                answer = number1 * number2
                            }
                            else if (selectedOperator == "÷") {
                                answer = number1 / number2
                            }
                            // Show the answer.
                            input = answer.toString()
                            // Prepare for a new calculation.
                            firstNumber = ""
                            selectedOperator = ""
                        }
                    }
                ) {
                    Text("=")
                }
                Text(
                    text = "Clear",
                    fontSize = 20.sp
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = input,
                    onValueChange = { newInput ->
                        // Assume the input is valid.
                        var validInput = true
                        // Count decimal points.
                        var decimalCount = 0
                        // Check every character.
                        for (character in newInput) {
                            if (character == '.') {
                                decimalCount++
                            }
                            // Reject any character that isn't a digit, '.' or '-'.
                            if (
                                !character.isDigit() &&
                                character != '.' &&
                                character != '-'
                            ) {
                                validInput = false
                            }
                        }
                        // Allow only one decimal point.
                        if (decimalCount > 1) {
                            validInput = false
                        }
                        // Count minus signs.
                        var minusCount = 0
                        for (index in newInput.indices) {
                            if (newInput[index] == '-') {
                                minusCount++
                                // Minus sign must be the first character.
                                if (index != 0) {
                                    validInput = false
                                }
                            }
                        }
                        // Allow only one minus sign.
                        if (minusCount > 1) {
                            validInput = false
                        }
                        // If valid, clean up the input.
                        if (validInput) {
                            var cleanedInput = newInput
                            // Remove unnecessary leading zeros.
                            while (
                                cleanedInput.length > 1 &&
                                cleanedInput.startsWith("0") &&
                                cleanedInput[1] != '.'
                            ) {
                                cleanedInput = cleanedInput.substring(1)
                            }
                            // Remove unnecessary zeros after a minus sign.
                            if (cleanedInput.startsWith("-0")) {
                                while (
                                    cleanedInput.length > 2 &&
                                    cleanedInput[1] == '0' &&
                                    cleanedInput[2] != '.'
                                ) {
                                    cleanedInput =
                                        "-" + cleanedInput.substring(2)
                                }
                            }
                            // Update the TextField.
                            input = cleanedInput
                        }
                    },
                    label = {
                        Text("Enter Number")
                    }
                )
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