package com.example.storyboard.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.storyboard.R
import com.example.storyboard.databinding.ActivityMainBinding
import com.example.storyboard.util.DataBaseHelper


class MainActivity : AppCompatActivity() {


    lateinit var binding : ActivityMainBinding
    var dataBaseHelper = DataBaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)


        binding.add.setOnClickListener{
            if(validate()) {
                var student = Student(binding.rollNo.text.toString().toInt(), binding.name.text.toString(), binding.score.text.toString().toInt())
                if(dataBaseHelper.add(student)>0){
                    Toast.makeText(this, "Record Added", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "Please enter missing fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.delete.setOnClickListener{

            if(dataBaseHelper.delete(binding.rollNo.text.toString().toInt())>0){
                Toast.makeText(this, "Record Deleted", Toast.LENGTH_SHORT).show()
            }
        }

        binding.modify.setOnClickListener{
            var student = Student(binding.rollNo.text.toString().toInt(), binding.name.text.toString(), binding.score.text.toString().toInt())
            if(dataBaseHelper.update(student)>0) {
                Toast.makeText(this, "Record Modify", Toast.LENGTH_SHORT).show()
            }
        }

        binding.view.setOnClickListener{
            val intent = Intent(this, ViewAcitivity::class.java)
            startActivity(intent)
        }
    }


    fun validate(): Boolean{
        var ready: Boolean = true
        if(binding.name.text.isNullOrEmpty()){
            ready = false
        }
        if(binding.rollNo.text.isNullOrEmpty()){
            ready = false
        }
        if(binding.score.text.isNullOrEmpty()){
            ready = false
        }
        return ready
    }
}
