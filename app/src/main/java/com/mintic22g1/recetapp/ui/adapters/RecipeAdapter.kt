package com.mintic22g1.recetapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide
import com.mintic22g1.recetapp.data.models.RecipeItemModel
import com.mintic22g1.recetapp.databinding.ItemRecipeBinding
import com.mintic22g1.recetapp.interfaces.OnRecipeClickListener

import kotlin.collections.List;

class RecipeAdapter(var list: List<RecipeItemModel>): RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
    class ViewHolder(val view: ItemRecipeBinding): RecyclerView.ViewHolder(view.root)

    var _listener: OnRecipeClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemRecipeBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.view.itemRecipeName.text = item.name
        holder.view.itemRecipeSumary.text = "${item.star} estrellas - ${item.duration} min"
        holder.view.itemRecipeStar.rating = (item.star.toFloat() / 5.0).toFloat()
        holder.view.root.setOnClickListener {
            _listener?.onClick(item)
        }
        Glide.with(holder.view.root).load(item.image).centerCrop().into(holder.view.itemRecipeImage)
    }

    fun changeDataSet(list: kotlin.collections.List<RecipeItemModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return  this.list.size
    }


}