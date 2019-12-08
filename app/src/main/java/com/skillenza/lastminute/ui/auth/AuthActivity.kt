package com.skillenza.lastminute.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.skillenza.lastminute.R
import com.skillenza.lastminute.ui.main.MainActivity
import com.skillenza.lastminute.ui.base.BaseActivity
import com.skillenza.lastminute.utils.PreferenceManager
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity: BaseActivity() {

    private val authViewModel by lazy { ViewModelProviders.of(this).get(AuthViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        if (!PreferenceManager.userId.isBlank()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }
        attachViewModel()
        attachClickListeners()
    }

    private fun attachClickListeners() {
        etPhone.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                etPhone.error = null
            }
        })

        btnLogin.setOnClickListener {
            val phoneNumber = etPhone.text.toString()
            if (phoneNumber.length != 10) {
                etPhone.error = "Enter valid phone number"
                return@setOnClickListener
            }
            showProgressDialog("Authenticating...")
            authViewModel.login(phoneNumber)
        }
    }

    private fun attachViewModel() {
        authViewModel.successLogin.observe(this, Observer { successLogin->
            if (successLogin != null) {
                hideProgressDialog()
                if (successLogin) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    showSnackBar(authViewModel.message, Snackbar.LENGTH_LONG)
                }
            }
        })
    }
}
