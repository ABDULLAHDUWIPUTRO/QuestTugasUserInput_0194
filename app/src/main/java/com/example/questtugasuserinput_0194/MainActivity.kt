package com.example.questtugasuserinput_0194

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.questtugasuserinput_0194.ui.theme.QuestTugasUserInput_0194Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuestTugasUserInput_0194Theme {
                BiodataScreen()
            }
        }
    }
}

@Composable
fun BiodataScreen() {
    Box(modifier = Modifier.fillMaxSize()) {

        }