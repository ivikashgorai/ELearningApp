package com.example.elearningapp.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.elearningapp.database.CourseRepo

class ViewModelFactory(private val CourseRepo: CourseRepo):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CourseViewModel::class.java)){
            return CourseViewModel(CourseRepo) as T
        }
        return super.create(modelClass)
    }
}