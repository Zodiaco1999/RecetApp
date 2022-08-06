package com.mintic22g1.recetapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.mintic22g1.recetapp.data.models.ServiceItemModel
import com.mintic22g1.recetapp.databinding.ItemServiceBinding
import com.mintic22g1.recetapp.interfaces.OnServiceClickListener

class ServiceAdapter(var list: List<ServiceItemModel>) : RecyclerView.Adapter<ServiceAdapter.ViewHolder>()  {

    var listener: OnServiceClickListener? = null

    class ViewHolder(val view: ItemServiceBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return  ViewHolder(ItemServiceBinding.inflate(inflater, parent, false))
    }

    private var _selected: MutableLiveData<ServiceItemModel> = MutableLiveData()
    val selected: LiveData<ServiceItemModel> get() = _selected

    fun selected(item: ServiceItemModel){
        _selected.postValue(item)
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

    fun changeDataSet(list: List<ServiceItemModel>){
        this.list = list
        notifyDataSetChanged()
    }
}