package com.mintic22g1.recetapp.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mintic22g1.recetapp.data.models.RecipeItemModel
import com.mintic22g1.recetapp.data.models.ServiceItemModel
import com.mintic22g1.recetapp.data.repositories.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: HomeRepository): ViewModel() {
    private var _service: MutableLiveData<List<ServiceItemModel>> = MutableLiveData()
    val service: LiveData<List<ServiceItemModel>> get() = _service

    private var _recipe: MutableLiveData<List<RecipeItemModel>> = MutableLiveData()
    val recipe: LiveData<List<RecipeItemModel>> get() = _recipe

    private var _selected: MutableLiveData<RecipeItemModel> = MutableLiveData()
    val selected: LiveData<RecipeItemModel> get() = _selected

    fun services() {
        viewModelScope.launch {
            _service.postValue(repo.services())
        }
    }

    fun recipes(category: String?) {
        viewModelScope.launch {
            _recipe.postValue(repo.recipes(category))
        }
    }

    fun recipeSelected(item: RecipeItemModel) {
        _selected.postValue(item)
    }
}