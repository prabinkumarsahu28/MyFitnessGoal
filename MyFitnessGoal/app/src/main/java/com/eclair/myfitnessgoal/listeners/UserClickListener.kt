package com.eclair.myfitnessgoal.listeners

import com.eclair.myfitnessgoal.roomdb.UserEntity

interface UserClickListener {

    fun onUserDetailsClicked(userEntity: UserEntity)
}