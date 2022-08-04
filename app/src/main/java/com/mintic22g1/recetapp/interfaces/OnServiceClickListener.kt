package com.mintic22g1.recetapp.interfaces

import com.mintic22g1.recetapp.data.models.ServiceItemModel

interface OnServiceClickListener {
    fun onClick(item: ServiceItemModel)
}