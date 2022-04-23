package com.example.fact_e

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fact_e.Adapter.AdapterData
import com.example.fact_e.Model.DataFac
import com.example.fact_e.databinding.ActivityDataListBinding
import com.google.firebase.database.*

class DataList : AppCompatActivity() {

    private lateinit var DataB: DatabaseReference
    private lateinit var dataArrayList: ArrayList<DataFac>
    private lateinit var binding: ActivityDataListBinding
    private lateinit var recycler: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //recycler = findViewById(R.id.dataList)
       // recycler.layoutManager = LinearLayoutManager(this)
        //recycler.setHasFixedSize(true)

        //dataArrayList = arrayListOf<DataFac>()

        getDataFirstTime()
        getDataSecondTime()
        getDataThirdTime()



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_recyclers, menu)
        return true
    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.gdata11am -> {
                getDataFirstTime()}
            R.id.gdata3pm -> {getDataSecondTime()}
            R.id.gdata9pm -> {getDataThirdTime()}
        }
          return super.onOptionsItemSelected(item)
    }*/




     fun getDataFirstTime(){
        DataB = FirebaseDatabase.getInstance().getReference("Sorteo").child("11am")

        DataB.addValueEventListener(object: ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for(dataSnapshot in snapshot.children){
                        val data = dataSnapshot.getValue(DataFac::class.java)
                        dataArrayList.add(data!!)
                    }
                    recycler.adapter = AdapterData(dataArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }
     fun getDataSecondTime(){
        DataB = FirebaseDatabase.getInstance().getReference("").child("3pm")

        DataB.addValueEventListener(object: ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for(dataSnapshot in snapshot.children){
                        val data = dataSnapshot.getValue(DataFac::class.java)
                        dataArrayList.add(data!!)
                    }
                    recycler.adapter = AdapterData(dataArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }
     fun getDataThirdTime(){
        DataB = FirebaseDatabase.getInstance().getReference("").child("9pm")

        DataB.addValueEventListener(object: ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for(dataSnapshot in snapshot.children){
                        val data = dataSnapshot.getValue(DataFac::class.java)
                        dataArrayList.add(data!!)
                    }
                    recycler.adapter = AdapterData(dataArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }
}