package com.example.questtugasuserinput_0194

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
        // Layer bawah: Scaffold berisi gambar header
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Image(
                painter = painterResource(id = R.drawable.bg_spider),
                contentDescription = stringResource(id = R.string.cd_header),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.header_height))
                    .padding(bottom = innerPadding.calculateBottomPadding())
            )
        }