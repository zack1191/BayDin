package com.example.composetutorial.ui.theme.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.BayDin
import com.example.composetutorial.Questions
import com.example.composetutorial.R
import com.example.composetutorial.Utils
import com.example.composetutorial.ui.theme.activities.ui.theme.ui.theme.ComposeTutorialTheme
import com.google.gson.Gson
import java.nio.file.WatchEvent
import kotlin.coroutines.coroutineContext

class ChooseAnswerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val question = this.intent.getSerializableExtra("question") as Questions
                    ShowNumber(question.questionName!!, 1)
                }
            }
        }
    }
}

@Composable
fun ShowNumber(question: String, number: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.mintheinkha_logo),
                contentDescription = "Book Image",
                modifier = modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .width(100.dp),
                alignment = Alignment.Center
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Text(

            text = stringResource(R.string.des),
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        val context = LocalContext.current

        Button(
            onClick = {
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5C281D))
        ) {
            Text(text = "နောက်သို့")
        }
        Text(
            text = question,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        GridNumberLayout(column = 9, row = 9, num = number, context = context)

    }
}


@Preview(showBackground = true)
@Composable
fun ShowNumberPreview() {
    ComposeTutorialTheme {
        ShowNumber("Android", 1)
    }
}

@Composable
fun GridNumberLayout(row: Int, column: Int, num: Int, context: Context) {
    val json = Utils().getJsonDataFromAsset(context, "baydin.json")
    val bayDin = Gson().fromJson(json, BayDin::class.java)

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var index = 0
        repeat(row) { rowIndex ->
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(column) { gridPerRow ->
                    GridUI(rowIndex, gridPerRow,
                        bayDin.numberList[index++], onClick = { rowIndex, gridIndex ->
                            val number = bayDin.numberList[rowIndex * column + gridIndex]


                            Toast.makeText(
                                context, number,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )//bind number to all grid layout size total 81


                }
            }
        }
    }
}

@Composable
fun GridUI(
    rowIndex: Int, gridIndex: Int,
    number: String,
    onClick: (rowIndex: Int, gridIndex: Int) -> Unit
) {

    Box(
        modifier = Modifier
            .size(40.dp)
            .padding(4.dp)
            .background(color = Color(0xFF5C281D))
            .clickable {
                onClick(rowIndex, gridIndex)
            }
    ) {
        Text(
            text = number,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.Center),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
        )
    }
}

/*@Preview(showBackground = true)
@Composable
fun ShowGridPreview() {
    ComposeTutorialTheme {
        val context = LocalContext.current
        GridNumberLayout(column = 5, row = 9, context)
    }
}*/
