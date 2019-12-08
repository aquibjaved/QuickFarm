package com.skillenza.lastminute.ui.main.mylistings

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.skillenza.lastminute.R
import com.skillenza.lastminute.ui.base.BaseFragment
import com.skillenza.lastminute.ui.base.CustomBottomSheetDialogFragment
import com.skillenza.lastminute.ui.main.MainViewModel
import kotlinx.android.synthetic.main.dialog_new_listing.*
import kotlinx.android.synthetic.main.fragment_my_listings.*

class MyListingsFragment: BaseFragment() {

    private val mainViewModel by lazy { ViewModelProviders.of(activity!!).get(MainViewModel::class.java) }
    private var addListingDialog: CustomBottomSheetDialogFragment? = null

    override fun getLayoutResourceId() = R.layout.fragment_my_listings

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        attachViewModel()
        attachClickListeners()

        if (mainViewModel.myListings.isEmpty()) {
            baseActivity.showProgressDialog("Fetching your listings")
            mainViewModel.getMyListings()
        } else {
            showList()
        }

        srlMyListings.setOnRefreshListener {
            baseActivity.showProgressDialog("Fetching your listings")
            mainViewModel.getMyListings()
        }
    }

    private fun attachClickListeners() {
        fabAddListing.setOnClickListener {
            addListingDialog?.let {
                if (it.isShowing) {
                    it.dismiss()
                }
            }
            addListingDialog = CustomBottomSheetDialogFragment(activity!!).apply {
                setContentView(layoutInflater.inflate(R.layout.dialog_new_listing, null))
                setCancelable(false)
                btnAddListing?.setOnClickListener {
                    listOf<AppCompatEditText>(etName, etType, etPrice).forEach {
                        it.addTextChangedListener(object: TextWatcher {
                            override fun afterTextChanged(s: Editable?) {}
                            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                                it.error = null
                            }
                        })
                    }
                    val name = etName.text.toString()
                    if (name.trim().isEmpty()) {
                        etName.error = "Enter a valid name"
                        return@setOnClickListener
                    }
                    val type = etType.text.toString()
                    if (type.trim().isEmpty()) {
                        etName.error = "Enter a valid type"
                        return@setOnClickListener
                    }
                    val price = etPrice.text.toString()
                    if (price.trim().isEmpty()) {
                        etPrice.error = "Enter a price"
                        return@setOnClickListener
                    }

                    baseActivity.showProgressDialog("Adding listing")
                    mainViewModel.createListing(name, type, price)
                }
                show()
            }
        }
    }

    private fun attachViewModel() {
        mainViewModel.successMyListings.observe(this, Observer { successMyListings ->
            if (successMyListings != null) {
                baseActivity.hideProgressDialog()
                srlMyListings.isRefreshing = false
                if (successMyListings) {
                    showList()
                } else {
                    baseActivity.showSnackBar(mainViewModel.message, Snackbar.LENGTH_LONG)
                }
            }
        })

        mainViewModel.successCreateListings.observe(this, Observer { successCreateListings ->
            if (successCreateListings != null) {
                if (successCreateListings) {
                    mainViewModel.getMyListings()
                    addListingDialog?.dismiss()
                    baseActivity.showToast("Booking created", Toast.LENGTH_LONG)
                    mainViewModel.successCreateListings.value = null
                } else {
                    baseActivity.showSnackBar(mainViewModel.message, Snackbar.LENGTH_LONG)
                }
            }
        })
    }

    private fun showList() {
        if (mainViewModel.myListings.isNotEmpty()) {
            rvMyListings.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = MyListingsAdapter(mainViewModel.myListings)
            }
            srlMyListings.visibility = View.VISIBLE
            tvNoMyListings.visibility = View.GONE
        } else {
            srlMyListings.visibility = View.GONE
            tvNoMyListings.visibility = View.VISIBLE
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MyListingsFragment()
    }
}
