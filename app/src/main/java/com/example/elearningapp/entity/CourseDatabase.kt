package com.example.elearningapp.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.elearningapp.database.CourseDAO
import com.example.elearningapp.database.Course


// require no changes in this file
@Database(entities = [Course::class], version =4)
abstract class CourseDatabase() : RoomDatabase() {// this abstract because room jobs to write code
abstract fun CourseDAO() : CourseDAO
}