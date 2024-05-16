package com.example.cw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.TypedArrayUtils.getResourceId
import com.example.cw.ui.theme.Beige
import com.example.cw.ui.theme.CWTheme
import com.example.cw.ui.theme.PurpleGrey80
import kotlinx.coroutines.delay
import java.security.KeyStore.TrustedCertificateEntry


class GuessTheCountry : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val timerEnabled = intent.getBooleanExtra("timerEnabled", false)
            flag(timerEnabled)
        }
    }
}


//@Preview(showBackground = true)
@Composable
fun flag(timer: Boolean) {

    var selectedCountry by rememberSaveable { mutableStateOf<String?>(null) } /*Contains the country selected from the lazy column*/
    var correctAnswer by rememberSaveable { mutableStateOf("") }  /*Variable used to store the msg shown after pressing the submit button */
    var countryDisplayed by rememberSaveable { mutableStateOf(countryFlags.random()) } /*The random country selected that needs to be guessed*/
    var bText by rememberSaveable { mutableStateOf("SUBMIT") } /* bText -> button text*/
    var noOfClicks by rememberSaveable { mutableStateOf(0) }  /*This counter is used to keep a count of the no of clicks so that 'SUBMIT' and 'NEXT' can be displayed appropriately*/
    var answered by rememberSaveable { mutableStateOf(false) }

    var timeText by remember {
        mutableStateOf("")
    }
    var elapsedTime by remember { mutableStateOf(0L) }
    val totalTime = 10000L
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
//    if (timeRemaining == 0L) {
//        noOfClicks = 0
//    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Beige,
    ) {
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
                Text(text = timeText)
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

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
            ) {
                items(countries) { country ->
                    Text(
                        text = country,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { selectedCountry = country }
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {

                    if (selectedCountry == countryDisplayed.first) {
                        correctAnswer = "CORRECT"
                    } else {
                        correctAnswer = "INCORRECT"
                    }
                    //The button text will changed based on the below condiiton
                    if (noOfClicks % 2 == 0) {
                        bText = "NEXT"
                        answered = true
                        noOfClicks += 1


                    } else {
                        //Gets regenerated when the "NEXT" button is pressed
                        bText = "SUBMIT"
                        countryDisplayed = countryFlags.random()
                        answered = false
                        noOfClicks += 1
                    }
                }) {
                    Text(text = bText)
                }


                if (answered) {
                    if (correctAnswer == "CORRECT") {
                        Text(text = correctAnswer, color = Color.Green)
                    } else {
                        Text(text = correctAnswer, color = Color.Red, fontWeight = FontWeight.Bold)
                    }
                    Text(
                        text = countryDisplayed.first,
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )

                }

            }
        }
    }
}




