package com.example.proyectofinal_cristinajasonfabricio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainOfertasActivity : AppCompatActivity() {

    private lateinit var etBuscarCiudad: EditText
    private lateinit var etBuscarComida: EditText
    private lateinit var btnFiltrar: Button
    private lateinit var rvOfertas: RecyclerView
    private lateinit var adapter: OfertasAdapter
    private lateinit var sqlManager: SQLManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_ofertas)

        // Inicializa el SQLManager
        sqlManager = SQLManager(this)

        // Vincula las vistas
        etBuscarCiudad = findViewById(R.id.etBuscarCiudad)
        etBuscarComida = findViewById(R.id.etBuscarComida)
        btnFiltrar = findViewById(R.id.btnFiltrar)
        rvOfertas = findViewById(R.id.rvOfertas)

        // Configura el RecyclerView
        rvOfertas.layoutManager = LinearLayoutManager(this)
        adapter = OfertasAdapter(mutableListOf()) { oferta ->
            // Acción cuando se selecciona una oferta
            val intent = Intent(this, DetalleOfertaActivity::class.java)
            intent.putExtra("ofertaId", oferta.id)
            startActivity(intent)
        }
        rvOfertas.adapter = adapter

        // Configura el botón de filtrar
        btnFiltrar.setOnClickListener {
            val ciudad = etBuscarCiudad.text.toString()
            val tipoComida = etBuscarComida.text.toString()
            cargarOfertas(ciudad, tipoComida)
        }

        // Carga inicial de ofertas
        cargarOfertas("", "")
    }

    private fun cargarOfertas(ciudad: String, tipoComida: String) {
        val listaDeOfertas = sqlManager.obtenerOfertasFiltradas(ciudad, tipoComida)
        adapter.actualizarLista(listaDeOfertas)
    }
}