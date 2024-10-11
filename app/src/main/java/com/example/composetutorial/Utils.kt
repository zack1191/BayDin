package com.example.composetutorial

import android.content.Context
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.io.IOException

class Utils {
    fun getJsonDataFromAsset(
        context: Context,
        fileName: String
    ): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use {
                it.readText()
            }
        } catch (exp: IOException) {
            exp.printStackTrace()
            return null
        }

        return jsonString
    }

    fun questionList(context: Context): MutableList<Questions> {
        val jsonFileString = getJsonDataFromAsset(context = context, "baydin.json")
        val type = object : TypeToken<List<Questions>>() {}.type
        return Gson().fromJson(jsonFileString, type)
    }


}