package com.example.m206_sqlite_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Switch
import android.widget.Toast
import org.json.JSONException

class MainActivity_List : AppCompatActivity() {
    lateinit var listView:ListView
    override fun onResume() {
        super.onResume()
        loadData()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list)

        listView = findViewById(R.id.listview)

        val db = BDAppart(applicationContext)

        val AppartList = db.getAll()

        val StringList = ArrayList<String>()

        for(app in AppartList){

            StringList.add(app.toString())

        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, StringList)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->

            val appart = AppartList[position]
            val intent = Intent(applicationContext, MainActivity_Update::class.java)
            val bundle = Bundle()
            bundle.putInt("ID", appart.id)
            bundle.putString("Offre", appart.offre)
            bundle.putInt("Surface", appart.surface)
            bundle.putInt("Parking", appart.avecParking)
            bundle.putString("Image", appart.image)
            intent.putExtras(bundle)
            startActivity(intent)
            //finish()
        }
        listView.setOnItemLongClickListener { parent, view, position, id ->
            val appart = AppartList[position]

            val db = BDAppart(applicationContext)

            val deleted=db.deleteAppart(appart.id)
            if (deleted != 0){
                Toast.makeText(applicationContext, "Successfully Deleted", Toast.LENGTH_LONG).show()
                loadData()
               // StringList.removeAt(position)
               // adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(applicationContext, "Deletion Failed", Toast.LENGTH_LONG).show()
            }
            true
        }
    }
    fun loadData() {
        listView = findViewById(R.id.listview)
        val db = BDAppart(applicationContext)
        val AppartList = db.getAll()
        val StringList = ArrayList<String>()
        for(app in AppartList){
            StringList.add(app.toString())
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, StringList)
        listView.adapter = adapter
    }
}