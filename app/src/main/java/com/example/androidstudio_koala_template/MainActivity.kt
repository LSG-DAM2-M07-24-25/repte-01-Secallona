package com.example.androidstudio_koala_template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidstudio_koala_template.ui.theme.AndroidStudioKoalaTemplateTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidStudioKoalaTemplateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

fun manejarIcono(icono: String) {
     val iconoDef = when (icono) {
        "Add" -> Icons.Default.Add
        "Call" -> Icons.Default.Call
        "Email" -> Icons.Default.Email
        else -> Icons.Default.Add
    }

}

@Composable
fun Greeting(modifier: Modifier = Modifier) {

    var selectedText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val opcions = listOf("Add", "Call", "Email")
    var min by remember { mutableStateOf("") }
    var max by remember { mutableStateOf("") }
    val minFloat = min.toFloatOrNull() ?: 0f
    val maxFloat = max.toFloatOrNull() ?: 10f
    var sliderValue: Float by remember {
        mutableStateOf(0f)
    }
    var icono by remember { mutableStateOf(Icons.Default.Call) }



Box(modifier = Modifier.fillMaxSize()) {

    Column(modifier = Modifier.padding(20.dp)) {
        Text(
            text = "Repte 01",
            color = Color.Blue,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth(),
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )


        )


        // Menú desplegable
        DropdownMenu(

            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            opcions.forEach { opcion ->
                DropdownMenuItem(
                    text = { Text(text = opcion) },
                    onClick = {
                        expanded = false
                        selectedText = opcion
                    }
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text("Min:")
                TextField(
                    value = min,
                    onValueChange = { min = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text("Max:")
                TextField(
                    value = max,
                    onValueChange = { max = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = minFloat..maxFloat,
            steps = 9
        )
        Text(text = sliderValue.toString())
        Button(
            onClick = { icono = when(selectedText){
                "Add" -> Icons.Default.Add
                "Call" -> Icons.Default.Call
                "Email" -> Icons.Default.Email
                else -> Icons.Default.Add
            } },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.Black
            ),
            border = BorderStroke(5.dp, Color.Green)
        ) {
            Text(text = "Enviar")
        }
        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(thickness = 2.dp)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            BadgedBox(
                badge = {
                    Badge(
                        containerColor = Color.Green,
                        contentColor = Color.Red
                    ) {
                        Text(sliderValue.toString())
                    }
                },
                content = {
                    Icon(
                        icono,
                        contentDescription = "icono"
                    )
                }
            )
        }

    }
}
}


@Preview(
    showBackground = true, showSystemUi = true
)
@Composable
fun GreetingPreview() {
    AndroidStudioKoalaTemplateTheme {
        Greeting()
    }
}