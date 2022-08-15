package com.mintic22g1.recetapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mintic22g1.recetapp.data.models.RecipeItemModel
import com.mintic22g1.recetapp.data.models.ServiceItemModel
import com.mintic22g1.recetapp.databinding.ItemCardBinding
import com.mintic22g1.recetapp.interfaces.OnRecipeClickListener
import com.mintic22g1.recetapp.interfaces.OnServiceClickListener

class CardAdapter(var list: List<RecipeItemModel>) : RecyclerView.Adapter<CardAdapter.ViewHolder>()  {
    class ViewHolder(val view: ItemCardBinding): RecyclerView.ViewHolder(view.root)

    var _listener: OnRecipeClickListener? = null

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
        holder.view.itemServiceTitle.text = item.name
        holder.view.root.setOnClickListener {
            _listener?.onClick(item)
        }

        Glide.with(holder.view.root).load(item.image).centerCrop().into(holder.view.itemServiceImage)
    }

    fun changeDataSet(list: List<RecipeItemModel>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }


}