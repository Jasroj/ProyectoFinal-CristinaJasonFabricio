package com.example.proyectofinal_cristinajasonfabricio

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etContrasena: EditText
    private lateinit var etPreferencias: EditText
    private lateinit var btnRegistrarse: Button
    private lateinit var sqlManager: SQLManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializa la base de datos
        sqlManager = SQLManager(this)

        // Vincula elementos
        etNombre = findViewById(R.id.etNombre)
        etCorreo = findViewById(R.id.etCorreo)
        etContrasena = findViewById(R.id.etContrasena)
        etPreferencias = findViewById(R.id.etPreferencias)
        btnRegistrarse = findViewById(R.id.btnRegistrarse)

        // Configura el botón de registrar
        btnRegistrarse.setOnClickListener {
            val nombre = etNombre.text.toString()
            val correo = etCorreo.text.toString()
            val contrasena = etContrasena.text.toString()
            val preferencias = etPreferencias.text.toString()

            if (nombre.isNotEmpty() && correo.isNotEmpty() && contrasena.isNotEmpty()) {
                val exito = sqlManager.insertarUsuario(nombre, correo, contrasena, preferencias)
                if (exito) {
                    Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}