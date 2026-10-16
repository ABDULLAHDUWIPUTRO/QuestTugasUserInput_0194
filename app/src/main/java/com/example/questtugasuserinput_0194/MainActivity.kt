package com.example.questtugasuserinput_0194
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.matchParentSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.questtugasuserinput_0194.ui.theme.QuestTugasUserInput_0194Theme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
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
            Text(
                text = stringResource(id = R.string.subtitle_form),
                color = colorResource(id = R.color.text_subtitle),
                fontSize = dimensionResource(id = R.dimen.text_subtitle).value.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.spacing_small),
                    bottom = dimensionResource(id = R.dimen.spacing_large)
                )
            )

            // Nama depan + belakang
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    placeholder = { FieldHint(R.string.first_name) },
                    singleLine = true,
                    shape = fieldShape,
                    modifier = Modifier
                        .weight(1f)
                        .height(fieldHeight)
                )
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    placeholder = { FieldHint(R.string.last_name) },
                    singleLine = true,
                    shape = fieldShape,
                    modifier = Modifier
                        .weight(1f)
                        .height(fieldHeight)
                )
            }

            // Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { FieldHint(R.string.email) },
                singleLine = true,
                shape = fieldShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = spacing)
                    .height(fieldHeight)
            )

            // Tgl lahir + experience
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = spacing),
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    OutlinedTextField(
                        value = birthDate,
                        onValueChange = {},
                        readOnly = true,
                        placeholder = { FieldHint(R.string.birth_date) },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = stringResource(id = R.string.cd_calendar)
                            )
                        },
                        singleLine = true,
                        shape = fieldShape,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(fieldHeight)
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clickable { showDatePicker = true }
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    OutlinedTextField(
                        value = experience,
                        onValueChange = {},
                        readOnly = true,
                        placeholder = { FieldHint(R.string.experience) },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = stringResource(id = R.string.cd_dropdown)
                            )
                        },
                        singleLine = true,
                        shape = fieldShape,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(fieldHeight)
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clickable { showExperienceMenu = true }
                    )
                    DropdownMenu(
                        expanded = showExperienceMenu,
                        onDismissRequest = { showExperienceMenu = false }
                    ) {
                        experienceOptions.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(text = option) },
                                onClick = {
                                    experience = option
                                    showExperienceMenu = false
                                }
                            )
                        }
                    }
                }
            }

            // Skill
            Text(
                text = stringResource(id = R.string.choose_skill),
                color = colorResource(id = R.color.text_label),
                fontSize = dimensionResource(id = R.dimen.text_label).value.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.spacing_large))
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SkillCheckbox(R.string.skill_android, skillAndroid) { skillAndroid = it }
                SkillCheckbox(R.string.skill_uiux, skillUiux) { skillUiux = it }
                SkillCheckbox(R.string.skill_web, skillWeb) { skillWeb = it }
            }

            // Tombol
            Button(
                onClick = {
                    Toast.makeText(context, successMessage, Toast.LENGTH_SHORT).show()
                },
                enabled = isFormValid,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.button_radius)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.button_enabled),
                    disabledContainerColor = colorResource(id = R.color.button_disabled),
                    disabledContentColor = colorResource(id = R.color.button_disabled_text)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.spacing_large))
                    .height(dimensionResource(id = R.dimen.button_height))
            ) {
                Text(
                    text = stringResource(id = R.string.proceed),
                    fontSize = dimensionResource(id = R.dimen.text_button).value.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }