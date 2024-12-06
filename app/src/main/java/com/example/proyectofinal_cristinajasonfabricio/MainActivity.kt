package com.example.proyectofinal_cristinajasonfabricio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Declaración de variables para los elementos de la interfaz
    private lateinit var etCorreo: EditText
    private lateinit var etContrasena: EditText
    private lateinit var btnIniciarSesion: Button
    private lateinit var tvRegistrarse: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // Asocia el layout de login

        // Vincula los elementos de la interfaz
        etCorreo = findViewById(R.id.etCorreo)
        etContrasena = findViewById(R.id.etContrasena)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        tvRegistrarse = findViewById(R.id.tvRegistrarse)

        // Configura el botón de inicio de sesión
        btnIniciarSesion.setOnClickListener {
            val correo = etCorreo.text.toString().trim()
            val contrasena = etContrasena.text.toString().trim()

            if (correo.isEmpty() || contrasena.isEmpty()) {
                // Validación de campos vacíos
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validar credenciales y redirigir según el tipo de usuario
            if (validarCredenciales(correo, contrasena)) {
                // La función validarCredenciales maneja la redirección, aquí no es necesario agregar nada más
            } else {
                // Mostrar mensaje de error si las credenciales son incorrectas
                Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }


        // Configura el enlace para registrarse
        tvRegistrarse.setOnClickListener {
            // Navega a la actividad de registro
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    // Método para validar credenciales (ejemplo básico)
    private fun validarCredenciales(correo: String, contrasena: String): Boolean {
        // Redirección para administrador
        if (correo == "admin@sabores.com" && contrasena == "admin123") {
            val intent = Intent(this, AdminActivity::class.java)
            startActivity(intent)
            finish() // Finaliza actividad actual
            return true
        }

        // Redirección para usuarios regulares
        val db = SQLManager(this)
        val usuario = db.obtenerUsuarioPorCorreo(correo)
        if (usuario?.verificarContrasena(contrasena) == true) {
            val intent = Intent(this, MainOfertasActivity::class.java)
            startActivity(intent)
            finish() // Finaliza actividad actual
            return true
        }

        // Si las credenciales no son válidas
        return false
    }




}