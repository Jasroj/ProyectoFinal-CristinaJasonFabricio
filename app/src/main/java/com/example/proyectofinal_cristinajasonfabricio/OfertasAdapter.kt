package com.example.proyectofinal_cristinajasonfabricio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OfertasAdapter(
    private var listaOfertas: MutableList<Oferta>,
    private val onClick: (Oferta) -> Unit
) : RecyclerView.Adapter<OfertasAdapter.OfertaViewHolder>() {

    // ViewHolder para representar cada fila
    class OfertaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNegocio: TextView = itemView.findViewById(R.id.tvNegocio)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
        val tvPrecio: TextView = itemView.findViewById(R.id.tvPrecio)
    }

    // Infla el diseño de la fila
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfertaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_oferta, parent, false)
        return OfertaViewHolder(view)
    }

    // Conecta los datos con las vistas
    override fun onBindViewHolder(holder: OfertaViewHolder, position: Int) {
        val oferta = listaOfertas[position]
        holder.tvNegocio.text = oferta.idNegocio.toString() // Cambiar por el nombre real del negocio si está disponible
        holder.tvDescripcion.text = oferta.descripcion
        holder.tvPrecio.text = "₡${oferta.precio}"

        // Configura el clic en la fila
        holder.itemView.setOnClickListener {
            onClick(oferta)
        }
    }

    // Tamaño de la lista
    override fun getItemCount(): Int = listaOfertas.size

    // Método para actualizar la lista
    fun actualizarLista(nuevaLista: List<Oferta>) {
        listaOfertas.clear()
        listaOfertas.addAll(nuevaLista)
        notifyDataSetChanged()
    }
}