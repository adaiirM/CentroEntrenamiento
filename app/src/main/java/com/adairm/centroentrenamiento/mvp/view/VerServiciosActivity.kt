package com.adairm.centroentrenamiento.mvp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.adairm.centroentrenamiento.adapters.AdapterServicios
import com.adairm.centroentrenamiento.dataBase.entidades.ServicioDetalles
import com.adairm.centroentrenamiento.databinding.ActivityVerServiciosBinding
import com.adairm.centroentrenamiento.mvp.interfaces.views.IVerServiciosActivity
import com.adairm.centroentrenamiento.mvp.presenter.PresenterVerServiciosActivity
import kotlinx.coroutines.launch

class VerServiciosActivity : AppCompatActivity(), IVerServiciosActivity{
    private lateinit var binding: ActivityVerServiciosBinding
    private lateinit var presenter: PresenterVerServiciosActivity
    private lateinit var adapterServ: AdapterServicios
    private lateinit var servicios: List<ServicioDetalles>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerServiciosBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        presenter = PresenterVerServiciosActivity(this, applicationContext)

        construirRecycler()
        agregarServicio()
    }

    private fun construirRecycler(){
        //Inicia un corrutina para ejecutar los metodos de la base de datos
        lifecycleScope.launch {
            presenter.obtenerServicios()
            binding.rvServicios.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
            adapterServ = AdapterServicios(servicios)
            binding.rvServicios.adapter = adapterServ
            adapterServ.setOnClickListener{
                val p = binding.rvServicios.getChildAdapterPosition(it)
                val id = servicios[p].servicios.idServicio
                val intent = Intent(applicationContext, OpcionesServiciosActivity::class.java)
                intent.putExtra("id_servicio", id)
                startActivity(intent)
            }
            if(adapterServ.itemCount == 0){
                binding.emptyTextView.visibility = View.VISIBLE
            }else{
                binding.emptyTextView.visibility = View.GONE
            }
        }
    }

    private fun agregarServicio(){
        binding.faAgregar.setOnClickListener {
            intent = Intent(this, FormServicioNuevoActivity::class.java)
            startActivity(intent)
        }
    }

    override suspend fun devolverServicios(servicios: List<ServicioDetalles>) {
        this.servicios = servicios
    }
}