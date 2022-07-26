package com.mintic22g1.recetapp

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.mintic22g1.recetapp.databinding.ItemCardBinding
import com.mintic22g1.recetapp.databinding.ItemServiceBinding
import java.util.Collections.list

class ServiceAdapter(val list: List<ServiceItemModel>) : RecyclerView.Adapter<ServiceAdapter.ViewHolder>()  {

    var listener: OnServiceClickListener? = null

    class ViewHolder(val view: ItemServiceBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return  ViewHolder(ItemServiceBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.view.itemServiceImage.setImageResource(item.image)

        holder.view.root.setOnClickListener {
            listener?.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}