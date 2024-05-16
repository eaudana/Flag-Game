package com.example.cw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cw.ui.theme.Beige
import kotlinx.coroutines.delay
import kotlin.random.Random


class GuessTheFlag : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val timerEnabled = intent.getBooleanExtra("timerEnabled", false)
            flagGuesser(timerEnabled)
        }
    }
}


//@Preview(showBackground = true)
@Composable
fun flagGuesser(timer: Boolean) {


    var answer by rememberSaveable { mutableStateOf("") }
    var correctFlag by rememberSaveable { mutableStateOf("") } /*Contains the name of the flag that needs to be guessed*/

    //Gets three flags from the "randomFlags" function
    var countries by rememberSaveable { mutableStateOf(randomFlags()) }

    //Contains the names of the three flags generated
    var flagOne by rememberSaveable { mutableStateOf(countries[0].first) }
    var flagTwo by rememberSaveable { mutableStateOf(countries[1].first) }
    var flagThree by rememberSaveable { mutableStateOf(countries[2].first) }

    //Randomly selects a flag from FlagOne,flagTwo and FlagThree
    var currentFlag by rememberSaveable { mutableStateOf(Random.nextInt(3)) }
    correctFlag = countries[currentFlag].first


    var elapsedTime by remember { mutableStateOf(0L) }
    val totalTime = 10000L
    var timeText by remember { mutableStateOf("") }
    var timeRemaining by remember { mutableStateOf(0L) }

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

    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Beige,

        ) {


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                "Guess the Flag",
                style = TextStyle(fontSize = 20.sp),
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center
            )
            if (timer) {
                Text(text=timeText)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = correctFlag, style = TextStyle(fontSize = 25.sp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(painter = painterResource(id = countries[0].second),
                    contentDescription = "flag One",
                    modifier = Modifier
                        .clickable {
                            if (currentFlag == 0) {
                                answer = "Correct"

                            } else
                                answer = "Incorrect"
                        }
                        .size(120.dp, 120.dp)
                        .padding(15.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Image(painter = painterResource(id = countries[1].second),
                    contentDescription = "flag Two",
                    modifier = Modifier
                        .clickable {
                            if (currentFlag == 1) {
                                answer = "Correct"
                            } else
                                answer = "Incorrect"
                        }
                        .size(120.dp, 120.dp)
                        .padding(15.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Image(painter = painterResource(id = countries[2].second),
                    contentDescription = "flag Three",
                    modifier = Modifier
                        .clickable {
                            if (currentFlag == 2) {
                                answer = "Correct"
                            } else
                                answer = "Incorrect"
                        }
                        .size(120.dp, 120.dp)
                        .padding(15.dp)
                )


            }

            if (answer == "Correct") {
                Text(
                    text = "CORRECT",
                    color = Color.Green,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            } else if (answer == "Incorrect") {
                Text(
                    text = "INCORRECT",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Button(onClick = {
                //Regenerates when the button becomes "NEXT"
                answer = ""
                countries = randomFlags()
                flagOne = countries[0].first
                flagTwo = countries[1].first
                flagThree = countries[2].first
                currentFlag = Random.nextInt(3)
                correctFlag = countries[currentFlag].first

            }) {
                Text(text = "NEXT")

            }

        }
    }
}

//Selects three random flags from the list provided and return those three flags in an array
fun randomFlags(): MutableList<Pair<String, Int>> {
    var drawables_list = mutableListOf<Pair<String, Int>>()

    var c1 = countryFlags.random()
    var c2 = countryFlags.random()
    while (c1 == c2) {
        c2 = countryFlags.random()
    }
    var c3 = countryFlags.random()
    while (c1 == c3 || c2 == c3) {
        c3 = countryFlags.random()
    }
    drawables_list.add(c1)
    drawables_list.add(c2)
    drawables_list.add(c3)
    return drawables_list
}



