package com.example.newproject

import android.app.Dialog
import android.content.Context
import android.view.Window
class CustomDialog(context: Context): Dialog(context) {

init {
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    setContentView(R.layout.activity_custom_dailogue)


    }


}

