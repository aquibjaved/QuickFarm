package com.skillenza.lastminute.ui.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, V: RecyclerView.ViewHolder>: RecyclerView.Adapter<V>() {

    val TAG = this.javaClass.simpleName
}
