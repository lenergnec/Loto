package com.example.fact_e.Views.Fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fact_e.Adapter.AdapterData
import com.example.fact_e.DataList
import com.example.fact_e.Model.DataFac
import com.example.fact_e.R
import com.example.fact_e.databinding.FragmentHomeBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class Home : Fragment(R.layout.fragment_home) {

    //Implementamos el binding
    private lateinit var binding: FragmentHomeBinding
    private lateinit var recycler: RecyclerView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        var dataModelList = arrayListOf<DataFac>()
        var adaptador = AdapterData(dataModelList)

        recycler = view.findViewById(R.id.dataList)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adaptador
        //recycler.setHasFixedSize(true)

        binding.addNew.setOnClickListener{
            //findNavController().navigate(R.id.action_home_to_newBill)
            //Inflamos nuestro layout
            val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_new_bill, null)

            //Creamos nuestro dialog
            val mBuilder = AlertDialog.Builder(context)
                .setView(mDialogView)
                .setTitle("Ingrese los numeros")
                .setIcon(R.drawable.sendata)
                .setPositiveButton("Guardar"){
                    dialog,_->

                    var number = view.findViewById<EditText>(R.id.etNumber)
                    var amount = view.findViewById<EditText>(R.id.etAmount)

                    var radiogroup = view.findViewById<RadioGroup>(R.id.rg)
                    radiogroup.check(R.id.sorteo11am)

                    var radioSelected = radiogroup.checkedRadioButtonId
                    var radioButton =  view.findViewById<RadioButton>(radioSelected)
                    var sorteo = radioButton.text


                    if (mDialogView.findViewById<EditText>(R.id.etNumber).toString().isNotEmpty() && mDialogView.findViewById<EditText>(R.id.etAmount).toString().isNotEmpty())
                        {
                            val datos = DataFac("Numero $number", "Inversion $amount", "Sorteo $sorteo")
                            dataModelList.add(datos)
                            adaptador.notifyDataSetChanged()
                            //dataModelList.add(DataFac(number, amount, sorteos))
                            //adaptador.notifyDataSetChanged()

                    }else{
                        var emptyInputs = AlertDialog.Builder(context)
                            .setTitle("Campos vacios")
                            .setIcon(R.drawable.error)
                            .setMessage("Esta intentando enviar sin haber ingresado los datos.")
                            .setPositiveButton("Voy a llenar los datos."){
                                    dialog,_->

                                dialog.dismiss()
                                mDialogView.findViewById<EditText>(R.id.etNumber).requestFocus()
                            }
                        emptyInputs.create()
                        emptyInputs.show()
                    }
                }
            //Mostramos la alerta
                .setNegativeButton("Cancelar"){
                        dialog,_->
                    dialog.dismiss()
                }
                mBuilder.create()
                mBuilder.show()

            }

        }
        //var getData = DataList()
        //getData.getDataFirstTime()
    }