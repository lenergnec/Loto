package com.example.fact_e

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.fact_e.Model.DataFac
import com.example.fact_e.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Factura Electronica"

        //llamamos nuestro string de array


        binding.btnData11am.setOnClickListener{
            var recycler = Intent(this, DataList::class.java)
            startActivity(recycler)
        }

    }

    fun listAdapterSorteo(){
        val sorteoList = resources.getStringArray(R.array.sorteo)

        //creamos nuestro adapter
        val adapter = ArrayAdapter(this, R.layout.list_item, sorteoList)
        with(binding.etSorteo1){
            setAdapter(adapter)
        }

    }
}