package com.eclair.myfitnessgoal.models

import java.io.Serializable

class Users : Serializable {
    var userName: String? = null
    var email: String? = null
    var password: String? = null
    var userId: String? = null
    var goalType: String? = null
    var activeness: String? = null
    var sex: String? = null
    var height: String? = null
    var weight: String? = null
    var dob: String? = null
    var age: String? = null
    var reqCalorie: String? = null
    var profilePic: String? = null

    constructor() {}
    constructor(userName: String?, email: String?, userId: String?) {
        this.userName = userName
        this.email = email
        this.userId = userId
    }

    constructor(userName: String?, email: String?, userId: String?, password: String?) {
        this.userName = userName
        this.email = email
        this.userId = userId
        this.password = password
    }

    constructor(
        userName: String?,
        email: String?,
        password: String?,
        goalType: String?,
        activeness: String?,
    ) {
        this.userName = userName
        this.email = email
        this.password = password
        this.goalType = goalType
        this.activeness = activeness
    }

    constructor(
        userName: String?,
        email: String?,
        password: String?,
        goalType: String?,
        activeness: String?,
        sex: String?,
    ) {
        this.userName = userName
        this.email = email
        this.password = password
        this.goalType = goalType
        this.activeness = activeness
        this.sex = sex
    }

    constructor(
        userName: String?,
        email: String?,
        password: String?,
        goalType: String?,
        activeness: String?,
        sex: String?,
        height: String?,
        weight: String?,
        dob: String?,
        reqCalorie: String?,
    ) {
        this.userName = userName
        this.email = email
        this.password = password
        this.goalType = goalType
        this.activeness = activeness
        this.sex = sex
        this.height = height
        this.weight = weight
        this.dob = dob
        this.reqCalorie = reqCalorie
    }


}