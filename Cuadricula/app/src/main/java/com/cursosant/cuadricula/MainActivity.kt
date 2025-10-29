package com.cursosant.cuadricula

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.platform.LocalContext
import com.cursosant.cuadricula.ui.theme.CuadriculaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CuadriculaTheme {
                CuadroColores()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewCuadros() {
    CuadroColores()
}

@Composable
fun CuadroColores() {
    val context = LocalContext.current
    var numberClicks = 0

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Red)
                    .clickable {
                        numberClicks++
                        Toast.makeText(context, "Box tocado, Has clickeado ${numberClicks} veces", Toast.LENGTH_SHORT).show()
                    }
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Green)
                    .clickable {
                        numberClicks++
                        Toast.makeText(context, "Box tocado, Has clickeado ${numberClicks} veces", Toast.LENGTH_SHORT).show()
                    }
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Blue)
                    .clickable {
                        numberClicks++
                        Toast.makeText(context, "Box tocado, Has clickeado ${numberClicks} veces", Toast.LENGTH_SHORT).show()
                    }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Yellow)
                    .clickable {
                        numberClicks++
                        Toast.makeText(context, "Box tocado, Has clickeado ${numberClicks} veces", Toast.LENGTH_SHORT).show()
                    }
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Magenta)
                    .clickable {
                        numberClicks++
                        Toast.makeText(context, "Box tocado, Has clickeado ${numberClicks} veces", Toast.LENGTH_SHORT).show()
                    }
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Cyan)
                    .clickable {
                        numberClicks++
                        Toast.makeText(context, "Box tocado, Has clickeado ${numberClicks} veces", Toast.LENGTH_SHORT).show()
                    }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Gray)
                    .clickable {
                        numberClicks++
                        Toast.makeText(context, "Box tocado, Has clickeado ${numberClicks} veces", Toast.LENGTH_SHORT).show()
                    }
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Black)
                    .clickable {
                        numberClicks++
                        Toast.makeText(context, "Box tocado, Has clickeado ${numberClicks} veces", Toast.LENGTH_SHORT).show()
                    }
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.White)
                    .clickable {
                        numberClicks++
                        Toast.makeText(context, "Box tocado, Has clickeado ${numberClicks} veces", Toast.LENGTH_SHORT).show()
                    }
            )
        }
    }
}