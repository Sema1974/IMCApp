package com.example.imcapp

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.Slider

class ImcCalculatorActivity : AppCompatActivity() {
    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView
    private lateinit var tvHeight:TextView
    private lateinit var rsHeight: Slider
    private  var isMaleSelected = true



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
        rsHeight.addOnChangeListener{_, value,_-> tvHeight.text=DecimalFormat("#.##").format(value) + " cm"}
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(!isMaleSelected))
    }

    private fun getBackgroundColor(isComponentSelected: Boolean): Int {

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
        rsHeight=findViewById(R.id.rsHeight)

    }

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

}