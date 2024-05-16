package com.example.cw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.ui.theme.Beige
import kotlinx.coroutines.delay

class GuessHints : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Beige
            ) {
                val timerEnabled = intent.getBooleanExtra("timerEnabled", false)
                Hints(timerEnabled)

            }

        }
    }
}

//@Preview(showBackground = true)
@Composable
fun Hints(timer: Boolean) {

    //Random country selected
    var countryDisplayed by remember { mutableStateOf(countryFlags.random()) }
    var countryName by remember { mutableStateOf(countryDisplayed.first) }// Country name
    var bText by remember { mutableStateOf("SUBMIT") } //Button text

    var questionStatus by remember { mutableStateOf("") }
    var answer by remember { mutableStateOf("") }


    var guessText by remember { mutableStateOf(TextFieldValue("")) }
    //String binder used to display the dashes
    var noOfLetters by remember { mutableStateOf(StringBuilder("-".repeat(countryDisplayed.first.length))) }
    var wrongCounter by remember { mutableStateOf(0) }
    val textColor = when (questionStatus) {
        "CORRECT" -> Color.Green
        "WRONG!" -> Color.Red
        else -> {
            Color.Black
        }
    }
    var timeText by remember { mutableStateOf("") }
    var elapsedTime by remember { mutableStateOf(0L) }
    var timeRemaining by remember { mutableStateOf(0L) }
    val totalTime = 10000L

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
    if (timeRemaining == 0L) {
        bText = "NEXT"
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column() {
            Text(
                "Guess the Country",
                style = TextStyle(fontSize = 20.sp),
                fontWeight = FontWeight.Black
            )
            Text(timeText)
        }
        Box {
            var flagDisplayed = painterResource(id = countryDisplayed.second)
            Image(
                painter = flagDisplayed,
                contentDescription = "FlagToBeGuessed",
                modifier = Modifier
                    .size(165.dp)
            )

        }
        Text(text = noOfLetters.toString(), style = TextStyle(fontSize = 50.sp))

        TextField(
            value = guessText,
            modifier = Modifier
                .padding(8.dp)
                .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(4.dp)),
            onValueChange = { guessText = it },
            singleLine = true
        )
        Text(
            text = questionStatus,
            style = TextStyle(color = textColor, fontWeight = FontWeight.Bold)
        )
        Text(text = answer, style = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold))
        Button(onClick = {
            if (bText == "SUBMIT") {
                if (countryDisplayed.first.contains(guessText.text)) {
                    for (i in 0 until countryDisplayed.first.length) {
                        var char = countryName[i]
                        if (guessText.text == char.toString()) {
                            noOfLetters[i] = char
                        }
                    }
                } else {
                    wrongCounter++

                }

                if (noOfLetters.toString() == countryName) {
                    bText = "NEXT"
                    questionStatus = "CORRECT"

                }
                if (wrongCounter == 3) {
                    bText = "NEXT"
                    questionStatus = "WRONG!"
                    answer = countryDisplayed.first
                }

            } else {
                bText = "SUBMIT"
                countryDisplayed = countryFlags.random()
                countryName = countryDisplayed.first
                wrongCounter = 0
                questionStatus = ""
                answer = ""
                noOfLetters = StringBuilder("-".repeat(countryDisplayed.first.length))
            }
        }) {
            Text(text = bText)
        }

    }

}

