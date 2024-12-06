package com.example.proyectofinal_cristinajasonfabricio

class Usuario {
    var id: Int = 0
    var nombre: String = ""
    var correo: String = ""
    var contrasena: String = ""
    var preferencias: String = ""

    // Constructor principal con ID
    constructor(id: Int, nombre: String, correo: String, contrasena: String, preferencias: String) {
        this.id = id
        this.nombre = nombre
        this.correo = correo
        this.contrasena = contrasena
        this.preferencias = preferencias
    }

    // Constructor secundario sin ID
    constructor(nombre: String, correo: String, contrasena: String, preferencias: String) {
        this.nombre = nombre
        this.correo = correo
        this.contrasena = contrasena
        this.preferencias = preferencias
    }

    // Método para mostrar detalles del usuario
    fun mostrarDetalles() {
        println("ID: $id, Nombre: $nombre, Correo: $correo, Preferencias: $preferencias")
    }

    // Método para verificar la contraseña
    fun verificarContrasena(contrasenaIngresada: String): Boolean {
        return contrasenaIngresada == this.contrasena
    }
}