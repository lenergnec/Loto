package com.example.fact_e

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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


        binding.senData.setOnClickListener{

            var number = binding.etNumber.text.toString()
            var amount = binding.etAmount.text.toString()
            var seller = binding.etSeller.text.toString()

           if (binding.etNumber.text.toString().isNotEmpty() && binding.etAmount.text.toString().isNotEmpty() && binding.etSeller.text.toString().isNotEmpty())
           {
               //REAL TIME DATABASE
               DB = FirebaseDatabase.getInstance().getReference("Facturas")

               val fact = DataFac(number, amount, seller )

               DB.child(number).setValue(fact).addOnSuccessListener {
                   binding.etNumber.text?.clear()
                   binding.etAmount.text?.clear()
                   binding.etSeller.text?.clear()
                   binding.etNumber.requestFocus()

                   Toast.makeText(this, "Exitoso", Toast.LENGTH_LONG).show()
               }.addOnFailureListener {
                   Toast.makeText(this, "Fallido", Toast.LENGTH_LONG).show()
               }

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
            //FIRESTORE
            //llamamos a nuestra instancia de firebase y creamos nuestra collection


            FS.collection("Facturas").document(seller).set(
                //Creamos un hashmap para crear nuestra coleccion y llamamos nuestras vistas para recuperar los datos y mandarlos a la coleccion
                hashMapOf("Numero" to binding.etNumber.text.toString(), "Monto" to binding.etAmount.text.toString(), "Vendedor" to binding.etSeller.text.toString())
            )

        }
    }
}