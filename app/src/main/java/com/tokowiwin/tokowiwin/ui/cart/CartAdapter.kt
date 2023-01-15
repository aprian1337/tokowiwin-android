package com.tokowiwin.tokowiwin.ui.cart

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tokowiwin.tokowiwin.R
import com.tokowiwin.tokowiwin.data.remote.response.CartsDataItem
import com.tokowiwin.tokowiwin.databinding.ListCartsBinding
import com.tokowiwin.tokowiwin.utils.Method
import kotlinx.coroutines.NonCancellable.cancel
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class CartAdapter() : RecyclerView.Adapter<CartAdapter.ListViewHolder>() {
    inner class ListViewHolder (val binding: ListCartsBinding) : RecyclerView.ViewHolder(binding.root)

    private var data = mutableListOf<CartsDataItem>()
    private val handler: Handler = Handler()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ListCartsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<CartsDataItem>){
        data.clear()
        data = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val temp = data[position]
        holder.binding.txtProductName.text = temp.productName.toString()
        holder.binding.txtPrice.text = temp.productPrice.toString()
        holder.binding.txtTotalItemPrice.text = temp.productTotalPrice.toString()
        holder.binding.txtQty.setText(temp.qty.toString())
        if(temp.productPic!=""){
            Glide.with(holder.itemView.context)
                .load(temp.productPic)
                .into(holder.binding.imgProduct)
        }else{
            Glide.with(holder.itemView.context)
                .load(holder.binding.root.context.getDrawable(R.drawable.example))
                .into(holder.binding.imgProduct)
        }

        holder.binding.txtHapus.setOnClickListener {
            setOnItemClickCallback?.onItemClicked(temp, 0, Method.DELETE)
        }

        holder.binding.txtQty.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed({
                    if (holder.binding.txtQty.text.toString() != "") {
                        val qty = Integer.parseInt(holder.binding.txtQty.text.toString())
                        setOnItemClickCallback?.onItemClicked(temp, qty, Method.UPDATE)
                    } }, 1500)
            }
        })
    }

    private var setOnItemClickCallback : OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.setOnItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: CartsDataItem, qty: Int, type: Method)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}