package com.example.m206_sqlite_1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity_Update : AppCompatActivity() {

    lateinit var StringListItem:ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_update)
        val b = intent.extras
        val Id = findViewById<EditText>(R.id.ID)
        val Offre = findViewById<EditText>(R.id.Offre)
        val Surface = findViewById<EditText>(R.id.Surface)
        val Parking = findViewById<EditText>(R.id.avecParking)
        val Image = findViewById<EditText>(R.id.image)
        if (b != null) {
            Id.setText(b.getInt("ID").toString())
            Id.isEnabled=false
            Offre.setText(b.getString("Offre"))
            Surface.setText(b.getInt("Surface").toString())
            Parking.setText(b.getInt("Parking").toString())
            Image.setText(b.getString("Image"))
        }

        val btnUpdate = findViewById<Button>(R.id.Update)

        btnUpdate.setOnClickListener {

            val Id = findViewById<EditText>(R.id.ID).text.toString().toInt()
            val Offre = findViewById<EditText>(R.id.Offre).text.toString()
            val Surface = findViewById<EditText>(R.id.Surface).text.toString().toInt()
            val Parking = findViewById<EditText>(R.id.avecParking).text.toString().toInt()
            val Image = findViewById<EditText>(R.id.image).text.toString()


            val A = Appartement(Id, Offre, Surface, Parking, Image)

            val db = BDAppart(applicationContext)
            /**************************************************************************/

            val AppartListItem = db.searchById(Id)
            for (app in AppartListItem) {
               // StringListItem.add(app.toString())
                val rslt= db.updateAppart(app)
            }

            /**************************************************************************/



            val resultat = db.updateAppart(A)

            if (resultat != (-1).toLong()) {
                Toast.makeText(applicationContext, "Successfully Updated", Toast.LENGTH_LONG).show()
                //finish() // Fermez l'activité de mise à jour
            } else {
                Toast.makeText(applicationContext, "Update Failed", Toast.LENGTH_LONG).show()
            }
        }
    }
}