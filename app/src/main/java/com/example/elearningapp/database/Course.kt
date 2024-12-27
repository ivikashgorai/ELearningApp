package com.example.elearningapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course")
data class Course (
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    var courseName:String = "",
    var interested :String = "",
    var rating :String = "",
    var courseLevel: String = "",
    var courseImage:Int,
    var courseDescription:String = ""
)