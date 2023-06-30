package com.adairm.centroentrenamiento.mvp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.adairm.centroentrenamiento.adapters.AdapterClientes
import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.databinding.ActivityVerClientesBinding
import com.adairm.centroentrenamiento.mvp.interfaces.views.IVerClientesActivity
import com.adairm.centroentrenamiento.mvp.presenter.PresenterVerClientesActivity
import kotlinx.coroutines.launch

class VerClientesActivity : AppCompatActivity(), IVerClientesActivity {
    private lateinit var binding: ActivityVerClientesBinding
    private lateinit var presenter: PresenterVerClientesActivity
    private lateinit var adapterClientes: AdapterClientes
    private lateinit var clientes: List<Cliente>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerClientesBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        presenter = PresenterVerClientesActivity(this, applicationContext)

        mostrarClientes()
        agregarClientes()
        buscarCliente()
    }

    private fun mostrarClientes(){
        //Inicia un corrutina para ejecutar los metodos de la base de datos
        lifecycleScope.launch {
            //Mediante los metodos del presenter se manda un llamada para devolver los clientes
            presenter.obtenerClientes()
            binding.rvClientes.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
            adapterClientes = AdapterClientes(clientes)
            binding.rvClientes.adapter = adapterClientes
            adapterClientes.setOnClickListener{
                val p = binding.rvClientes.getChildAdapterPosition(it)
                val id = clientes[p].idCliente
                val intent = Intent(applicationContext, OpcionesClienteActivity::class.java)
                intent.putExtra("id_cliente", id)
                startActivity(intent)
            }
            if(adapterClientes.itemCount == 0){
                binding.emptyTextView.visibility = View.VISIBLE
            }else{
                binding.emptyTextView.visibility = View.GONE
            }
        }
    }

    private fun buscarCliente(){
        binding.btnBuscarClientes.setOnClickListener {
            lifecycleScope.launch {
                presenter.buscarPorNombre(binding.edtNombreCliente.text.toString())
                binding.rvClientes.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
                adapterClientes = AdapterClientes(clientes)
                binding.rvClientes.adapter = adapterClientes
                adapterClientes.setOnClickListener{
                    val p = binding.rvClientes.getChildAdapterPosition(it)
                    val id = clientes[p].idCliente
                    val intent = Intent(applicationContext, OpcionesClienteActivity::class.java)
                    intent.putExtra("id_cliente", id)
                    startActivity(intent)
                }
            }
        }
    }

    private fun agregarClientes(){
        binding.faAgregar.setOnClickListener {
            intent = Intent(this, FormClienteActivity::class.java)
            startActivity(intent)
        }
    }

    override suspend fun devolverClientes(clientes: List<Cliente>) {
        this.clientes = clientes
    }


}