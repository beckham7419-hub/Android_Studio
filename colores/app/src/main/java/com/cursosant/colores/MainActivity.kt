package com.cursosant.colores

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var estadoRosaSuave = false
    private var estadoAmarilloSuave = false
    private var estadoVerdeSuave = false
    private var estadoAzulSuave = false
    private var layoutTocado: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lyRosa = findViewById<LinearLayout>(R.id.ly_rosa)
        val lyAmarillo = findViewById<LinearLayout>(R.id.ly_amarillo)
        val lyVerde = findViewById<LinearLayout>(R.id.ly_verde)
        val lyAzul = findViewById<LinearLayout>(R.id.ly_azul)

        lyRosa.setOnClickListener {
            layoutTocado = "rosa"
            val intent = Intent(this, ActivityColor::class.java)
            intent.putExtra("color", Color.rgb(255, 105, 180))
            startActivity(intent)
        }

        lyAmarillo.setOnClickListener {
            layoutTocado = "amarillo"
            val intent = Intent(this, ActivityColor::class.java)
            intent.putExtra("color", Color.rgb(255, 215, 0))
            startActivity(intent)
        }

        lyVerde.setOnClickListener {
            layoutTocado = "verde"
            val intent = Intent(this, ActivityColor::class.java)
            intent.putExtra("color", Color.rgb(0, 200, 83))
            startActivity(intent)
        }

        lyAzul.setOnClickListener {
            layoutTocado = "azul"
            val intent = Intent(this, ActivityColor::class.java)
            intent.putExtra("color", Color.rgb(30, 144, 255))
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        val lyRosa = findViewById<LinearLayout>(R.id.ly_rosa)
        val lyAmarillo = findViewById<LinearLayout>(R.id.ly_amarillo)
        val lyVerde = findViewById<LinearLayout>(R.id.ly_verde)
        val lyAzul = findViewById<LinearLayout>(R.id.ly_azul)

        when (layoutTocado) {
            "rosa" -> {
                estadoRosaSuave = !estadoRosaSuave
                val color = if (estadoRosaSuave) Color.rgb(255, 182, 193) else Color.rgb(255, 105, 180)
                lyRosa.setBackgroundColor(color)
            }
            "amarillo" -> {
                estadoAmarilloSuave = !estadoAmarilloSuave
                val color = if (estadoAmarilloSuave) Color.rgb(255, 255, 153) else Color.rgb(255, 215, 0)
                lyAmarillo.setBackgroundColor(color)
            }
            "verde" -> {
                estadoVerdeSuave = !estadoVerdeSuave
                val color = if (estadoVerdeSuave) Color.rgb(144, 238, 144) else Color.rgb(0, 200, 83)
                lyVerde.setBackgroundColor(color)
            }
            "azul" -> {
                estadoAzulSuave = !estadoAzulSuave
                val color = if (estadoAzulSuave) Color.rgb(173, 216, 230) else Color.rgb(30, 144, 255)
                lyAzul.setBackgroundColor(color)
            }
        }

        layoutTocado = null
    }
}