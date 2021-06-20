package com.eclair.myfitnessgoal.roomdb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FitnessViewModelFactory(private val fitnessRepo: FitnessRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FitnessViewModel(fitnessRepo) as T
    }
}