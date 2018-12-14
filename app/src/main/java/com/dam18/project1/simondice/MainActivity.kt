package com.dam18.project1.simondice



import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
/**Esto hace que pueda llamar los botones por su id**/
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.util.*


class MainActivity : AppCompatActivity() {

    var secuencia:ArrayList<String> = ArrayList()
    var secuenciaRecogida:ArrayList<String> = ArrayList()
    var go:Boolean = false
    var nivel:Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSec.setVisibility(View.INVISIBLE)
        btnRojo.setTag("btnRojo")
        btnVerde.setTag("btnVerde")
        btnAzul.setTag("btnAzul")
        btnAmarillo.setTag("btnAmarillo")

        btnJugar.setOnClickListener {
            toast("Nivel "+nivel)
            secuencia=generarSecuencia()
            Log.d("SECUENCIA ---- ", secuencia.toString())
            empezarSecuencia()
        }

        btnRojo.setOnClickListener{
            if (go==true) {
                recogerSecuencia(it)
            }
        }
        btnVerde.setOnClickListener{
            if (go==true) {
                recogerSecuencia(it)
            }
        }
        btnAzul.setOnClickListener{
            if (go==true) {
                recogerSecuencia(it)
            }
        }
        btnAmarillo.setOnClickListener{
            if (go==true) {
                recogerSecuencia(it)
            }
        }
    }

    fun generarSecuencia():ArrayList<String>{
        val sec:ArrayList<String> = ArrayList()
        var boton = ""
        for (i in 0..nivel -1 step 1) {
            val x = Random().nextInt(4)
            when (x) {
                0 -> boton="btnRojo"
                1 -> boton="btnVerde"
                2 -> boton="btnAzul"
                3 -> boton="btnAmarillo"
            }
            sec.add(boton)
        }
        nivel++
        return sec
    }

    fun empezarSecuencia() {
        val rojoOscuro = Color.parseColor("#FFCC0000")
        val rojoClaro = Color.parseColor("#FFFF4444")
        val verdeOscuro = Color.parseColor("#FF669900")
        val verdeClaro = Color.parseColor("#FF99CC00")
        val azulOscuro = Color.parseColor("#FF0099CC")
        val azulClaro = Color.parseColor("#FF00DDFF")
        val amarilloOscuro = Color.parseColor("#FFFFBB33")
        val amarilloClaro = Color.parseColor("#FFFFE11E")

        val seg:Long=1000

        for ((index,valor) in secuencia.withIndex()){
            val handler1 = Handler()
            handler1.postDelayed({
                if (valor.equals("btnRojo")) {
                    btnRojo.setBackgroundColor(rojoClaro)
                }
                if (valor.equals("btnVerde")) {
                    btnVerde.setBackgroundColor(verdeClaro)
                }
                if (valor.equals("btnAzul")) {
                    btnAzul.setBackgroundColor(azulClaro)
                }
                if (valor.equals("btnAmarillo")) {
                    btnAmarillo.setBackgroundColor(amarilloClaro)
                }
            }, seg*(index+1))

            val handler2 = Handler()
            handler2.postDelayed({
                if (valor.equals("btnRojo")) {
                    btnRojo.setBackgroundColor(rojoOscuro)
                }
                if (valor.equals("btnVerde")) {
                    btnVerde.setBackgroundColor(verdeOscuro)
                }
                if (valor.equals("btnAzul")) {
                    btnAzul.setBackgroundColor(azulOscuro)
                }
                if (valor.equals("btnAmarillo")) {
                    btnAmarillo.setBackgroundColor(amarilloOscuro)
                }
            }, seg*(index+1)+500)
        }

        val handler3 = Handler()
        handler3.postDelayed({
            //toast("GO!")
            go=true
        }, seg*secuencia.size+500)
    }

    fun recogerSecuencia(view: View){
        val btn = view as Button
        secuenciaRecogida.add(btn.getTag().toString())

        btnSec.setVisibility(View.VISIBLE)

        btnSec.setOnClickListener {
            comprobarSecuencia()
        }
    }

    fun comprobarSecuencia(){
        var correcto = false
        if(secuencia.equals(secuenciaRecogida)){
            correcto = true
        }
        if (correcto){
            toast("SECUENCIA CORRECTA!!!!")
        }else{
            toast("OH NO!! SECUENCIA INCORRECTA")
            nivel=1
        }
        btnSec.setVisibility(View.INVISIBLE)
        secuenciaRecogida = ArrayList()
    }
}