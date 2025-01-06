package com.example.calccargas2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import androidx.fragment.app.Fragment

class DynamicFragment :Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragmento
        return inflater.inflate(R.layout.fragment_dynamic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etPeso = view.findViewById<EditText>(R.id.etPeso_dym)
        val etAceleracion = view.findViewById<EditText>(R.id.etAceleracion_dym)
        val etArea = view.findViewById<EditText>(R.id.etArea_dym)
        val etVelocidadViento = view.findViewById<EditText>(R.id.etVelocidadViento_dym)
        val btnCalcular = view.findViewById<Button>(R.id.btnCalcular_dym)
        val tvResultado = view.findViewById<TextView>(R.id.tvResultado_dym)

        btnCalcular.setOnClickListener {
            val peso = etPeso.text.toString().toDoubleOrNull() ?: 0.0
            val aceleracion = etAceleracion.text.toString().toDoubleOrNull() ?: 0.0
            val area = etArea.text.toString().toDoubleOrNull() ?: 0.0
            val velocidadViento = etVelocidadViento.text.toString().toDoubleOrNull() ?: 0.0

            val gravedad = 9.81
            val masa = peso // En kg
            val densidadAire = 1.225 // kg/m³
            val coefArrastre = 1.2 // Aproximado para carga rectangular

            val fuerzaViento = coefArrastre * area * 0.5 * densidadAire * velocidadViento.pow(2)
            val fuerzaDinamica = (masa * aceleracion) + fuerzaViento
            val fuerzaTotal = (masa * gravedad) + fuerzaDinamica

            tvResultado.text = """
                Fuerza por viento: ${"%.2f".format(fuerzaViento)} N
                Fuerza dinámica adicional: ${"%.2f".format(fuerzaDinamica)} N
                Fuerza total: ${"%.2f".format(fuerzaTotal)} N
            """.trimIndent()
        }
    }
}