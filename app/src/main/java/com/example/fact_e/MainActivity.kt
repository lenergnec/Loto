package com.example.fact_e

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.fact_e.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var DB: DatabaseReference
    private var FS = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Factura Electronica"

        //llamamos nuestro string de array
        val sorteoList = resources.getStringArray(R.array.sorteo)
        //creamos nuestro adapter
        val adapter = ArrayAdapter(this, R.layout.list_item, sorteoList)
        with(binding.etSorteo){
            setAdapter(adapter)
        }


        binding.senData.setOnClickListener{


            var number = binding.etNumber.text.toString()
            var amount = binding.etAmount.text.toString()
            var sorteo = binding.etSorteo.text.toString()

           if (binding.etNumber.text.toString().isNotEmpty() && binding.etAmount.text.toString().isNotEmpty() && binding.etSorteo.text.toString().isNotEmpty())
           {

               val confirmSendData = AlertDialog.Builder(this)
                   confirmSendData.setTitle("Confirmacion de envio de datos")
                   confirmSendData.setIcon(R.drawable.sendata)
                   confirmSendData.setMessage("¿Estas seguro de enviar este numero?")
                   confirmSendData.setPositiveButton("Enviar"){
                           dialog,_->

                       //REAL TIME DATABASE
                       DB = FirebaseDatabase.getInstance().getReference("Facturas")

                       //creamos una instancia de la clase datafac y le pasamos las variables de la clase
                       val fact = DataFac(number.toInt(), amount.toInt(), sorteo )

                       //el child sera el dato cabecera de nuestra coleccion y en setvalue le pasamos nuestro objeto fact que contiene los datos que mandaremos
                       DB.child(sorteo).setValue(fact).addOnSuccessListener {
                           binding.etNumber.text?.clear()
                           binding.etAmount.text?.clear()
                           binding.etSorteo.text?.clear()
                           binding.etNumber.requestFocus()

                           Toast.makeText(this, "Se ha enviado con exito", Toast.LENGTH_LONG).show()
                       }.addOnFailureListener {
                           Toast.makeText(this, "El envio ha fallado", Toast.LENGTH_LONG).show()
                       }

                       //FIRESTORE
                       //llamamos a nuestra instancia de firebase y creamos nuestra collection

                       //var fireNumber = binding.etNumber.text.toString()
                       FS.collection("Facturas").document(sorteo).set(
                           //Creamos un hashmap para crear nuestra coleccion y llamamos nuestras vistas para recuperar los datos y mandarlos a la coleccion
                           hashMapOf("Numero" to number.toInt(), "Monto" to amount.toInt(), "Sorteo" to sorteo )
                       )

                   }
               confirmSendData.setNegativeButton("Revisar"){
                   dialog,_->
                   binding.etNumber.requestFocus()
               }.show()
           }else {
               var emptyInputs = AlertDialog.Builder(this)
                   .setTitle("Campos vacios")
                   .setIcon(R.drawable.error)
                   .setMessage("Esta intentando enviar sin haber ingresado los datos.")
                   .setPositiveButton("Voy a llenar los datos."){
                       dialog,_->
                       dialog.dismiss()
                       binding.etNumber.requestFocus()
                   }
               emptyInputs.create()
               emptyInputs.show()
           }


        }
    }
}