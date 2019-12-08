package com.skillenza.lastminute.ui.base

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.skillenza.lastminute.R

class CustomBottomSheetDialogFragment(context: Context): BottomSheetDialog(context) {
    private lateinit var behaviour: BottomSheetBehavior<FrameLayout>

    override fun onStart() {
        super.onStart()
        behaviour.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun setContentView(view: View) {
        super.setContentView(view)
        val bottomSheet = window?.decorView?.findViewById<FrameLayout>(R.id.design_bottom_sheet)
        behaviour = BottomSheetBehavior.from(bottomSheet)
        behaviour.state = BottomSheetBehavior.STATE_EXPANDED
        behaviour.setBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback(){
            override fun onSlide(view: View, p1: Float) {
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
            override fun onStateChanged(p0: View, p1: Int) {
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        })
    }
}
