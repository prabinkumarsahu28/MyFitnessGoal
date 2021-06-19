package com.eclair.myfitnessgoal.listeners

import com.eclair.myfitnessgoal.roomdb.ExerciseEntity

interface ExerciseClickListener {
    fun onClicked(exerciseEntity: ExerciseEntity)
}