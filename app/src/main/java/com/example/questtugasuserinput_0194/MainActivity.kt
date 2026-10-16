package com.example.questtugasuserinput_0194

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
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

        // Layer atas: Box mengapung di atas Scaffold
        BiodataCard(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BiodataCard(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }
    var skillAndroid by remember { mutableStateOf(false) }
    var skillUiux by remember { mutableStateOf(false) }
    var skillWeb by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    var showExperienceMenu by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()
    val experienceOptions = stringArrayResource(id = R.array.experience_options)
    val dateFormat = stringResource(id = R.string.date_format)
    val successMessage = stringResource(id = R.string.msg_success)

    val fieldShape = RoundedCornerShape(dimensionResource(id = R.dimen.field_radius))
    val fieldHeight = dimensionResource(id = R.dimen.field_height)
    val spacing = dimensionResource(id = R.dimen.spacing)

    val isFormValid = firstName.isNotBlank() &&
            lastName.isNotBlank() &&
            email.isNotBlank() &&
            birthDate.isNotBlank() &&
            experience.isNotBlank() &&
            (skillAndroid || skillUiux || skillWeb)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = dimensionResource(id = R.dimen.card_radius),
                    topEnd = dimensionResource(id = R.dimen.card_radius)
                )
            )
            .background(colorResource(id = R.color.card_background))
            .navigationBarsPadding()
    )  {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(dimensionResource(id = R.dimen.card_padding)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.title_form),
                color = colorResource(id = R.color.purple_title),
                fontSize = dimensionResource(id = R.dimen.text_title).value.sp,
                fontWeight = FontWeight.Bold
            )
