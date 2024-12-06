package com.example.proyectofinal_cristinajasonfabricio

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues

class SQLManager(context: Context) : SQLiteOpenHelper(context, "sabores_conscientes.db", null, 1) {

    // Método para crear la base de datos
    override fun onCreate(db: SQLiteDatabase?) {
        // Tabla de negocios
        db?.execSQL("""
            CREATE TABLE negocios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                ciudad TEXT NOT NULL,
                tipo_comida TEXT NOT NULL
            )
        """.trimIndent())

        // Tabla de ofertas
        db?.execSQL("""
            CREATE TABLE ofertas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                id_negocio INTEGER NOT NULL,
                descripcion TEXT NOT NULL,
                precio REAL NOT NULL,
                tiempo_restante TEXT NOT NULL,
                FOREIGN KEY(id_negocio) REFERENCES negocios (id)
            )
        """.trimIndent())
        db?.execSQL("""
    CREATE TABLE usuarios (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        nombre TEXT NOT NULL,
        correo TEXT NOT NULL UNIQUE,
        contrasena TEXT NOT NULL,
        preferencias TEXT
    )
""".trimIndent())
    }

    // Método para actualizar la base de datos (gestión de versiones)
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ofertas")
        db?.execSQL("DROP TABLE IF EXISTS negocios")
        db?.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)
    }

    // Método para insertar un negocio
    fun insertarNegocio(nombre: String, ciudad: String, tipoComida: String): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("nombre", nombre)
        values.put("ciudad", ciudad)
        values.put("tipo_comida", tipoComida)

        val resultado = db.insert("negocios", null, values)
        //db.close()
        return resultado != -1L
    }

    // Método para insertar una oferta
    fun insertarOferta(idNegocio: Int, descripcion: String, precio: Double, tiempoRestante: String): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("id_negocio", idNegocio)
        values.put("descripcion", descripcion)
        values.put("precio", precio)
        values.put("tiempo_restante", tiempoRestante)

        val resultado = db.insert("ofertas", null, values)
        //db.close()
        return resultado != -1L
    }

    // Método para obtener todos los negocios
    fun obtenerNegocios(): List<Map<String, String>> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM negocios", null)
        val listaNegocios = mutableListOf<Map<String, String>>()

        if (cursor.moveToFirst()) {
            do {
                val negocio = mapOf(
                    "id" to cursor.getInt(cursor.getColumnIndexOrThrow("id")).toString(),
                    "nombre" to cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    "ciudad" to cursor.getString(cursor.getColumnIndexOrThrow("ciudad")),
                    "tipo_comida" to cursor.getString(cursor.getColumnIndexOrThrow("tipo_comida"))
                )
                listaNegocios.add(negocio)
            } while (cursor.moveToNext())
        }
        cursor.close()
       // db.close()
        return listaNegocios
    }

    fun obtenerUsuarioPorCorreo(correo: String): Usuario? {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM usuarios WHERE correo = ?",
            arrayOf(correo)
        )

        var usuario: Usuario? = null
        if (cursor.moveToFirst()) {
            usuario = Usuario(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                correo = cursor.getString(cursor.getColumnIndexOrThrow("correo")),
                contrasena = cursor.getString(cursor.getColumnIndexOrThrow("contrasena")),
                preferencias = cursor.getString(cursor.getColumnIndexOrThrow("preferencias"))
            )
        }
        cursor.close()
        return usuario
    }


    fun obtenerOfertasFiltradas(ciudad: String, tipoComida: String): List<Oferta> {
        val db = readableDatabase
        val query = """
        SELECT o.id, o.descripcion, o.precio, o.tiempo_restante, o.id_negocio 
        FROM ofertas o
        INNER JOIN negocios n ON o.id_negocio = n.id
        WHERE n.ciudad LIKE ? AND n.tipo_comida LIKE ?
    """.trimIndent()

        val cursor = db.rawQuery(query, arrayOf("%$ciudad%", "%$tipoComida%"))
        val listaOfertas = mutableListOf<Oferta>()

        if (cursor.moveToFirst()) {
            do {
                val oferta = Oferta(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    idNegocio = cursor.getInt(cursor.getColumnIndexOrThrow("id_negocio")),
                    descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion")),
                    precio = cursor.getDouble(cursor.getColumnIndexOrThrow("precio")),
                    tiempoRestante = cursor.getString(cursor.getColumnIndexOrThrow("tiempo_restante"))
                )
                listaOfertas.add(oferta)
            } while (cursor.moveToNext())
        }
        cursor.close()
        //db.close()
        return listaOfertas
    }
    // Método obtenerOfertaPorId
    fun obtenerOfertaPorId(ofertaId: Int): Oferta? {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM ofertas WHERE id = ?",
            arrayOf(ofertaId.toString())
        )

        var oferta: Oferta? = null
        if (cursor.moveToFirst()) {
            oferta = Oferta(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                idNegocio = cursor.getInt(cursor.getColumnIndexOrThrow("id_negocio")),
                descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion")),
                precio = cursor.getDouble(cursor.getColumnIndexOrThrow("precio")),
                tiempoRestante = cursor.getString(cursor.getColumnIndexOrThrow("tiempo_restante"))
            )
        }
        cursor.close()
        //db.close()
        return oferta
    }

    // Método insertarUsuario
    fun insertarUsuario(nombre: String, correo: String, contrasena: String, preferencias: String): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("nombre", nombre)
        values.put("correo", correo)
        values.put("contrasena", contrasena)
        values.put("preferencias", preferencias)

        val resultado = db.insert("usuarios", null, values)
        //db.close()
        return resultado != -1L
    }
}