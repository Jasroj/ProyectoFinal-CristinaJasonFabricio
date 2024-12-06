package com.example.proyectofinal_cristinajasonfabricio

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetalleOfertaActivity : AppCompatActivity() {

    private lateinit var tvNombreNegocio: TextView
    private lateinit var tvDescripcion: TextView
    private lateinit var tvPrecio: TextView
    private lateinit var tvTiempoRestante: TextView
    private lateinit var btnReservar: Button
    private lateinit var sqlManager: SQLManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oferta_detalle)

        // Inicializa la base de datos
        sqlManager = SQLManager(this)

        // Vincula elementos
        tvNombreNegocio = findViewById(R.id.tvNombreNegocio)
        tvDescripcion = findViewById(R.id.tvDescripcionOferta)
        tvPrecio = findViewById(R.id.tvPrecio)
        tvTiempoRestante = findViewById(R.id.tvTiempoRestante)
        btnReservar = findViewById(R.id.btnReservar)

        // Obtén el ID de la oferta seleccionada
        val ofertaId = intent.getIntExtra("ofertaId", -1)

        if (ofertaId != -1) {
            cargarDetalles(ofertaId)
        }

        // Configura el botón de reservar
        btnReservar.setOnClickListener {
            // Implementa la lógica para la reserva
            reservarOferta(ofertaId)
        }
    }

    private fun cargarDetalles(ofertaId: Int) {
        val oferta = sqlManager.obtenerOfertaPorId(ofertaId)
        if (oferta != null) {
            tvNombreNegocio.text = oferta.idNegocio.toString() // Cambiar por nombre real
            tvDescripcion.text = oferta.descripcion
            tvPrecio.text = "₡${oferta.precio}"
            tvTiempoRestante.text = "Tiempo restante: ${oferta.tiempoRestante}"
        }
    }

    private fun reservarOferta(ofertaId: Int) {
        // Implementar la lógica de reserva
        // Ejemplo: Mostrar un mensaje de éxito
        Toast.makeText(this, "Oferta reservada con éxito", Toast.LENGTH_SHORT).show()
    }
}