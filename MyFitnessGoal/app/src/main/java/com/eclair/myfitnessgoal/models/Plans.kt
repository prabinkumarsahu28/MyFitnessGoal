package com.eclair.myfitnessgoal.models

import java.io.Serializable

class Plans : Serializable {
    var difficulty: String? = null
    var duration: String? = null
    var img: String? = null
    var name: String? = null
    var perWeek: String? = null

    constructor() {}
    constructor(
        difficulty: String?,
        duration: String?,
        img: String?,
        name: String?,
        perWeek: String?
    ) {
        this.difficulty = difficulty
        this.duration = duration
        this.img = img
        this.name = name
        this.perWeek = perWeek
    }


}