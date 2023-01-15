package com.tokowiwin.tokowiwin.ui.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tokowiwin.tokowiwin.R
import com.tokowiwin.tokowiwin.data.remote.response.TransactionProductsItem
import com.tokowiwin.tokowiwin.databinding.ListDetailBinding

class DetailAdapter() : RecyclerView.Adapter<DetailAdapter.ListViewHolder>() {
    inner class ListViewHolder (val binding: ListDetailBinding) : RecyclerView.ViewHolder(binding.root)

    private var data = mutableListOf<TransactionProductsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ListDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<TransactionProductsItem>){
        data.clear()
        data = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val temp = data[position]
        holder.binding.txtDetailPrice.text = temp.productPrice
        holder.binding.txtDetailProductName.text = temp.productName
        holder.binding.txtDetailQty.text = "Kuantitas: ${temp.productQty.toString()}"
        holder.binding.txtDetailTotalItemPrice.text = temp.productTotalPrice
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

    override fun getItemCount(): Int {
        return data.size
    }
}