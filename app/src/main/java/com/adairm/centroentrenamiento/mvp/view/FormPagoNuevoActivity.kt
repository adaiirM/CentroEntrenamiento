package com.adairm.centroentrenamiento.mvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.adairm.centroentrenamiento.adapters.AdapterClientes
import com.adairm.centroentrenamiento.adapters.AdapterServicios
import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.dataBase.entidades.PrestamoServicios
import com.adairm.centroentrenamiento.dataBase.entidades.ServicioDetalles
import com.adairm.centroentrenamiento.databinding.ActivityFormPagoNuevoBinding
import com.adairm.centroentrenamiento.mvp.interfaces.views.IFormPagoNuevoActivity
import com.adairm.centroentrenamiento.mvp.presenter.PresenterFormPagoNuevoActivity
import kotlinx.coroutines.launch
import java.time.LocalDate

class FormPagoNuevoActivity : AppCompatActivity(), IFormPagoNuevoActivity {
    private lateinit var binding: ActivityFormPagoNuevoBinding
    private lateinit var adapterClientes: AdapterClientes
    private lateinit var adapterServ: AdapterServicios
    private lateinit var presenter: PresenterFormPagoNuevoActivity
    private lateinit var cliente: Cliente
    private lateinit var clientes: List<Cliente>
    private lateinit var servicios: List<ServicioDetalles>
    private lateinit var lista: List<String>
    private var idServicio = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormPagoNuevoBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        presenter = PresenterFormPagoNuevoActivity(this, applicationContext)


        mostrarClientesServicios()
        buscarCliente()
        binding.btnAceptar.setOnClickListener {
            lifecycleScope.launch {
                if (validar()){
                    agregarPagoServicio()
                    Toast.makeText(applicationContext, "Servicio insertado correctamente", Toast.LENGTH_SHORT).show()
                    if (binding.spinnerEstadoPago.selectedItemPosition == 0){
                        presenter.buscarPorId(binding.edtId.text.toString().toInt())
                        cliente.numAdeudos ++
                        presenter.actualizarCliente(cliente)
                    }
                    limpiarCampos()
                }
            }
        }
    }

    private fun mostrarClientesServicios(){
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
                binding.edtId.setText(id.toString())
            }
            if(adapterClientes.itemCount == 0){
                binding.emptyTextViewCliente.visibility = View.VISIBLE
            }else{
                binding.emptyTextViewCliente.visibility = View.GONE
            }
            llenarSpinner()

        }

        //Inicia un corrutina para ejecutar los metodos de la base de datos
        lifecycleScope.launch {
            //Mediante los metodos del presenter se manda un llamada para devolver los clientes
            presenter.obtenerServicios()
            binding.rvServicios.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
            adapterServ = AdapterServicios(servicios)
            binding.rvServicios.adapter = adapterServ
            adapterServ.setOnClickListener{
                val p = binding.rvServicios.getChildAdapterPosition(it)
                idServicio  = servicios[p].servicios.idServicio
                binding.edtMontoPago.setText(servicios[p].servicios.montoCobro.toString())
                binding.edtConceptoPago.setText(servicios[p].conceptoCobro.nombreConceptoCobro)
                binding.edtDescripcionCobro.setText(servicios[p].servicios.descripcionCobro)
                val fecha = LocalDate.now()
                binding.edtFechaInicio.setText(fecha.toString())

                if (servicios[p].conceptoCobro.idConceptoCobro.toString() == "1"){
                    binding.edtFechaCorte.setText(fecha.plusDays(1).toString())
                    binding.spinnerEstadoPago.setSelection(1)
                    binding.spinnerEstadoPago.isEnabled = false
                }
                if (servicios[p].conceptoCobro.idConceptoCobro.toString() == "2"){
                    binding.edtFechaCorte.setText(fecha.plusWeeks(1).toString())
                    binding.spinnerEstadoPago.isEnabled = true
                }
                if (servicios[p].conceptoCobro.idConceptoCobro.toString() == "3"){
                    binding.edtFechaCorte.setText(fecha.plusMonths(1).toString())
                    binding.spinnerEstadoPago.isEnabled = true
                }
            }
            if(adapterServ.itemCount == 0){
                binding.emptyTextViewServicios.visibility = View.VISIBLE
            }else{
                binding.emptyTextViewServicios.visibility = View.GONE
            }
        }
    }

    private fun llenarSpinner(){
        lifecycleScope.launch {
            presenter.obtenerEstadoPago()
            val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, lista)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerEstadoPago.adapter = adapter
        }
    }

    private fun buscarCliente(){
        binding.btnBuscarClientes.setOnClickListener {
            lifecycleScope.launch {
                presenter.buscarPorNombre(binding.edtNombreClientes.text.toString())
                binding.rvClientes.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
                adapterClientes = AdapterClientes(clientes)
                binding.rvClientes.adapter = adapterClientes
                adapterClientes.setOnClickListener{
                    val p = binding.rvClientes.getChildAdapterPosition(it)
                    val id = clientes[p].idCliente
                    binding.edtId.setText(id.toString())
                }
            }
        }
    }

    private suspend fun agregarPagoServicio(){
        val prestamoServicios = PrestamoServicios(
            binding.edtFechaInicio.text.toString(),
            binding.edtFechaCorte.text.toString(),
            binding.edtId.text.toString().toInt(),
            idServicio,
            (binding.spinnerEstadoPago.selectedItemPosition + 1)
        )

        presenter.insertarPrestamo(prestamoServicios)
    }

    private suspend fun validar(): Boolean{
        var flag = true
        if (binding.edtId.text.toString() == "" || binding.edtConceptoPago.text.toString() == ""
            || binding.edtMontoPago.text.toString() == "" || binding.edtDescripcionCobro.text.toString() == ""
            || binding.edtFechaInicio.text.toString() == "" || binding.edtFechaCorte.text.toString() == ""){
            mostrarText("Rellena todos los campos")
            flag = false
        }else{
            presenter.buscarPorId(binding.edtId.text.toString().toInt())
            if(cliente.numAdeudos > 3 && binding.spinnerEstadoPago.selectedItemPosition == 0){
                mostrarText("Tienes mas de 3 adeudos pendientes, realiza un pago de ellos para continuar")
                flag = false
            }
            return flag
        }
        return flag
    }

    private fun limpiarCampos(){
        binding.edtId.setText("")
        binding.edtConceptoPago.setText("")
        binding.edtMontoPago.setText("")
        binding.edtDescripcionCobro.setText("")
        binding.edtFechaInicio.setText("")
        binding.edtFechaInicio.setText("")
    }

    private fun mostrarText(texto: String){
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
    }

    override suspend fun devolverCliente(cliente: Cliente) {
        this.cliente = cliente
    }

    override suspend fun devolverClientes(clientes: List<Cliente>) {
        this.clientes = clientes
    }

    override suspend fun devolverServicios(servicios: List<ServicioDetalles>) {
        this.servicios = servicios
    }

    override suspend fun devolverEstadoPago(lista: List<String>) {
        this.lista = lista
    }

}