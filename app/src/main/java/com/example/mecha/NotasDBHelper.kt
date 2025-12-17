package com.example.mecha

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NotasDBHelper(context: Context) :
    SQLiteOpenHelper(context, "NotasDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE notas (" +
                    "titulo TEXT PRIMARY KEY," +
                    "descripcion TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS notas")
        onCreate(db)
    }

    fun insertNota(titulo: String, descripcion: String): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("titulo", titulo)
        values.put("descripcion", descripcion)
        val result = db.insert("notas", null, values)
        return result != -1L
    }

    fun updateNota(titulo: String, descripcion: String): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("descripcion", descripcion)
        val result = db.update("notas", values, "titulo=?", arrayOf(titulo))
        return result > 0
    }

    fun deleteNota(titulo: String): Boolean {
        val db = writableDatabase
        val result = db.delete("notas", "titulo=?", arrayOf(titulo))
        return result > 0
    }
}
