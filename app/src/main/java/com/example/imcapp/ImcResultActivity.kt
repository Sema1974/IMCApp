package com.example.imcapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView

class ImcResultActivity: AppCompatActivity() {
    private lateinit var btnReCal: AppCompatButton
    private lateinit var tvReFi: TextView
    private lateinit var tvRes: TextView
    private lateinit var tvComentario: TextView

    private fun initListeners(){
        btnReCal.setOnClickListener(){
            finish()
        }
    }

    private fun mostrarResul(valorRel: Double){
        when(valorRel) {
            in 0.00..18.50 -> {
                tvReFi.text = getString(R.string.muydelgado)
                tvReFi.setTextColor(ContextCompat.getColor(this, R.color.red))
                tvComentario.text = getString(R.string.comentario_delgado)

            }
            in 18.51..24.99 -> {
                tvReFi.text = getString(R.string.estupendo)
                tvReFi.setTextColor(ContextCompat.getColor(this, R.color.green))
                tvComentario.text = getString(R.string.comentario_ideal)
            }
            in 25.00..29.99-> {
                tvReFi.text = getString(R.string.sobrepeso)
                tvReFi.setTextColor(ContextCompat.getColor(this, R.color.mage))
                tvComentario.text = getString(R.string.comentario_sobrepeso)
            }
            else -> {
                tvReFi.text = getString(R.string.muchosobrepeso)
                tvReFi.setTextColor(ContextCompat.getColor(this, R.color.orqui))
                tvComentario.text = getString(R.string.comentario_muchosobrepeso)
            }
        }
    }

    private fun initComponents() {
        btnReCal = findViewById(R.id.botonReCalcular)
        tvReFi = findViewById(R.id.resultadofinal)
        tvRes = findViewById(R.id.numefinal)
        tvComentario = findViewById(R.id.sentenciafinal)

    }
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_imc_result)

            val valorRel:Double = intent.extras?.getDouble("EXTRA_NAME") ?: -1.0

            initComponents()
            initListeners()
            mostrarResul(valorRel)

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
}