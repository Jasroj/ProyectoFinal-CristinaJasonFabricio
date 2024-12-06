package com.example.proyectofinal_cristinajasonfabricio

class Oferta {
    var id: Int = 0
    var idNegocio: Int = 0
    var descripcion: String = ""
    var precio: Double = 0.0
    var tiempoRestante: String = ""

    // Constructor principal
    constructor(id: Int, idNegocio: Int, descripcion: String, precio: Double, tiempoRestante: String) {
        this.id = id
        this.idNegocio = idNegocio
        this.descripcion = descripcion
        this.precio = precio
        this.tiempoRestante = tiempoRestante
    }

    // Constructor secundario (sin ID)
    constructor(idNegocio: Int, descripcion: String, precio: Double, tiempoRestante: String) {
        this.idNegocio = idNegocio
        this.descripcion = descripcion
        this.precio = precio
        this.tiempoRestante = tiempoRestante
    }

    // Método para mostrar detalles de la oferta
    fun mostrarDetalles() {
        println("ID: $id, Negocio ID: $idNegocio, Descripción: $descripcion, Precio: $precio, Tiempo Restante: $tiempoRestante")
    }
}