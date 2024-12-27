package com.example.elearningapp.database

class CourseRepo(private val CourseDAO: CourseDAO) {
    suspend fun getAllCourse() : List<Course> = CourseDAO.getAllCourse()
    suspend fun addCourse(Course: Course) = CourseDAO.addCourse(Course)
    suspend fun updateCourse(Course: Course) = CourseDAO.updateCourse(Course)

}