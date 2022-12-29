package com.tokowiwin.tokowiwin.ui.transactions

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tokowiwin.tokowiwin.R
import com.tokowiwin.tokowiwin.data.remote.response.ProductsDataItem
import com.tokowiwin.tokowiwin.data.remote.response.TransactionsDataItem
import com.tokowiwin.tokowiwin.databinding.ListTransactionsBinding

class TransactionsAdapter() : RecyclerView.Adapter<TransactionsAdapter.ListViewHolder>() {
    inner class ListViewHolder (val binding: ListTransactionsBinding) : RecyclerView.ViewHolder(binding.root)

    private var data = mutableListOf<TransactionsDataItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ListTransactionsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<TransactionsDataItem>){
        data.clear()
        data = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val temp = data[position]
        holder.binding.txtProductName.text = temp.productName
        holder.binding.txtInfo.text = temp.anotherProduct
        holder.binding.txtDate.text = temp.date
        if(temp.productPic!=""){
            Glide.with(holder.itemView.context)
                .load(temp.productPic)
                .into(holder.binding.imgProduct)
        }else{
            Glide.with(holder.itemView.context)
                .load(holder.binding.root.context.getDrawable(R.drawable.example))
                .into(holder.binding.imgProduct)
        }
    }

    private var setOnItemClickCallback : OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.setOnItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ProductsDataItem)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}