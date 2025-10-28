package com.cursosant.fragment

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnA = findViewById<ImageButton>(R.id.btnA)
        val btnB = findViewById<ImageButton>(R.id.btnB)
        val btnC = findViewById<ImageButton>(R.id.btnC)

        btnA.setOnClickListener {
            val intent = Intent(this, ActivityA::class.java)
            ActivityYLauncher.launch(intent)
        }
        btnB.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)
            ActivityYLauncher.launch(intent)
        }
        btnC.setOnClickListener {
            val intent = Intent(this, ActivityC::class.java)
            ActivityYLauncher.launch(intent)
        }
    }

    private val ActivityYLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()
        }
        else if (result.resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Salio de la actividad", Toast.LENGTH_SHORT).show()
        }
    }
}