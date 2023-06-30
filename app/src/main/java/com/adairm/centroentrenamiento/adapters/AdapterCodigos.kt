package com.adairm.centroentrenamiento.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adairm.centroentrenamiento.databinding.ItemListCodigosPostalesBinding
import com.adairm.centroentrenamiento.retrofit.Codigo

class AdapterCodigos (private val listCodigos:List<Codigo>):
    RecyclerView.Adapter<AdapterCodigos.ViewHolderCodigos>(), View.OnClickListener{

    lateinit var listener: View.OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCodigos {
        val binding = ItemListCodigosPostalesBinding.inflate(LayoutInflater.from(parent.context))
        binding.root.setOnClickListener(this)
        return ViewHolderCodigos(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderCodigos, position: Int) {
        holder.asignarDatos(listCodigos.get(position))
    }

    override fun getItemCount(): Int {
        return listCodigos.size
    }

    class ViewHolderCodigos(binding: ItemListCodigosPostalesBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemListCodigosPostalesBinding

        init {
            this.binding = binding
        }

        fun asignarDatos(servicio: Codigo) {
            binding.edtCodigoPostal.text = servicio.codigoPostal
            binding.edtColonia.text = servicio.ciudad
            binding.edtEstado.text = servicio.estado
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