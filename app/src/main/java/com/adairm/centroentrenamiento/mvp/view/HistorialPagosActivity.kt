package com.adairm.centroentrenamiento.mvp.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.adairm.centroentrenamiento.adapters.AdapterClientes
import com.adairm.centroentrenamiento.adapters.AdapterPrestamosServicio
import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.dataBase.entidades.PrestamoServiciosDetalles
import com.adairm.centroentrenamiento.databinding.ActivityHistorialPagosBinding
import com.adairm.centroentrenamiento.mvp.interfaces.views.IHistorialPagosActivity
import com.adairm.centroentrenamiento.mvp.presenter.PresenterHistorialPagosActivity
import kotlinx.coroutines.launch

class HistorialPagosActivity : AppCompatActivity(), IHistorialPagosActivity {
    private lateinit var binding: ActivityHistorialPagosBinding
    private lateinit var presenter: PresenterHistorialPagosActivity
    private lateinit var adapterPrestamosServicio: AdapterPrestamosServicio
    private lateinit var adapterClientes: AdapterClientes
    private lateinit var prestamos: List<PrestamoServiciosDetalles>
    private lateinit var conceptos: List<String>
    private lateinit var clientes: List<Cliente>
    private var idC = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistorialPagosBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        presenter = PresenterHistorialPagosActivity(this, applicationContext)

        llenarSpinner()
        buscarCliente()

    }

    private fun llenarSpinner(){
        lifecycleScope.launch {
            presenter.obtenerConceptos()
            val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, conceptos)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerConcepto.adapter = adapter
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
                    binding.spinnerConcepto.setSelection(0)
                    val p = binding.rvClientes.getChildAdapterPosition(it)
                    idC = clientes[p].idCliente

                    binding.spinnerConcepto.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            mostrarPrestamos((position + 1), idC)
                        }
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                        }
                    }
                }
            }
        }
    }

    private fun mostrarPrestamos(p: Int, idCliente: Int){
        lifecycleScope.launch {
            presenter.buscarNombreConceptoPrestamo(binding.edtNombreCliente.text.toString(), p, idCliente)
            binding.rvPrestamos.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
            adapterPrestamosServicio = AdapterPrestamosServicio(prestamos)
            binding.rvPrestamos.adapter = adapterPrestamosServicio
            adapterPrestamosServicio.setOnClickListener{}

        }
    }

    override suspend fun devolverPrestamos(prestamos: List<PrestamoServiciosDetalles>) {
        this.prestamos = prestamos
    }

    override suspend fun devolverConceptos(conceptos: List<String>) {
        this.conceptos = conceptos
    }

    override suspend fun devolverClientes(clientes: List<Cliente>) {
        this.clientes = clientes
    }
}