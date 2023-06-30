package com.adairm.centroentrenamiento.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adairm.centroentrenamiento.dataBase.entidades.ServicioDetalles
import com.adairm.centroentrenamiento.dataBase.entidades.Servicios
import com.adairm.centroentrenamiento.dataBase.repository.GestiosPagosRepository
import com.adairm.centroentrenamiento.databinding.ItemListServiciosBinding

    class AdapterServicios (private val listServicios:List<ServicioDetalles>):
    RecyclerView.Adapter<AdapterServicios.ViewHolderClientes>(), View.OnClickListener{

    lateinit var listener: View.OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClientes {
        val binding = ItemListServiciosBinding.inflate(LayoutInflater.from(parent.context))
        binding.root.setOnClickListener(this)
        return ViewHolderClientes(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderClientes, position: Int) {
        holder.asignarDatos(listServicios.get(position))
    }

    override fun getItemCount(): Int {
        return listServicios.size
    }

    class ViewHolderClientes(binding: ItemListServiciosBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemListServiciosBinding

        init {
            this.binding = binding
        }

        fun asignarDatos(servicio: ServicioDetalles) {
            binding.edtConcepto.text = servicio.conceptoCobro.nombreConceptoCobro
            binding.edtMonto.text = servicio.servicios.montoCobro.toString()
            binding.edtDescripcion.text = servicio.servicios.descripcionCobro
        }
    }

    fun setOnClickListener(listener: View.OnClickListener){
        this.listener = listener
    }

    override fun onClick(v: View?) {
        if (listener != null){
            listener.onClick(v)
        }
    }

}