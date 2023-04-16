package com.example.peoject_crista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val buttonClickr = findViewById<Button>(R.id.button_r)
        buttonClickr.setOnClickListener {
            val intent = Intent(this, Report_Road::class.java)
            startActivity(intent)
        }

        val buttonClickp = findViewById<Button>(R.id.button_p)
        buttonClickp.setOnClickListener {
            val intent = Intent(this, Report_Power::class.java)
            startActivity(intent)
        }

    }
}