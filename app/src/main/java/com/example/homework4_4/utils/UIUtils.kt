package com.example.homework4_4.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

object UIUtils {
    fun EditText.showKeyboard(context: Context){
        requestFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }
}