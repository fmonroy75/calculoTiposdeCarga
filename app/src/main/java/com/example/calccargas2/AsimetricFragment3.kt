package com.example.calccargas2


import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.atan
import kotlin.math.cos
import androidx.fragment.app.Fragment
import kotlin.math.*

class AsimetricFragment3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragmento
        return inflater.inflate(R.layout.fragment_asimetric3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar vistas
        val etPeso = view.findViewById<EditText>(R.id.etPeso_asim)
        val etDistancia1 = view.findViewById<EditText>(R.id.etDistancia1_asim)
        val etDistancia2 = view.findViewById<EditText>(R.id.etDistancia2_asim)
        val etDistancia3 = view.findViewById<EditText>(R.id.etDistancia3_asim)
        val etLongitud1 = view.findViewById<EditText>(R.id.etLongitud1_asim)
        val etLongitud2 = view.findViewById<EditText>(R.id.etLongitud2_asim)
        val etLongitud3 = view.findViewById<EditText>(R.id.etLongitud3_asim)
        val etAltura = view.findViewById<EditText>(R.id.etAltura_asim)
        val btnCalcular = view.findViewById<Button>(R.id.btnCalcular_asim)
        val tvResultado = view.findViewById<TextView>(R.id.tvResultado_asim)

        // Establecer la acción del botón para realizar el cálculo
        btnCalcular.setOnClickListener {
            val peso = etPeso.text.toString().toDoubleOrNull() ?: 0.0
            val distancia1 = etDistancia1.text.toString().toDoubleOrNull() ?: 0.0
            val distancia3 = etDistancia2.text.toString().toDoubleOrNull() ?: 0.0
            val distancia2 = etDistancia2.text.toString().toDoubleOrNull() ?: 0.0
            val longitud1 = etLongitud1.text.toString().toDoubleOrNull() ?: 0.0
            val longitud2 = etLongitud2.text.toString().toDoubleOrNull() ?: 0.0
            val longitud3 = etLongitud2.text.toString().toDoubleOrNull() ?: 0.0
            val altura = etAltura.text.toString().toDoubleOrNull() ?: 0.0

            // Calcular los ángulos de los tirantes usando longitud real
            val angulo1 = Math.toDegrees(asin(altura / longitud1))
            val angulo2 = Math.toDegrees(asin(altura / longitud2))
            val angulo3 = Math.toDegrees(asin(altura / longitud3))

            // Calcular las tensiones en los tirantes
            val tension1 = ((peso  * distancia2*distancia3) / (distancia1*(distancia1 + distancia2+distancia3))) * (1 / cos(Math.toRadians(angulo1)))
            val tension2 = ((peso  * distancia1*distancia3) / (distancia2*(distancia1 + distancia2+distancia3))) * (1 / cos(Math.toRadians(angulo2)))
            val tension3 = ((peso  * distancia1*distancia2) / (distancia3*(distancia1 + distancia2+distancia3))) * (1 / cos(Math.toRadians(angulo3)))
            val tension1kg=tension1/9.81
            val tension2kg=tension2/9.81
            val tension3kg=tension3/9.81
            // Mostrar resultados
            val resultado = "Tensión en Tirante 1: %.2f N =>  %.2f Kg \nAngulo 1: %.2f\nTensión en Tirante 2: %.2f N =>  %.2f Kg\nAngulo 2: %.2f\nTensión en Tirante 3: %.2f N =>  %.2f Kg\nAngulo 3: %.2f".format(tension1,tension1kg,angulo1, tension2,tension2kg,angulo2,tension3,tension3kg,angulo3)
            tvResultado.text = resultado
        }
    }
}