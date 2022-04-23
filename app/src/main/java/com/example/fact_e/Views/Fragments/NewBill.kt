package com.example.fact_e.Views.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.fact_e.Model.DataFac
import com.example.fact_e.R
import com.example.fact_e.databinding.FragmentNewBillBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class NewBill : Fragment(R.layout.fragment_new_bill) {

    private lateinit var binding: FragmentNewBillBinding

    private lateinit var DB: DatabaseReference
    private var FS = FirebaseFirestore.getInstance()
    private lateinit var datafac: DataFac


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNewBillBinding.bind(view)

    }

    fun sendDataFB(){


        var number = binding.etNumber.text.toString()
        var amount = binding.etAmount.text.toString()


        if (binding.etNumber.text.toString().isNotEmpty() && binding.etAmount.text.toString().isNotEmpty())
        {


        }


        if (binding.etNumber.text.toString().isNotEmpty() && binding.etAmount.text.toString().isNotEmpty())
        {

            DB = FirebaseDatabase.getInstance().getReference()

            //creamos una instancia de la clase datafac y le pasamos las variables de la clase
            val fact = DataFac(number, amount,datafac.sorteo)

            //el child sera el dato cabecera de nuestra coleccion y en setvalue le pasamos nuestro objeto fact que contiene los datos que mandaremos
            DB.child(datafac.sorteo.toString()).push().setValue(fact).addOnSuccessListener {
                binding.etNumber.text?.clear()
                binding.etAmount.text?.clear()
                binding.etNumber.requestFocus()

                Toast.makeText(requireContext(), "Se ha enviado con exito", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(requireContext(), "El envio ha fallado", Toast.LENGTH_LONG).show()
            }


            /*val confirmSendData = AlertDialog.Builder(requireContext())
            confirmSendData.setTitle("Confirmacion de envio de datos")
            confirmSendData.setIcon(R.drawable.sendata)
            confirmSendData.setMessage("¿Estas seguro de enviar este numero?")
            confirmSendData.setPositiveButton("Enviar"){
                    dialog,_->

                //REAL TIME DATABASE


                //FIRESTORE
                //llamamos a nuestra instancia de firebase y creamos nuestra collection

                var fireNumber = binding.etNumber.text.toString()
                FS.collection("Facturas").document(datafac.sorteo.toString()).set(
                    //Creamos un hashmap para crear nuestra coleccion y llamamos nuestras vistas para recuperar los datos y mandarlos a la coleccion
                   hashMapOf("Numero" to number, "Inversion" to amount, "Sorteo" to datafac.sorteo))

            }
            confirmSendData.setNegativeButton("Revisar"){
                    dialog,_->
                binding.etNumber.requestFocus()
            }.show()*/
        }else {
            var emptyInputs = AlertDialog.Builder(requireContext())
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