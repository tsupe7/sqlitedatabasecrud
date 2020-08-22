package com.example.storyboard.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.storyboard.R
import com.example.storyboard.databinding.LayoutStudentBinding

/**
 * Created by Tushar on 9/5/20.
 */
class StudentAdapter (var studentList : ArrayList<Student>): RecyclerView.Adapter<StudentAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<LayoutStudentBinding>(inflater, R.layout.layout_student, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var student = studentList[position]
        holder.bind(student)
    }

    class ViewHolder(val binding: LayoutStudentBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
            binding.name.text = "Name :"+student.name
            binding.rollNo.text = "Roll Number :"+student.rollnumber.toString()
            binding.score.text = "Score :"+student.score.toString()
            binding.executePendingBindings()
        }
    }
}