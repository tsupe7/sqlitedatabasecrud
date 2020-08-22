package com.example.storyboard.util

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.storyboard.View.Student

/**
 * Created by Tushar on 9/5/20.
 */
class DataBaseHelper(context: Context) : SQLiteOpenHelper(context,
    DATABASE_NAME,null,
    DATABASE_VERSION
) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "studnetDatabase"
        private val TABLE_STUDENT = "student"
        private val KEY_NAME = "name"
        private val KEY_ROLL_NO = "rollnumber"
        private val KEY_SCORE = "score"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_STUDENT + "("
                + KEY_ROLL_NO + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_SCORE + " INTEGER" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT)
        onCreate(db)
    }

    fun add(student: Student): Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, student.name)
        contentValues.put(KEY_ROLL_NO, student.rollnumber)
        contentValues.put(KEY_SCORE,student.score )
        // Inserting Row
        val success = db.insert(TABLE_STUDENT, null, contentValues)

        db.close() // Closing database connection
        return success
    }

    fun delete(rollnumber: Int): Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ROLL_NO, rollnumber)
        val success = db.delete(TABLE_STUDENT,"$KEY_ROLL_NO="+rollnumber,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    fun update(student: Student):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ROLL_NO, student.rollnumber)
        contentValues.put(KEY_NAME, student.name) // EmpModelClass Name
        contentValues.put(KEY_SCORE,student.score ) // EmpModelClass Email

        // Updating Row
        val success = db.update(TABLE_STUDENT, contentValues,"$KEY_ROLL_NO="+student.rollnumber,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    fun viewStudents():ArrayList<Student>{
        val empList:ArrayList<Student> = ArrayList<Student>()
        val selectQuery = "SELECT  * FROM $TABLE_STUDENT"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var rollNo: Int
        var name: String
        var score: Int
        if (cursor.moveToFirst()) {
            do {
                rollNo = cursor.getInt(cursor.getColumnIndex(KEY_ROLL_NO))
                name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                score = cursor.getInt(cursor.getColumnIndex(KEY_SCORE))
                val student= Student(rollNo, name, score)
                empList.add(student)
            } while (cursor.moveToNext())
        }
        return empList
    }

}