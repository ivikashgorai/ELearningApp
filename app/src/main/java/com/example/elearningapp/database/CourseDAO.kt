package com.example.elearningapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CourseDAO {
    @Query("SELECT * FROM course")
    suspend fun getAllCourse(): List<Course> // this fun will get data from database

    @Insert // uses for inserting in database
    suspend fun addCourse(course: Course) // this fun inserts the data

    @Update
    suspend fun updateCourse(course:Course) // this fun update the course searching by id
}