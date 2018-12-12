package com.dam18.project1.simondice


/**Esto hace que pueda llamar los botones por su id**/
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {

    var secuencia:ArrayList<String> = ArrayList()
    var secuenciaRecogida:ArrayList<String> = ArrayList()
    var go:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSec.setVisibility(View.INVISIBLE)
        btnRojo.setTag("BtnRojo")
        btnVerde.setTag("BtnVerde")
        btnAzul.setTag("BtnAzul")
        btnAmarillo.setTag("BtnAmarillo")

        btnJugar.setOnClickListener {
            secuencia=generarSecuencia()
            empezarSecuencia(secuencia)
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
        //TODO
        val sec:ArrayList<String> = ArrayList()
        sec.add("BtnRojo")
        return sec
    }

    fun empezarSecuencia(sec:ArrayList<String>) {
        val rojoOscuro = Color.parseColor("#FFCC0000");
        val rojoClaro = Color.parseColor("#FFFF4444");

        val handler1 = Handler()
        handler1.postDelayed(Runnable {
            btnRojo.setBackgroundColor(rojoClaro)
        }, 500)

        val handler2 = Handler()
        handler2.postDelayed(Runnable {
            btnRojo.setBackgroundColor(rojoOscuro)
        }, 1000)

        val handler3 = Handler()
        handler3.postDelayed(Runnable {
            toast("GO!")
            go=true
        }, 2000)
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
        var correcto:Boolean =  false
        if(secuencia.equals(secuenciaRecogida)){
            correcto = true
        }
        if (correcto){
            toast("SECUENCIA CORRECTA!!!!")
        }else{
            toast("OH NO!! SECUENCIA INCORRECTA")
        }
        btnSec.setVisibility(View.INVISIBLE)
        secuenciaRecogida = ArrayList()
    }
}
