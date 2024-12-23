package com.example.imcapp

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.Slider

class ImcCalculatorActivity : AppCompatActivity() {
    private lateinit var btnCalcular:AppCompatButton
    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView
    private lateinit var tvHeight:TextView
    private lateinit var rsHeight:Slider
    private lateinit var viewAge:TextView
    private lateinit var btnSubtractAge:FloatingActionButton
    private lateinit var btnAddAge:FloatingActionButton
    private lateinit var  viewWeight:TextView
    private lateinit var btnSubtractWeight:FloatingActionButton
    private lateinit var btnAddWeight:FloatingActionButton
    private  var isMaleSelected = true
    private  var ageStart = 20
    private var weightStart = 60
    private var heightStart = 120
    private var resultado = 0.0



    private fun initUI() {
        setGenderColor()
        setAge(0)
        setWeight(0)
    }

    private fun initListeners() {
            viewMale.setOnClickListener{
                isMaleSelected = true
                setGenderColor()
            }
            viewFemale.setOnClickListener{
                isMaleSelected = false
                setGenderColor()
            }
            rsHeight.addOnChangeListener{_, value,_-> tvHeight.text=DecimalFormat("#.##").format(value) + " cm"}
            btnSubtractAge.setOnClickListener(){
                setAge(-1)
            }
            btnAddAge.setOnClickListener(){
                setAge(1)
            }
            btnSubtractWeight.setOnClickListener(){
                setWeight(-1)
            }
            btnAddWeight.setOnClickListener(){
                setWeight(1)
            }
            btnCalcular.setOnClickListener(){
                val intentGA = Intent(this, ImcResultActivity::class.java)
                intentGA.putExtra("EXTRA_NAME", navigate2result(calculateIMC()))
                startActivity(intentGA)

            }
    }
    private fun calculateIMC(): Double{
        val alturaMetro : Double = tvHeight.text.toString().replace(" cm", "").toDouble()/100
        resultado = (weightStart.toDouble()/(alturaMetro*alturaMetro))
        return resultado
    }
    private fun navigate2result(imc : Double):String{
        return imc.toString()
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(!isMaleSelected))
    }
    private fun setAge(nume: Int){
        ageStart = ageStart+nume
        viewAge.text=ageStart.toString()
    }
    private fun setWeight(nume: Int){
        weightStart = weightStart+nume
        viewWeight.text=weightStart.toString()
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
        viewAge=findViewById(R.id.viewAge)
        btnSubtractAge=findViewById(R.id.btnSubtractAge)
        btnAddAge=findViewById(R.id.btnAddAge)
        viewWeight=findViewById(R.id.viewWeight)
        btnSubtractWeight=findViewById(R.id.btnSubtractWeight)
        btnAddWeight=findViewById(R.id.btnAddWeight)
        btnCalcular=findViewById(R.id.botonCalcular)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculator)
        initComponents()
        initListeners()
        initUI()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

}