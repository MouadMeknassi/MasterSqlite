package com.example.m206_sqlite_1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDAppart(context : Context) : SQLiteOpenHelper(context,"BD_App",null,1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE Appart_Table (id INTEGER PRIMARY KEY, offre TEXT, surface INTEGER, avecParking INTEGER, image TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Appartement")
        onCreate(db)
    }

    fun ajouterAppart(appart : Appartement):Long {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put("id", appart.id)
        values.put("offre", appart.offre)
        values.put("surface", appart.surface)
        values.put("avecParking", appart.avecParking)
        values.put("image", appart.image)
        return db.insert("Appart_Table", null, values)
    }

    fun getAll():ArrayList<Appartement> {

        var AppartList = ArrayList<Appartement>()

        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Appart_Table", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val offre = cursor.getString(1)
                val surface = cursor.getInt(2)
                val avecParking = cursor.getInt(3)
                val image = cursor.getString(4)
                val app = Appartement(id,offre,surface,avecParking,image)
                AppartList.add(app)
            } while (cursor.moveToNext())
        }
        return AppartList
    }
    fun updateAppart(appart: Appartement): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("id", appart.id)
        values.put("offre", appart.offre)
        values.put("surface", appart.surface)
        values.put("avecParking", appart.avecParking)
        values.put("image", appart.image)
        // Update app's row in database with the values of updated app
        return db.update("Appart_Table", values, "id = ?", arrayOf(appart.id.toString())).toLong()
    }
    fun deleteAppart(id: Int): Int {
        val db = this.writableDatabase
        return db.delete("Appart_Table", "id = ?", arrayOf(id.toString()))
    }
    fun searchById(id: Int): ArrayList<Appartement> {
        var AppartListItem = ArrayList<Appartement>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Appart_Table WHERE id = ?", arrayOf(id.toString()))
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val offre = cursor.getString(1)
                val surface = cursor.getInt(2)
                val avecParking = cursor.getInt(3)
                val image = cursor.getString(4)
                val app = Appartement(id,offre,surface,avecParking,image)
                AppartListItem.add(app)
            } while (cursor.moveToNext())
        }else{
            AppartListItem.size
        }
        return AppartListItem
    }
}