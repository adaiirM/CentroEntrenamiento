package com.adairm.centroentrenamiento.mvp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.adairm.centroentrenamiento.adapters.AdapterCodigos
import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.databinding.ActivityOpcionesClienteBinding
import com.adairm.centroentrenamiento.mvp.interfaces.views.IOpcionesClienteActivity
import com.adairm.centroentrenamiento.mvp.presenter.PresenterOpcionesClienteActivity
import com.adairm.centroentrenamiento.retrofit.Codigo
import com.adairm.ejercicio3er.Utils.Apis
import com.adairm.ejercicio3er.Utils.CodigoService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OpcionesClienteActivity : AppCompatActivity(), IOpcionesClienteActivity {
    private lateinit var binding: ActivityOpcionesClienteBinding
    private lateinit var presenter: PresenterOpcionesClienteActivity
    private lateinit var cliente: Cliente
    private lateinit var codigoService: CodigoService
    private var listCodigo = ArrayList<Codigo>()
    private var adapterCodigos = AdapterCodigos(listCodigo)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpcionesClienteBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        presenter = PresenterOpcionesClienteActivity(this, applicationContext)

        llenarCampos()
        eliminarCliente()
        actualizarCliente()
        buscarPorCodigo()
        recyclerVacio()
    }

    private fun llenarCampos(){
        val d = intent.getIntExtra("id_cliente", 0)
        lifecycleScope.launch {
            presenter.buscarPorId(d)
            binding.edtNombre.setText(cliente.nombreCliente)
            binding.edtApellidoPaterno.setText(cliente.apellidoPaterno)
            binding.edtApellidoMaterno.setText(cliente.apellidoMaterno)
            binding.edtEdad.setText(cliente.edad)
            binding.edtPeso.setText(cliente.peso)
            binding.edtEstatura.setText(cliente.estatura)
            binding.edtPadecimiento.setText(cliente.padecimientoMedico)
            binding.edtConsumoMed.setText(cliente.consumoMedicamento)
            binding.edtPresionArterial.setText(cliente.presionArterial)
            binding.edtCodigoPostal.setText(cliente.codigoPostal)
            binding.edtColonia.setText(cliente.colonia)
            binding.edtEstado.setText(cliente.estado)
            binding.edtTelefono.setText(cliente.telefono)
            binding.edtCorreo.setText(cliente.correo)
        }
    }

    private fun eliminarCliente(){
        binding.btnEliminar.setOnClickListener {
            lifecycleScope.launch {
                val d = intent.getIntExtra("id_cliente", 0)
                presenter.buscarPorId(d)
                presenter.eliminarCliente(cliente)
                intent = Intent(applicationContext, VerClientesActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun actualizarCliente(){
        val d = intent.getIntExtra("id_cliente", 0)
        binding.btnActualizar.setOnClickListener {
            lifecycleScope.launch {
                val cliente = Cliente(d,
                    binding.edtNombre.text.toString(),
                    binding.edtApellidoPaterno.text.toString(),
                    binding.edtApellidoMaterno.text.toString(),
                    binding.edtEdad.text.toString(),
                    binding.edtPeso.text.toString(),
                    binding.edtEstatura.text.toString(),
                    binding.edtPadecimiento.text.toString(),
                    binding.edtConsumoMed.text.toString(),
                    binding.edtPresionArterial.text.toString(),
                    binding.edtCodigoPostal.text.toString(),
                    binding.edtColonia.text.toString(),
                    binding.edtEstado.text.toString(),
                    binding.edtTelefono.text.toString(),
                    binding.edtCorreo.text.toString()
                )
                presenter.actualizarCliente(cliente)
                intent = Intent(applicationContext, VerClientesActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun buscarPorCodigo(){
        binding.btnBuscarCod.setOnClickListener {
            listCodigo = ArrayList()
            codigoService = Apis.getCodigoService()
            val call: Call<List<Codigo>> = codigoService.getByCodigo(binding.edtNombreCod.text.toString())

            call.enqueue(object: Callback<List<Codigo>> {
                override fun onResponse(call: Call<List<Codigo>>, response: Response<List<Codigo>>) {
                    if (response.isSuccessful){
                        listCodigo = (response.body() as ArrayList<Codigo>?)!!
                        binding.rvCodigos.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                        adapterCodigos = AdapterCodigos(listCodigo)
                        adapterCodigos.setOnClickListener {
                            val p = binding.rvCodigos.getChildAdapterPosition(it)
                            binding.edtCodigoPostal.setText(listCodigo[p].codigoPostal)
                            binding.edtColonia.setText(listCodigo[p].ciudad)
                            binding.edtEstado.setText(listCodigo[p].estado)
                        }
                        binding.rvCodigos.adapter = adapterCodigos
                        recyclerVacio()
                    }

                }

                override fun onFailure(call: Call<List<Codigo>>, t: Throwable) {
                    Log.e("Error: ", t.message!!)
                }
            })
        }
    }

    override suspend fun devolverCliente(cliente: Cliente) {
        this.cliente = cliente
    }

    private fun recyclerVacio(){
        if(adapterCodigos.itemCount == 0){
            binding.emptyTextViewCodigos.visibility = View.VISIBLE
        }else{
            binding.emptyTextViewCodigos.visibility = View.GONE
        }
    }
}
