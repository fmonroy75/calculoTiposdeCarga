package com.example.calccargas2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlin.math.atan
import kotlin.math.cos
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SimetricFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el diseño del fragmento
        return inflater.inflate(R.layout.fragment_simetric, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etPeso = view.findViewById<EditText>(R.id.etPeso_sim)
        val etNumeroTirantes = view.findViewById<EditText>(R.id.etNumeroTirantes_sim)
        val etLongitudTirantes = view.findViewById<EditText>(R.id.etLongitudTirantes_sim)
        val etAltura = view.findViewById<EditText>(R.id.etAltura_sim)

        val btnCalcular: Button = view.findViewById(R.id.btnCalcular_sim)
        val tvResultado = view.findViewById<TextView>(R.id.tvResultado_sim)

        btnCalcular.setOnClickListener {
            val peso = etPeso.text.toString().toDoubleOrNull() ?: 0.0
            val numeroTirantes = etNumeroTirantes.text.toString().toIntOrNull() ?: 1
            val longitudTirantes = etLongitudTirantes.text.toString().toDoubleOrNull() ?: 1.0
            val altura = etAltura.text.toString().toDoubleOrNull() ?: 1.0

            val theta = atan(altura / longitudTirantes)
            val tension = peso / (numeroTirantes * cos(theta))
            val thetaDegrees = Math.toDegrees(theta)

            tvResultado.text = "Ángulo: %.2f°\nTensión: %.2f kgf".format(thetaDegrees, tension)
        }
    }
}
