package com.cursosant.cal_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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
        val btnX = findViewById<Button>(R.id.btnX)
        val btnY = findViewById<Button>(R.id.btnY)
        val btnZ = findViewById<Button>(R.id.btnZ)

        btnX.setOnClickListener {
            startActivity(Intent(this, ActivityX::class.java))
        }
        btnY.setOnClickListener {
            val intent = Intent(this, ActivityY::class.java)
            ActivityYLauncher.launch(intent)
        }
        btnZ.setOnClickListener {
            finish()
            startActivity(Intent(this, ActivityZ::class.java))
        }
    }

    private val ActivityYLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val texto = data?.getStringExtra("texto")
            Toast.makeText(this, "Texto recibido: $texto", Toast.LENGTH_SHORT).show()
        }
        else if (result.resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "El Usuario lo Cancelo", Toast.LENGTH_SHORT).show()
        }
    }
}