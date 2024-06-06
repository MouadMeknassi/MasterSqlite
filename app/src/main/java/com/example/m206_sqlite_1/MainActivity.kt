package com.example.m206_sqlite_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnList = findViewById<Button>(R.id.List)

        btnList.setOnClickListener {

            val i = Intent(applicationContext, MainActivity_List::class.java)

            startActivity(i)
        }
        val btnSearch = findViewById<Button>(R.id.Search)

        btnSearch.setOnClickListener{
            val iSearch = Intent(applicationContext, MainActivity_search::class.java)

            startActivity(iSearch)
        }

        val btnAdd = findViewById<Button>(R.id.Add)
        btnAdd.setOnClickListener {

            val Id = findViewById<EditText>(R.id.ID).text.toString().toInt()
            val Offre = findViewById<EditText>(R.id.Offre).text.toString()
            val Surface = findViewById<EditText>(R.id.Surface).text.toString().toInt()
            val Parking = findViewById<EditText>(R.id.avecParking).text.toString().toInt()
            val Image = findViewById<EditText>(R.id.image).text.toString()

            val A = Appartement(Id,Offre,Surface,Parking,Image)

            val db = BDAppart(applicationContext)

            val resultat = db.ajouterAppart(A)

            if(resultat != (-1).toLong()) {
                Toast.makeText(applicationContext,"Succesfuly Added",Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(applicationContext,"Failure",Toast.LENGTH_LONG).show()
            }
        }
        /*
        val btnDelete = findViewById<Button>(R.id.Delete)
        btnDelete.setOnClickListener{
            val Id = findViewById<EditText>(R.id.ID).text.toString().toInt()

            val db = BDAppart(applicationContext)

            val deleted = db.deleteAppart(Id)

            if (deleted != 0) {
                Toast.makeText(applicationContext, "Successfully Deleted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Deletion Failed", Toast.LENGTH_LONG).show()
            }
        }
        */
    }
}