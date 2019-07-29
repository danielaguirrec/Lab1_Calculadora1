package com.example.lab1_calculadora1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.Validators.not
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Inicializacion de las variables
    private var parcial=""
    private var parcialstr = ""
    private var result = 0.0
    private var result2 = 0
    private var resultaux=0.0
    private var operacion=""
    private var num1=2.0
    private var num2=2.0
    private var reinicio=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bBoton0.setOnClickListener { appendExpr("0") }
        bBoton1.setOnClickListener { appendExpr("1") }
        bBoton2.setOnClickListener { appendExpr("2") }
        bBoton3.setOnClickListener { appendExpr("3") }
        bBoton4.setOnClickListener { appendExpr("4") }
        bBoton5.setOnClickListener { appendExpr("5") }
        bBoton6.setOnClickListener { appendExpr("6") }
        bBoton7.setOnClickListener { appendExpr("7") }
        bBoton8.setOnClickListener { appendExpr("8") }
        bBoton9.setOnClickListener { appendExpr("9") }
        bBotonMas.setOnClickListener { appendExpr("+") }
        bBotonMenos.setOnClickListener { appendExpr("-") }
        bBotonMul.setOnClickListener { appendExpr("*") }
        bBotonDiv.setOnClickListener { appendExpr("/") }
        bBotonDot.setOnClickListener { appendExpr(".") }
        bBotonEq.setOnClickListener { appendExpr("=") }
        bBorrar.setOnClickListener { appendExpr("<") }
        bCA.setOnClickListener { appendExpr("A") }
        bOpenpar.setOnClickListener { appendExpr("%") }
        bClosepar.setOnClickListener { appendExpr("Mod") }

    }
    fun appendExpr(Caracter: String){   //fhsdsd
        if (Caracter=="="){
            num2=parcial.toDouble()  //convieryo el segundo operador para hacer su respectiva operacion
            when(operacion){     // switch que realiza la operacion
                "+" -> result = num1+num2
                "-" -> result = num1-num2
                "*" -> result = num1*num2
                "/" -> result = num1/num2
                "%" -> result = num1*num2*0.01
                "Mod" ->  result= num1%num2
            }
            parcialstr=result.toString()    //luego de haber resuelto la operacion le doy los valores del resultado a
            parcial=result.toString()       // el resultado que tengo parcial
            num1=0.0   //reinicializo
            num2=0.0
            reinicio=true
            operacion=""
            resultaux=result-result.toInt()
            if(resultaux==0.0){
                result2=result.toInt()
                tvResultado.text = result2.toString()  //muestro el resultado en un textview
            }
            else{
                tvResultado.text = result.toString()  //muestro el resultado en un textview
                result=0.0 //
            }
            result=0.0 //
        }
        else if (Caracter=="<"){   //expresion que se ejecuta si necesito borrar
            var ultimaPos= parcialstr.length-1
            if(parcialstr[ultimaPos].toString()=="+" || parcialstr[ultimaPos].toString()=="-" ||parcialstr[ultimaPos].toString()=="*" ||parcialstr[ultimaPos].toString()=="/" || parcialstr[ultimaPos].toString()=="%" ||parcialstr[ultimaPos].toString()=="Mod"){

            }
            else{
                parcialstr=parcialstr.substring(0,ultimaPos)
                parcial=parcialstr
            }
            parcialstr=parcialstr.substring(0,ultimaPos)
            tvResultado.text=parcialstr
        }
        else if (Caracter=="A"){
             parcial=""
             parcialstr = ""
             result = 0.0
             result2 = 0
             resultaux=0.0
             operacion=""
             num1=2.0
             num2=2.0
             reinicio=false
             tvResultado.text=""
        }
        else {  // si mi tecla hundida no es "=" utilizo otra logica

            if (Caracter=="+"  || Caracter=="-" || Caracter=="*" || Caracter=="/" || Caracter=="%" || Caracter=="Mod") {  //si es una operacion cambio las comienzo de nuevo obteniendo e
                operacion=Caracter                                                      //el operador dos
                reinicio=false // si mi caracter es un operador no se ha reiniciado la operacion, solo cambio de numero a operador
                num1=parcial.toDouble()
                parcial=""
                parcialstr+=Caracter  //
            }
            else{  //si entra a esta parte es por q la tecla ingresada es un num y el numero parcial sera el que hundio
                if(reinicio){ //si viene de terminar una operacion y se hundio un boton ya hay un numero parcial
                    parcial=Caracter
                    parcialstr=Caracter
                    reinicio=false // como ya reinicie debo volver a apagar la bandera
                }
                else{  // si no se reinicio es por
                    parcial+=Caracter
                    parcialstr+=Caracter
                }
            }
            tvResultado.text=parcialstr
        }
    }
}
