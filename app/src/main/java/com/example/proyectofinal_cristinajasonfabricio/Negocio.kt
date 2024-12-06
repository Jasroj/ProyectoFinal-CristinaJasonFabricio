package com.example.proyectofinal_cristinajasonfabricio

class Negocio {
    var id: Int = 0
    var nombre: String = ""
    var ciudad: String = ""
    var tipoComida: String = ""

    constructor(id: Int, nombre: String, ciudad: String, tipoComida: String) {
        this.id = id
        this.nombre = nombre
        this.ciudad = ciudad
        this.tipoComida = tipoComida
    }

    // Constructor secundario sin ID
    constructor(nombre: String, ciudad: String, tipoComida: String) {
        this.nombre = nombre
        this.ciudad = ciudad
        this.tipoComida = tipoComida
    }

    // Método para mostrar detalles del restaurante
    fun mostrarDetalles() {
        println("ID: $id, Nombre: $nombre, Ciudad: $ciudad, Tipo de Comida: $tipoComida")
    }
}