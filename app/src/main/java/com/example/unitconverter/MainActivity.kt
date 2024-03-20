package com.example.unitconverter

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.defaultDecayAnimationSpec
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

//@Composable
//fun CustomStyledText(text: String) {
//    Text(
//        text = text,
//        modifier = Modifier
//            .padding(16.dp) // Add padding
//            .width(200.dp), // Set width
//        style = TextStyle(
//            color = Color.Red, // Set text color
//            fontSize = 18.sp, // Set text size
//            fontFamily = FontFamily.SansSerif, // Set font family
//            fontWeight = FontWeight.Bold // Set font weight
//        )
//    )
//}

@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("")}
    var outputValue by remember {mutableStateOf("")}
    var inputUnit by remember {mutableStateOf("Meters")}
    var outputUnit by remember {mutableStateOf("Meters")}
    var iExpanded by remember { mutableStateOf(false)}
    var oExpanded by remember {mutableStateOf(false)}
    val conversionFactor = remember { mutableDoubleStateOf(1.00) }
    val oConversionFactor = remember { mutableDoubleStateOf(1.00)}

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 32.sp,
        color = Color.Blue
    )

    fun convertUnits(){
        // ?: -> elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.doubleValue * 100.00/oConversionFactor.doubleValue).roundToInt()/100.00
        outputValue = result.toString()
    }

//    fun oConvertUnits(){
//        // ?: -> elvis operator
//        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
//        val result = (inputValueDouble * oConversionFactor.doubleValue * 100.00).roundToInt()/100.00
//        outputValue = result.toString()
//    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Unit Converter", style = customTextStyle)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            convertUnits()
        },
        label = {Text("Enter Value")}
        )
        Spacer(Modifier.height(16.dp))
        Row {
            // Input box
            Box{
                //Input Button
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.doubleValue = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.doubleValue = 1.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Feet") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.doubleValue = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Millimeters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Millimeters"
                            conversionFactor.doubleValue = 0.001
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(Modifier.width(16.dp))
            // Output Box
            Box{
                // Output Button
                Button(onClick = { oExpanded = true }) {
                    Text(text =outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeters"
                            oConversionFactor.doubleValue = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            oConversionFactor.doubleValue = 1.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Feet") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.doubleValue = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Millimeters") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Millimeters"
                            oConversionFactor.doubleValue = 0.001
                            convertUnits()
                        }
                    )
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        // Result text
        Text(text = "Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium
            )
    }
}

@Preview (showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}
