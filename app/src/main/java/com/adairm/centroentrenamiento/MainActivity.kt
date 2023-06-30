package com.adairm.centroentrenamiento

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.adairm.centroentrenamiento.adapters.AdapterPrestamosServicio
import com.adairm.centroentrenamiento.dataBase.entidades.PrestamoServicios
import com.adairm.centroentrenamiento.dataBase.entidades.PrestamoServiciosDetalles
import com.adairm.centroentrenamiento.dataBase.repository.GestiosPagosRepository
import com.adairm.centroentrenamiento.databinding.ActivityMainBinding
import com.adairm.centroentrenamiento.mvp.view.FormPagoNuevoActivity
import com.adairm.centroentrenamiento.mvp.view.HistorialPagosActivity
import com.adairm.centroentrenamiento.mvp.view.VerClientesActivity
import com.adairm.centroentrenamiento.mvp.view.VerServiciosActivity
import com.adairm.centroentrenamiento.retrofit.Codigo
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterPrestamosServicio: AdapterPrestamosServicio
    private lateinit var prestamo: PrestamoServiciosDetalles
    private lateinit var listCodigo: List<Codigo>
    private var id: Int = 0
    private var idCliente: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        GestiosPagosRepository.inicializar(this)
        val db = GestiosPagosRepository.get()
        agregarPago()
        llenarSpinner(db)
        binding.spinnerConcepto.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(applicationContext, "Elemento seleccionado: $position", Toast.LENGTH_SHORT).show()
                mostrarPrestamos(db, (position + 1))
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        mostrarClientes()
        mostrarServicios()
        mostrarHistorial()


    }

    private fun agregarPago(){
        binding.bntAgregar.setOnClickListener {
            intent = Intent(this, FormPagoNuevoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun mostrarClientes(){
        binding.btnClientes.setOnClickListener {
            intent = Intent(this, VerClientesActivity::class.java)
            startActivity(intent)
        }
    }

    private  fun mostrarServicios(){
        binding.btnServicios.setOnClickListener {
            intent = Intent(this, VerServiciosActivity::class.java)
            startActivity(intent)
        }
    }

    private fun mostrarHistorial(){
        binding.btnHistorial.setOnClickListener {
            intent = Intent(this, HistorialPagosActivity::class.java)
            startActivity(intent)
        }
    }

    private fun mostrarPrestamos(db: GestiosPagosRepository, idS: Int){
        lifecycleScope.launch {
            val clientes = db.buscarPrestamosPorConcepto(idS)

            binding.rvPrestamos.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
            adapterPrestamosServicio = AdapterPrestamosServicio(clientes)
            binding.rvPrestamos.adapter = adapterPrestamosServicio
            adapterPrestamosServicio.setOnClickListener{
                val p = binding.rvPrestamos.getChildAdapterPosition(it)

                lifecycleScope.launch {
                    id = clientes[p].prestamoServicios.idPrestamoServicio
                    idCliente = clientes[p].cliente.idCliente
                    prestamo = db.obtenerPrestamoId(id)
                    pagarPendiente(db)
                }
            }
            if(adapterPrestamosServicio.itemCount == 0){
                binding.emptyTextView.visibility = View.VISIBLE
            }else{
                binding.emptyTextView.visibility = View.GONE
            }
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

    private fun pagarPendiente(db: GestiosPagosRepository){
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Confirmación")
        builder.setMessage("¿Estás seguro que marcar como pagado?")

        builder.setPositiveButton("Sí") { dialog, which ->
            lifecycleScope.launch {
                val prest = db.buscarIdPrestamo(prestamo.prestamoServicios.idPrestamoServicio)
                val cliente = db.buscarPorId(idCliente)
                cliente.numAdeudos --
                val serv = PrestamoServicios(id,
                    prest.fechaInicio,
                    prest.fechaCorte,
                    prest.idCliente,
                    prest.idServicio,
                    2
                )
                db.actualizarPrestamo(serv)
                db.actualizarCliente(cliente)

                intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
        }
        builder.setNegativeButton("No") { dialog, which ->
            dialog.cancel()
        }

        val dialog = builder.create()
        dialog.show()
    }
}