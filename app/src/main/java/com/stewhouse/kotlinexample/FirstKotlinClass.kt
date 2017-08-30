package com.stewhouse.kotlinexample

import android.support.v7.app.AppCompatActivity
import android.util.Log

/**
 * Created by Gomguk on 2017. 8. 16..
 */

class FirstKotlinClass: AppCompatActivity() {
    fun safeCalls() {
        val listWithNulls: List<String?> = listOf("2B", null, "9S")

        for (item in listWithNulls) {
//            Log.e("FirstKotlinClass", item)           // Application crashes because of the null context of List.
            item?.let { Log.e("FirstKotlinClass", it) }
        }
    }

    fun elvisOperator(b: String?) {
//        val l: Int = if (b != null) b.length else -1  // Warning for recommending to use Elvis Operator.
        val l: Int = b?.length ?: -1                    // Using Elvis Operator.

        Log.e("FirstKotlinClass", "val l: $l")
    }

    fun NPEOperator(b: String?) = try {
        val l = b!!.length      // This will occur KotlinNullPointerException.
    } catch (e: Throwable) {
        e.printStackTrace()
    }

    fun safeCasts(any: Any): String? {
        return any as? String   // If any cannot be converted to String, it'll return null.
    }

    fun collectionsOfNullableType() {
        val nullableList: List<String?> = listOf("Arthur", "Lancelot", null, "Bedivere", "Merlin")
        val notNullList: List<String> = nullableList.filterNotNull()

        Log.e("FirstKotlinClass", "nullableList: $nullableList")
        Log.e("FirstKotlinClass", "notNullList: $notNullList")
    }
}