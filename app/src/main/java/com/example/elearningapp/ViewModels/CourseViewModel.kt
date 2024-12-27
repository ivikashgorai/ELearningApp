package com.example.elearningapp.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elearningapp.database.Course
import com.example.elearningapp.database.CourseRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.example.elearningapp.R

class CourseViewModel(private val courseRepository: CourseRepo): ViewModel() { // not return here

    val courseList = MutableStateFlow<List<Course>>(emptyList())

    init{
        addSamplecourseIfNeeded()
        getAllCourse()
    }

         private fun addSamplecourseIfNeeded(){
            viewModelScope.launch(Dispatchers.IO){
                val existingCourse = courseRepository.getAllCourse()
                if(existingCourse.isEmpty()){
                    //generate course
                    generateAllCourse().forEach{
                        courseRepository.addCourse(it)
                    }
                    getAllCourse()
                }
            }
        }
    private fun getAllCourse(){
        viewModelScope.launch(Dispatchers.IO){
            courseList.value = courseRepository.getAllCourse()
        }
    }

    private fun generateAllCourse():List<Course>{
        return listOf<Course>(
            Course(
                courseName = "Complete Interview preparation",
                interested = "944k+ Interested Geeks",
                rating = "4.8",
                courseLevel = "Beginner to Advanced",
                courseImage = R.drawable.cip,
                courseDescription = "This Course will help you to prepare for interview"
            ),
            Course(
                courseName = "Gate Data Science and Artificial Intelligence",
                interested = "200k+ Interested Geeks",
                rating = "4.7",
                courseLevel = "Beginner to Advanced",
                courseImage = R.drawable.da_gate_1720781930,
                courseDescription = "This Course will help you to prepare for Gate"
            ),
            Course(
                courseName = "Devops",
                interested = "200k+ Interested Geeks",
                rating = "4.7",
                courseLevel = "Beginner to Advanced",
                courseImage = R.drawable.devops,
                courseDescription = "This Course will help you to prepare for Gate"
            )
            ,
            Course(
                courseName = "Django Framework",
                interested = "200k+ Interested Geeks",
                rating = "4.7",
                courseLevel = "Beginner to Advanced",
                courseImage = R.drawable.django,
                courseDescription = "This Course will help you to prepare for Gate"
            )
            ,
            Course(
                courseName = "Data Structure and Algorithm",
                interested = "200k+ Interested Geeks",
                rating = "4.7",
                courseLevel = "Beginner to Advanced",
                courseImage = R.drawable.dsa_1723009292,
                courseDescription = "This Course will help you to prepare for Gate"
            )
            ,
            Course(
                courseName = "FRSNL",
                interested = "200k+ Interested Geeks",
                rating = "4.7",
                courseLevel = "Beginner to Advanced",
                courseImage = R.drawable.fsrnl,
                courseDescription = "This Course will help you to prepare for Gate"
            )
            ,
            Course(
                courseName = "Artificial Intelligence",
                interested = "200k+ Interested Geeks",
                rating = "4.7",
                courseLevel = "Beginner to Advanced",
                courseImage = R.drawable.genai_1722948634,
                courseDescription = "This Course will help you to prepare for Gate"
            )
            ,
            Course(
                courseName = "Gate 2025",
                interested = "200k+ Interested Geeks",
                rating = "4.7",
                courseLevel = "Beginner to Advanced",
                courseImage = R.drawable.da_gate_1720781930,
                courseDescription = "This Course will help you to prepare for Gate"
            )
            ,
            Course(
                courseName = "Software Testing",
                interested = "200k+ Interested Geeks",
                rating = "4.7",
                courseLevel = "Beginner to Advanced",
                courseImage = R.drawable.softeware_testing,
                courseDescription = "This Course will help you to prepare for Gate"
            )
            ,
            Course(
                courseName = "Technical Interview Preparation",
                interested = "200k+ Interested Geeks",
                rating = "4.7",
                courseLevel = "Beginner to Advanced",
                courseImage = R.drawable.technical_interview,
                courseDescription = "This Course will help you to prepare for Gate"
            )

        )
    }

}