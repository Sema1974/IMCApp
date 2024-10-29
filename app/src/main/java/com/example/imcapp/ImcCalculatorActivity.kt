package com.example.imcapp

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ImcCalculatorActivity : AppCompatActivity() {
    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView
    private  var isMaleSelected = true
    override fun onCreate(savedInstanceState: Bundle?) {
        initComponents()
        initListeners()
        initUI()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initUI() {
        setGenderColor()
    }

    private fun initListeners() {


        val viewMale: CardView = findViewById(R.id.viewMale)
            viewMale.setOnClickListener{
                isMaleSelected = true
                setGenderColor()
            }
        val viewFemale: CardView = findViewById(R.id.viewFemale)
            viewFemale.setOnClickListener{
                isMaleSelected = false
                setGenderColor()
            }
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(!isMaleSelected))
    }

    private fun getBackgroundColor(isComponentSelected: boolean): int {

        val colorReference = if(isComponentSelected){
            R.color.bg_comp_sel
        }else{
            R.color.bg_comp
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initComponents() {
        viewMale=findViewById(R.id.viewMale)
        viewFemale=findViewById(R.id.viewFemale)
    }
}