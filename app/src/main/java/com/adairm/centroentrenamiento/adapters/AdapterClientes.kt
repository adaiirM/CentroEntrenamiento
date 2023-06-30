package com.adairm.centroentrenamiento.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.databinding.ItemListClientesBinding


class AdapterClientes (private val listClientes:List<Cliente>):
    RecyclerView.Adapter<AdapterClientes.ViewHolderClientes>(), View.OnClickListener{

    lateinit var listener: View.OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClientes {
        val binding = ItemListClientesBinding.inflate(LayoutInflater.from(parent.context))
        binding.root.setOnClickListener(this)
        return ViewHolderClientes(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderClientes, position: Int) {
        holder.asignarDatos(listClientes.get(position))
    }

    override fun getItemCount(): Int {
        return listClientes.size
    }

    class ViewHolderClientes(binding: ItemListClientesBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemListClientesBinding

        init {
            this.binding = binding
        }

        fun asignarDatos(cliente: Cliente) {
            binding.edtNombre.text = cliente.nombreCompleto
            binding.edtEdad.text = cliente.edad
            binding.edtTelefono.text = cliente.telefono
            binding.edtCorreo.text = cliente.correo
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