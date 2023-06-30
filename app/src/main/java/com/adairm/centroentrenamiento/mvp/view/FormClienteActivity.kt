package com.adairm.centroentrenamiento.mvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.adairm.centroentrenamiento.adapters.AdapterCodigos
import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.databinding.ActivityFormClienteBinding
import com.adairm.centroentrenamiento.mvp.interfaces.views.IFormClienteActivity
import com.adairm.centroentrenamiento.mvp.presenter.PresenterFormClienteActivity
import com.adairm.centroentrenamiento.retrofit.Codigo
import com.adairm.ejercicio3er.Utils.Apis
import com.adairm.ejercicio3er.Utils.CodigoService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormClienteActivity : AppCompatActivity(), IFormClienteActivity{
    private lateinit var binding: ActivityFormClienteBinding
    private lateinit var codigoService: CodigoService
    private var listCodigo = ArrayList<Codigo>()
    private var adapterCodigos = AdapterCodigos(listCodigo)
    private lateinit var presenter: PresenterFormClienteActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormClienteBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        presenter = PresenterFormClienteActivity(this, applicationContext)

        aceptar()
        buscarPorCodigo()
        recyclerVacio()
    }

    private fun aceptar(){
        binding.faAceptar.setOnClickListener {
            lifecycleScope.launch {
                if (validar()){
                    insertarCliente()
                    Toast.makeText(applicationContext, "Cliente insertado correctamente", Toast.LENGTH_SHORT).show()
                    limpiarCampos()
                }
            }
        }
    }

    private suspend fun insertarCliente(){
        val cliente = Cliente(
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
        presenter.insertarCliente(cliente)
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

    private fun validar(): Boolean{
        var flag = true
        if (binding.edtNombre.text.toString() == "" || binding.edtApellidoPaterno.text.toString() == ""
            || binding.edtApellidoMaterno.text.toString() == "" || binding.edtEdad.text.toString() == ""
            || binding.edtPeso.text.toString() == "" || binding.edtEstatura.text.toString() == ""
            || binding.edtPadecimiento.text.toString() == "" || binding.edtConsumoMed.text.toString() == ""
            || binding.edtPresionArterial.text.toString() == "" || binding.edtCodigoPostal.text.toString() == ""
            || binding.edtColonia.text.toString() == "" || binding.edtEstado.text.toString() == ""
            || binding.edtTelefono.text.toString() == "" || binding.edtCorreo.text.toString() == ""){
            mostrarText("Rellena todos los campos")
            flag = false
        }else{
            if (tieneEspacios(binding.edtNombre.text.toString()) || tieneEspacios(binding.edtApellidoPaterno.text.toString())
                || tieneEspacios(binding.edtApellidoMaterno.text.toString())){
                mostrarText("No insertes espacios en datos personales")
                flag = false
            }
        }
        return flag
    }

    private fun limpiarCampos(){
        binding.edtNombre.setText("")
        binding.edtApellidoPaterno.setText("")
        binding.edtApellidoMaterno.setText("")
        binding.edtEdad.setText("")
        binding.edtPeso.setText("")
        binding.edtEstatura.setText("")
        binding.edtPadecimiento.setText("")
        binding.edtConsumoMed.setText("")
        binding.edtPresionArterial.setText("")
        binding.edtCodigoPostal.setText("")
        binding.edtColonia.setText("")
        binding.edtEstado.setText("")
        binding.edtTelefono.setText("")
        binding.edtCorreo.setText("")
    }

    private fun tieneEspacios(text: String): Boolean {
        val patron = "\\s".toRegex()
        return patron.containsMatchIn(text)
    }

    private fun mostrarText(texto: String){
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
    }

    private fun recyclerVacio(){
        if(adapterCodigos.itemCount == 0){
            binding.emptyTextViewCodigos.visibility = View.VISIBLE
        }else{
            binding.emptyTextViewCodigos.visibility = View.GONE
        }
    }
}