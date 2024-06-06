package com.example.m206_sqlite_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast

class MainActivity_search : AppCompatActivity() {
    lateinit var StringListItem:ArrayList<String>
    lateinit var adapter:ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_search)

        StringListItem = ArrayList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, StringListItem)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        btnSearch.setOnClickListener {
            var idString = findViewById<EditText>(R.id.idAppart).text.toString().trim()
            if (idString.isEmpty()){
                findViewById<EditText>(R.id.idAppart).setBackgroundColor(resources.getColor(R.color.errorColor))
                Toast.makeText(applicationContext, "Veuillez entrer une valeur numérique valide pour l'id", Toast.LENGTH_LONG).show()
            }else{
                findViewById<EditText>(R.id.idAppart).setBackgroundColor(resources.getColor(R.color.transparent))
                try {
                    val id = idString.toInt()
                    val listView = findViewById<ListView>(R.id.listview)

                    val db = BDAppart(applicationContext)
                    val AppartListItem = db.searchById(id)
                    if (AppartListItem.size==0) {
                        Toast.makeText(applicationContext, "L'id $id n'existe pas !", Toast.LENGTH_LONG).show()
                        StringListItem.clear()
                        adapter.notifyDataSetChanged()
                    } else {
                        for (app in AppartListItem) {
                            StringListItem.add(app.toString())
                        }
                        listView.adapter = adapter
                    }
                }catch (e: NumberFormatException){
                    Toast.makeText(applicationContext, "Veuillez entrer une valeur numérique valide pour l'id", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}