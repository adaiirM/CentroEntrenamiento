package com.adairm.centroentrenamiento.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adairm.centroentrenamiento.dataBase.entidades.PrestamoServicios
import com.adairm.centroentrenamiento.dataBase.entidades.PrestamoServiciosDetalles
import com.adairm.centroentrenamiento.dataBase.entidades.ServicioDetalles
import com.adairm.centroentrenamiento.databinding.ItemListPrestamosServicioBinding
import com.adairm.centroentrenamiento.databinding.ItemListServiciosBinding

class AdapterPrestamosServicio (private val listPrestamos:List<PrestamoServiciosDetalles>):
    RecyclerView.Adapter<AdapterPrestamosServicio.ViewHolderPrestamos>(), View.OnClickListener{

    lateinit var listener: View.OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPrestamos {
        val binding = ItemListPrestamosServicioBinding.inflate(LayoutInflater.from(parent.context))
        binding.root.setOnClickListener(this)
        return ViewHolderPrestamos(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderPrestamos, position: Int) {
        holder.asignarDatos(listPrestamos.get(position))
    }

    override fun getItemCount(): Int {
        return listPrestamos.size
    }

    class ViewHolderPrestamos(binding: ItemListPrestamosServicioBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemListPrestamosServicioBinding

        init {
            this.binding = binding
        }

        fun asignarDatos(servicio: PrestamoServiciosDetalles) {
            binding.edtNombre.text = servicio.cliente.nombreCompleto
            binding.edtEdad.text = servicio.cliente.edad
            binding.edtTelefono.text = servicio.cliente.telefono
            binding.edtCorreo.text = servicio.cliente.correo
            binding.edtDescripcionCobro.text = servicio.servicios.descripcionCobro
            binding.edtFechaInicio.text = servicio.prestamoServicios.fechaInicio
            binding.edtFechaCorte.text = servicio.prestamoServicios.fechaCorte
            binding.edtMontoPago.text = servicio.servicios.montoCobro.toString()
            binding.edtEstado.text = servicio.estadoPago.nombreEstadoPago
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