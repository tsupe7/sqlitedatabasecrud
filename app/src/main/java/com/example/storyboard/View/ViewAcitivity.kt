package com.example.storyboard.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storyboard.R
import com.example.storyboard.databinding.ActivityMainBinding
import com.example.storyboard.databinding.ActivityViewBinding
import com.example.storyboard.util.DataBaseHelper

/**
 * Created by Tushar on 9/5/20.
 */
class ViewAcitivity : AppCompatActivity() {

    var dataBaseHelper = DataBaseHelper(this)
    lateinit var binding : ActivityViewBinding
    var studentList :ArrayList<Student> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityViewBinding>(this, R.layout.activity_view)
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        studentList = dataBaseHelper.viewStudents()
        var adapter = StudentAdapter(studentList)
        binding.recycleView.adapter = adapter

    }
}