@file:Suppress("DEPRECATION")

package com.skillenza.lastminute.ui.base

import android.app.ProgressDialog
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


abstract class BaseActivity: AppCompatActivity() {

    val TAG = this.javaClass.simpleName

    private val inputMethodManager by lazy { getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }
    private var progressDialog: ProgressDialog? = null
    private var snackBar: Snackbar? = null
    private var toast: Toast? = null

    fun showProgressDialog(message: String) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this, ProgressDialog.STYLE_SPINNER)
            progressDialog?.setCancelable(false)
        }
        progressDialog?.setMessage(message)
        progressDialog?.show()
    }

    fun hideProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing)
            progressDialog?.dismiss()
    }

    fun showKeyboard() {
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun hideKeyboard(view: View) {
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
    }

    val rootView: View
        get() =  window.decorView.findViewById(android.R.id.content)

    fun replaceFragment(containerId: Int, fragment: Fragment, title: Int) {
        supportFragmentManager.beginTransaction().replace(containerId, fragment, getString(title)).commit()
        supportActionBar?.setTitle(title)
    }

    fun showSnackBar(message: String, length: Int) {
        snackBar = Snackbar.make(rootView, message, length)
        snackBar?.show()
    }

    fun showSnackBar(message: String, length: Int, buttonText: String, listener: View.OnClickListener) {
        snackBar = Snackbar.make(rootView, message, length)
            .setAction(buttonText, listener)
        snackBar?.show()
    }

    fun dismissSnackBar() {
        snackBar?.dismiss()
    }

    fun showToast(message: String, length: Int) {
        cancelToast()
        toast = Toast.makeText(this, message, length)
        toast?.show()
    }

    fun cancelToast() {
        toast?.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()

        hideProgressDialog()
    }
}
