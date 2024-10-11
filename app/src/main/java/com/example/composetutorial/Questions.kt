package com.example.composetutorial

import java.io.Serializable


data class Questions(

    var questionNo: Int? = null,
    var questionName: String? = null

) : Serializable