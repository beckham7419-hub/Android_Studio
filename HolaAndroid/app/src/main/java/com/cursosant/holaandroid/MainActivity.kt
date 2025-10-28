package com.cursosant.holaandroid

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var linlay_verde_mexico: LinearLayout;
    private lateinit var linlay_blanco_mexico: LinearLayout;
    private lateinit var linlay_rojo_mexico: LinearLayout;
    private var mediaPlayer: MediaPlayer? = null //declarar mediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        linlay_verde_mexico = findViewById(R.id.linlay_verde_mexico)
        linlay_verde_mexico.setOnClickListener {
            Toast.makeText(baseContext, "Soy el Linear Layout verde mexico", Toast.LENGTH_SHORT).show()
        }

        linlay_blanco_mexico = findViewById(R.id.linlay_blanco_mexico)
        linlay_blanco_mexico.setOnClickListener {
            Toast.makeText(baseContext, "Soy el Linear Layout blanco mexico", Toast.LENGTH_SHORT).show()
            mediaPlayer = MediaPlayer.create(this,R.raw.himno) //ubicar el archivo de audio
            mediaPlayer?.isLooping = true //loop
            mediaPlayer?.start() //Y que empiece la cancion
        }

        linlay_rojo_mexico = findViewById(R.id.linlay_rojo_mexico)
        linlay_rojo_mexico.setOnClickListener {
            Toast.makeText(baseContext, "Soy el Linear Layout rojo mexico", Toast.LENGTH_SHORT).show()
        }
    }
}