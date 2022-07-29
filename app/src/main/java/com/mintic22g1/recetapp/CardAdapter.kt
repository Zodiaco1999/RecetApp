package com.mintic22g1.recetapp

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.mintic22g1.recetapp.databinding.ItemCardBinding
import com.mintic22g1.recetapp.databinding.ItemServiceBinding
import java.util.Collections.list

class CardAdapter(val list: List<ServiceItemModel>) : RecyclerView.Adapter<CardAdapter.ViewHolder>()  {

    var listener: OnServiceClickListener? = null

    class ViewHolder(val view: ItemCardBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return  ViewHolder(ItemCardBinding.inflate(inflater, parent, false))
    }

    private var _selected: MutableLiveData<ServiceItemModel> = MutableLiveData()
    val selected: LiveData<ServiceItemModel> get() = _selected

    fun selected(item: ServiceItemModel){
        _selected.postValue(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.view.itemServiceImage.setImageResource(item.image)
        holder.view.itemServiceTitle.text = item.title

        holder.view.root.setOnClickListener {
            listener?.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}