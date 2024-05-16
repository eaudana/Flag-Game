package com.example.cw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.ui.theme.Beige
import kotlinx.coroutines.delay

class AdvancedLevel : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Beige
            ) {
                val timerEnabled = intent.getBooleanExtra("timerEnabled", false)
                threeFlags(timerEnabled)
            }

        }
    }
}


//@Preview(showBackground = true)
@Composable
fun threeFlags(timer: Boolean) {
    var bText by rememberSaveable { mutableStateOf("SUBMIT") } /*Contains whether the button needs to be "SUBMIT" or "NEXT" */

    var counter by rememberSaveable { mutableStateOf(0) } /*Keeps a count of the no of submits*/
    var score by rememberSaveable { mutableStateOf(0) } /*Keeps a count of the correct no of guesses*/
    var countries by rememberSaveable { mutableStateOf(randomFlags()) }

    //Contains the names of the three flags generated
    var flagOne by rememberSaveable { mutableStateOf(countries[0].first) }
    var flagTwo by rememberSaveable { mutableStateOf(countries[1].first) }
    var flagThree by rememberSaveable { mutableStateOf(countries[2].first) }

    //Text-field values for each flag
    var textValue1 by remember { mutableStateOf(TextFieldValue("")) }
    var textValue2 by remember { mutableStateOf(TextFieldValue("")) }
    var textValue3 by remember { mutableStateOf(TextFieldValue("")) }

    //Contains "CORRECT" or 'WRONG" depending on whether the correct flag was guessed respectively
    var answer1 by rememberSaveable { mutableStateOf("") }
    var answer2 by rememberSaveable { mutableStateOf("") }
    var answer3 by rememberSaveable { mutableStateOf("") }

    //These three variables are used to make the text fields uneditable if the correct answer was entered.
    var editable1 by rememberSaveable { mutableStateOf(true) }
    var editable2 by rememberSaveable { mutableStateOf(true) }
    var editable3 by rememberSaveable { mutableStateOf(true) }

    var questionStatus by remember { mutableStateOf("") } /* This will be displayed at the end of each round depending on whether the user entered the correct country names*/
    var finished by remember { mutableStateOf(false) }
    var textColor1 by remember { mutableStateOf(Color.Black) }

    val textColor3 = when (answer3) {
        "CORRECT" -> Color.Green
        "INCORRECT" -> Color.Red
        else -> {
            Color.Black
        }
    }
    val textColor2 = when (answer2) {
        "CORRECT" -> Color.Green
        "INCORRECT" -> Color.Red
        "WRONG" -> Color.Red
        else -> {
            Color.Black
        }
    }
    textColor1 = when (answer1) {
        "CORRECT" -> Color.Green
        "INCORRECT" -> Color.Red
        else -> {
            Color.Black
        }
    }

    var elapsedTime by remember { mutableStateOf(0L) }
    val totalTime = 10000L
    var timeRemaining by remember { mutableStateOf(0L) }
    var timeText by remember { mutableStateOf("") }

    if (timer) {
        LaunchedEffect(Unit) {
            val startTime = System.currentTimeMillis()
            while (true) {
                elapsedTime = System.currentTimeMillis() - startTime
                delay(1000)
                timeText = "Time Remaining: ${timeRemaining / 1000} seconds"
            }
        }
    }
    timeRemaining = totalTime - elapsedTime

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Guess the Countries",
            style = TextStyle(fontSize = 20.sp),
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center
        )
        Text("Score : $score", textAlign = TextAlign.End)
        Text(timeText)
    }
    Spacer(modifier = Modifier.height(200.dp))
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.width(30.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = countries[0].second),
                contentDescription = "flag One",
                modifier = Modifier
                    .size(150.dp, 100.dp)
                    .padding(16.dp)

            )
            TextField(
                value = textValue1,
                enabled = editable1,
                modifier = Modifier
                    .padding(8.dp)
                    .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(4.dp)),
                onValueChange = { textValue1 = it },
                textStyle = TextStyle(color = textColor1)
            )
            if (finished) {
                Text(text = flagOne, style = TextStyle(color = Color.Blue))
            }

        }
        Spacer(modifier = Modifier.width(16.dp))


        Image(
            painter = painterResource(id = countries[1].second),
            contentDescription = "flag Two",
            modifier = Modifier
                .size(150.dp, 100.dp)
                .padding(16.dp)

        )
        TextField(
            value = textValue2,
            enabled = editable2,
            modifier = Modifier
                .padding(8.dp)

                .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(4.dp)),
            onValueChange = { textValue2 = it },
            singleLine = true,
            textStyle = TextStyle(color = textColor2)
        )
        if (finished) {
            Text(text = flagTwo, style = TextStyle(color = Color.Blue))
        }
        Spacer(modifier = Modifier.width(16.dp))

        Image(
            painter = painterResource(id = countries[2].second),
            contentDescription = "flag Three",
            modifier = Modifier
                .size(150.dp, 100.dp)
                .padding(16.dp)

        )
        TextField(
            value = textValue3,
            enabled = editable3,
            onValueChange = { textValue3 = it },
            modifier = Modifier
                .padding(8.dp)
                .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(4.dp)),
            textStyle = TextStyle(color = textColor3)
        )
        if (finished) {
            Text(text = flagThree, style = TextStyle(color = Color.Blue))
        }


        Button(onClick = {
            counter++
            if (bText == "SUBMIT") {
                if (textValue1.text == flagOne) {
                    answer1 = "CORRECT"
                    if (editable1) {
                        score++
                    }
                    editable1 = false


                } else {
                    answer1 = "INCORRECT"
                }
                if (textValue2.text == flagTwo) {
                    answer2 = "CORRECT"
                    if (editable2) {
                        score++
                    }
                    editable2 = false

                } else {
                    answer2 = "INCORRECT"
                }
                if (textValue3.text == flagThree) {
                    answer3 = "CORRECT"
                    if (editable3) {
                        score++
                    }
                    editable3 = false

                } else {
                    answer3 = "INCORRECT"
                }

                if (counter < 3) {
                    bText = "SUBMIT"
                } else {
                    bText = "NEXT"
                }

                if (answer1 == "CORRECT" && answer2 == "CORRECT" && answer3 == "CORRECT") {
                    questionStatus = "CORRECT"
                    bText = "NEXT"
                } else if (counter == 3) {
                    questionStatus = "WRONG!"
                    finished = true
                }
            } else {
                bText = "SUBMIT"
                counter = 0
                countries = randomFlags()
                flagOne = countries[0].first
                flagTwo = countries[1].first
                flagThree = countries[2].first
                answer1 = ""
                answer2 = ""
                answer3 = ""
                editable1 = true
                editable2 = true
                editable3 = true
                questionStatus = ""
                finished = false
                //Everything is regenerated when the "NEXT" button is pressed.

            }

        }
        ) {
            Text(bText)
        }

        Text(text = questionStatus, style = TextStyle(color = textColor2))
    }

}