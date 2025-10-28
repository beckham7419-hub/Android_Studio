package com.cursosant.fragments_xyz

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

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
        val btnX = findViewById<Button>(R.id.btn_fragment_X)
        val btnY = findViewById<Button>(R.id.btn_fragment_Y)
        val btnZ = findViewById<Button>(R.id.btn_fragment_Z)

        reemplazarFragment(FragmentX())

        btnX.setOnClickListener { reemplazarFragment(FragmentX()) }
        btnY.setOnClickListener { reemplazarFragment(FragmentY()) }
        btnZ.setOnClickListener { reemplazarFragment(FragmentZ()) }
    }

    private fun reemplazarFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
        .replace(R.id.contenedor_fragments, fragment)
        .commit()
    }
}