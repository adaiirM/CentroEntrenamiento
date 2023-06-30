package com.adairm.centroentrenamiento.mvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.adairm.centroentrenamiento.dataBase.entidades.Servicios
import com.adairm.centroentrenamiento.databinding.ActivityFormServicioNuevoBinding
import com.adairm.centroentrenamiento.mvp.interfaces.views.IFormServicioNuevoActivity
import com.adairm.centroentrenamiento.mvp.presenter.PresenterFormServicioNuevoActivity
import kotlinx.coroutines.launch

class FormServicioNuevoActivity : AppCompatActivity(), IFormServicioNuevoActivity{
    private lateinit var binding: ActivityFormServicioNuevoBinding
    private lateinit var presenter: PresenterFormServicioNuevoActivity
    private lateinit var conceptos: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormServicioNuevoBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        presenter = PresenterFormServicioNuevoActivity(this, applicationContext)

        llenarSpinner()
        btnAceptar()
    }


    private fun llenarSpinner(){
        lifecycleScope.launch {
            presenter.obtenerConceptos()
            val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, conceptos)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerConcepto.adapter = adapter
        }
    }

    private fun btnAceptar() {
        binding.btnAceptar.setOnClickListener {
            if(validar()){
                lifecycleScope.launch {
                    insertarServicio()
                    Toast.makeText(applicationContext, "Servicio insertado correctamente", Toast.LENGTH_SHORT).show()
                    limpiarCampos()
                }
            }
        }
    }

    private suspend fun insertarServicio(){
        val servicio = Servicios(
            (binding.spinnerConcepto.selectedItemPosition + 1),
            binding.edtMontoCobro.text.toString().toDouble(),
            binding.edtDescripcionCobro.text.toString()
        )
        presenter.insertarServicio(servicio)
    }

    private fun validar(): Boolean{
        var flag = true
        if (binding.edtMontoCobro.text.toString() == "" || binding.edtDescripcionCobro.text.toString() == ""){
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            flag = false
        }
        return flag
    }

    private fun limpiarCampos(){
        binding.spinnerConcepto.setSelection(0)
        binding.edtMontoCobro.setText("")
        binding.edtDescripcionCobro.setText("")
    }

    override suspend fun devolverConceptos(conceptos: List<String>) {
        this.conceptos = conceptos
    }

}