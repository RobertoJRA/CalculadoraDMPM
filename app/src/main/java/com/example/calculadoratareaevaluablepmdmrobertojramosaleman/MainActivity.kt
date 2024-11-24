package com.example.calculadoratareaevaluablepmdmrobertojramosaleman

import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadoratareaevaluablepmdmrobertojramosaleman.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity(), OnClickListener {


    private lateinit var binding: ActivityMainBinding
    private var resultado=0
    private var op1 = 0
    private  var operacion: Char? = null
    private var error: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resultado = savedInstanceState?.getInt("numerosMostrar") ?: 0
        binding.numerosMostrar.text = op1.toString()
        binding.borrarPantalla.setOnClickListener(this)
        binding.borrarTodo.setOnClickListener(this)

        binding.n1.setOnClickListener(this)
        binding.n2.setOnClickListener(this)
        binding.n3.setOnClickListener(this)
        binding.n4.setOnClickListener(this)
        binding.n5.setOnClickListener(this)
        binding.n6.setOnClickListener(this)
        binding.n7.setOnClickListener(this)
        binding.n8.setOnClickListener(this)
        binding.n9.setOnClickListener(this)
        binding.n0.setOnClickListener(this)

        binding.sumar.setOnClickListener(this)
        binding.restar.setOnClickListener(this)
        binding.multiplicar.setOnClickListener(this)
        binding.dividir.setOnClickListener(this)
        binding.mostrarResultado.setOnClickListener(this)

        binding.porcentaje?.setOnClickListener(this)
        binding.cambiarSigno?.setOnClickListener(this)
        binding.elevarCuadrado?.setOnClickListener(this)
        binding.elevarCubo?.setOnClickListener(this)
        binding.raizCuadrada?.setOnClickListener(this)
        binding.raizCubica?.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            binding.n1.id -> agregarNumero(1)
            binding.n2.id -> agregarNumero(2)
            binding.n3.id -> agregarNumero(3)
            binding.n4.id -> agregarNumero(4)
            binding.n5.id -> agregarNumero(5)
            binding.n6.id -> agregarNumero(6)
            binding.n7.id -> agregarNumero(7)
            binding.n8.id -> agregarNumero(8)
            binding.n9.id -> agregarNumero(9)
            binding.n0.id -> agregarNumero(0)

            binding.borrarPantalla.id -> {op1 = 0; binding.numerosMostrar.text = op1.toString()}
            binding.borrarTodo.id -> {op1 = 0; resultado = 0; binding.numerosMostrar.text = op1.toString()}

            binding.sumar.id -> { resultado += op1; op1 = 0; operacion = '+'; binding.numerosMostrar.text = resultado.toString()}
            binding.restar.id -> opRestar()

            binding.multiplicar.id -> opMultiplicar()
            binding.dividir.id -> opDividir()

            binding.porcentaje?.id -> operacion = 'p'
            binding.cambiarSigno?.id -> {op1= op1*-1; binding.numerosMostrar.text = op1.toString()}
            binding.elevarCuadrado?.id ->{resultado= op1*op1;op1=resultado; binding.numerosMostrar.text = resultado.toString()}
            binding.elevarCubo?.id ->{resultado= op1*op1*op1;op1=resultado; binding.numerosMostrar.text = resultado.toString()}
            binding.raizCuadrada?.id ->{resultado = Math.sqrt(op1.toDouble()).toInt();op1=resultado; binding.numerosMostrar.text = resultado.toString()}
            binding.raizCubica?.id ->{resultado = op1.toDouble().pow(1.0/3.0) .toInt();op1=resultado; binding.numerosMostrar.text = resultado.toString()}



            binding.mostrarResultado.id -> resultado()
        }
    }


        private fun agregarNumero(numero: Int) {
            op1 = if (op1 == 0) numero else "$op1$numero".toInt()
            binding.numerosMostrar.text = op1.toString()
        }


    private fun resultado() {
        when (operacion) {
            '+' -> resultado += op1;
            '-' -> opRestar()
            '*' -> opMultiplicar()
            '/' -> opDividir()
            'p' -> resultado = op1/100
        }
        op1=0
        operacion = null
        binding.numerosMostrar.text = resultado.toString()
    }

    private fun opMultiplicar() {
        if (operacion == null && resultado == 0) {
            resultado = op1
        } else if (operacion == '*') {
            resultado *= op1
        }
        op1 = 0
        operacion = '*'
        binding.numerosMostrar.text = resultado.toString()
    }

    private fun opRestar() {
        if (operacion == null && resultado == 0) {
            resultado = op1
        } else if (operacion == '-') {
            resultado -= op1
        }
        op1 = 0
        operacion = '-'
        binding.numerosMostrar.text = resultado.toString()
    }

    private fun opDividir() {
        error = false
        if (operacion == null && resultado == 0) {
            resultado = op1
        } else if (operacion == '/') {
            if (op1!=0){
                resultado /= op1
            }else{
                binding.numerosMostrar.text ="Error"
                error = true
            }
        }
        op1 = 0
        operacion = '/'
        if (!error){
            binding.numerosMostrar.text = resultado.toString()
        }

    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("op1", op1)
        outState.putInt("resultado", resultado)
        outState.putChar("operacion", operacion ?: ' ')
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        op1 = savedInstanceState.getInt("op1", 0)
        resultado = savedInstanceState.getInt("resultado", 0)
        operacion = savedInstanceState.getChar("operacion", ' ')
        binding.numerosMostrar.text = resultado.toString()
    }

}