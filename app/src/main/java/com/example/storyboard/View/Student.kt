package com.example.storyboard.View

/**
 * Created by Tushar on 9/5/20.
 */
public class Student( var rollnumber: Int, var name: String, var score: Int) {

    override fun toString(): String {
        return "Student(rollnumber=$rollnumber, name='$name', score=$score)"
    }
}