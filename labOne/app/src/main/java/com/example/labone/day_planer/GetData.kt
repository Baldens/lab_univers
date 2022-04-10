package com.example.labone.day_planer

import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder

class GetData {
    fun readFileFromAssets(context: Context, filename: String?): String? {
        var reader: BufferedReader? = null
        val builder = StringBuilder()
        try {
            reader = BufferedReader(
                InputStreamReader(
                    context.assets.open(filename!!)
                )
            )
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                builder.append(line).append("\n")
            }
            return builder.toString()
        } catch (e: FileNotFoundException) {
            Log.e("File", "Файл не был найден: " + e.message)
        } catch (e: Exception) {
            Log.e("File", "Файл не был найден: " + e.message)
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: Exception) {
                    Log.e("File", "Файл не был найден: " + e.message)
                }
            }
        }
        return null
    }

    fun writeFileFromAssets(){
        
    }
}