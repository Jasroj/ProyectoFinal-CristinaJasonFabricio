package com.example.proyectofinal_cristinajasonfabricio

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdminActivity : AppCompatActivity() {

    private lateinit var etNombreProducto: EditText
    private lateinit var etDescripcionProducto: EditText
    private lateinit var etPrecioProducto: EditText
    private lateinit var btnAgregarProducto: Button
    private lateinit var sqlManager: SQLManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        // Vincular vistas
        etNombreProducto = findViewById(R.id.etNombreProducto)
        etDescripcionProducto = findViewById(R.id.etDescripcionProducto)
        etPrecioProducto = findViewById(R.id.etPrecioProducto)
        btnAgregarProducto = findViewById(R.id.btnAgregarProducto)

        sqlManager = SQLManager(this)

        btnAgregarProducto.setOnClickListener {
            val nombre = etNombreProducto.text.toString()
            val descripcion = etDescripcionProducto.text.toString()
            val precio = etPrecioProducto.text.toString().toDoubleOrNull()

            if (nombre.isNotEmpty() && descripcion.isNotEmpty() && precio != null) {
                val exito = sqlManager.insertarOferta(1, descripcion, precio, "24 horas") // Cambiar ID de negocio si aplica
                if (exito) {
                    Toast.makeText(this, "Producto agregado con éxito", Toast.LENGTH_SHORT).show()
                    limpiarCampos()
                } else {
                    Toast.makeText(this, "Error al agregar el producto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun limpiarCampos() {
        etNombreProducto.text.clear()
        etDescripcionProducto.text.clear()
        etPrecioProducto.text.clear()
    }
}

