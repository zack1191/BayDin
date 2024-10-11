package com.example.composetutorial.ui.theme.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.BayDin
import com.example.composetutorial.Questions
import com.example.composetutorial.R
import com.example.composetutorial.SampleQuestions
import com.example.composetutorial.Utils
import com.example.composetutorial.ui.theme.activities.ui.theme.ComposeTutorialTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class QuestionsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val json = Utils().getJsonDataFromAsset(this, "baydin.json")
                    val bayDin = Gson().fromJson(json, BayDin::class.java)
                    ShowQuestion(questions = bayDin.questions)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Questions(question: Questions, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Surface(
        shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp,
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .animateContentSize()
            .padding(4.dp),
        onClick = {
            val intent = Intent(context, ChooseAnswerActivity::class.java)
            intent.putExtra("question", question)
            context.startActivity(intent)
        }
    ) {
        Row(
            modifier = Modifier
                .padding(all = 8.dp)
                .height(IntrinsicSize.Max)
                .fillMaxWidth()
        ) {
            Text(
                text = question.questionNo.toString(),
                modifier = Modifier.padding(10.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))

            Divider(
                modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = question.questionName.toString(),
                modifier = modifier
            )
        }
    }


}

/*@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ComposeTutorialTheme {
        Questions(
            Questions(
                18,
                "မမျှော်လင့်သော ငွေကြေးအစုလိုက် အပုံလိုက်ရခြင်း (သို့မဟုတ်) ထီပေါက်ခြင်းရှိနိုင်/ မရှိနိုင်နှင့် ဆိုင်သော အဟော။"
            )
        )
    }
}*/

@Composable
fun ShowQuestion(questions: List<Questions>, modifier: Modifier = Modifier) {
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
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.des2),
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(questions) { questions ->
                com.example.composetutorial.ui.theme.activities.Questions(question = questions)

            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun QuestionsPreview() {
    ShowQuestion(questions = SampleQuestions.sampleQuestions)
}