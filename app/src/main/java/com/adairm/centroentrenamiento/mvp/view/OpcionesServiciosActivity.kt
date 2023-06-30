package com.adairm.centroentrenamiento.mvp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.adairm.centroentrenamiento.dataBase.entidades.Servicios
import com.adairm.centroentrenamiento.dataBase.repository.GestiosPagosRepository
import com.adairm.centroentrenamiento.databinding.ActivityOpcionesServiciosBinding
import kotlinx.coroutines.launch

class OpcionesServiciosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOpcionesServiciosBinding
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpcionesServiciosBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        GestiosPagosRepository.inicializar(this)
        val db = GestiosPagosRepository.get()

        id = intent.getIntExtra("id_servicio", 0)
        llenarSpinner(db)
        llenarCampos(db)
        eliminarServicio(db)
        actualizarCliente(db)
    }

    private fun llenarCampos(db: GestiosPagosRepository){
        lifecycleScope.launch {
            val servicios = db.buscarServicioPorId(id)
            binding.spinnerConcepto.setSelection(servicios.idConceptoCobro - 1)
            binding.edtMontoCobro.setText(servicios.montoCobro.toString())
            binding.edtDescripcionCobro.setText(servicios.descripcionCobro)
        }
    }

    private fun llenarSpinner(db: GestiosPagosRepository){
        lifecycleScope.launch {
            val miArreglo = db.obtenerConceptos()
            val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, miArreglo)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerConcepto.adapter = adapter
        }
    }

    private fun eliminarServicio(db: GestiosPagosRepository){
        binding.btnEliminar.setOnClickListener {
            lifecycleScope.launch {
                db.eliminarServicio(db.buscarServicioPorId(id))
                intent = Intent(applicationContext, VerServiciosActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun actualizarCliente(db: GestiosPagosRepository){
        binding.btnActualizar.setOnClickListener {
            lifecycleScope.launch {
                if(validar()){
                    val servicio = Servicios(id,
                        (binding.spinnerConcepto.selectedItemPosition + 1),
                        binding.edtMontoCobro.text.toString().toDouble(),
                        binding.edtDescripcionCobro.text.toString()
                    )
                    db.actualizarServicio(servicio)
                    intent = Intent(applicationContext, VerServiciosActivity::class.java)
                    startActivity(intent)
                    limpiarCampos()
                }
            }
        }
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

}