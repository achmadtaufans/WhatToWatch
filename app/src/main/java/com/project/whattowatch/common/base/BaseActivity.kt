package com.project.whattowatch.common.base

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.project.whattowatch.WhatToWatch

open class BaseActivity : AppCompatActivity() {

    private var mMyApp: WhatToWatch? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMyApp = this.applicationContext as WhatToWatch
    }

    override fun onResume() {
        super.onResume()
        mMyApp?.setCurrentActivity(this)
    }

    override fun onPause() {
        clearReferences()
        super.onPause()
    }

    override fun onDestroy() {
        clearReferences()
        super.onDestroy()
    }

    private fun clearReferences() {
        val currActivity = mMyApp?.getCurrentActivity()
        if (this == currActivity) mMyApp?.setCurrentActivity(null)
    }

    fun showKeySoft(shown : Boolean){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        if(shown){
            imm!!.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
        }else{
            imm!!.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        }
    }
}