package com.tokowiwin.tokowiwin.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tokowiwin.tokowiwin.R
import com.tokowiwin.tokowiwin.data.remote.response.ProductsDataItem
import com.tokowiwin.tokowiwin.databinding.ListProductsBinding

class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.ListViewHolder>() {
    inner class ListViewHolder (val binding: ListProductsBinding) : RecyclerView.ViewHolder(binding.root)

    private var data = mutableListOf<ProductsDataItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ListProductsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<ProductsDataItem>){
        data.clear()
        data = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val temp = data[position]
        holder.binding.txtProductName.text = temp.productName.toString()
        holder.binding.txtPrice.text = temp.productPrice.toString()
        holder.binding.txtStock.text = "Stock : ${temp.productStock.toString()}"
        if(temp.productPic!=""){
            Glide.with(holder.itemView.context)
                .load(temp.productPic)
                .into(holder.binding.imgProduct)
        }else{
            Glide.with(holder.itemView.context)
                .load(holder.binding.root.context.getDrawable(R.drawable.example))
                .into(holder.binding.imgProduct)
        }

        holder.binding.btnTambahKeranjang.setOnClickListener {
            var qty = 0
            if (holder.binding.txtQty.text.toString() != "") {
                qty = Integer.parseInt(holder.binding.txtQty.text.toString())
            }
            setOnItemClickCallback?.onItemClicked(temp, qty)
        }
    }

    private var setOnItemClickCallback : OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.setOnItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ProductsDataItem, qty: Int)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}