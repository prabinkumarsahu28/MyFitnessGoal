package com.eclair.myfitnessgoal.models

import java.io.Serializable

class Users : Serializable {
    private var userName: String? = null
    private var email: String? = null
    private var password: String? = null
    private var userId: String? = null
    var profilePic: String? = null

    constructor() {}
    constructor(userName: String?, email: String?, userId: String?, profilePic: String?) {
        this.userName = userName
        this.email = email
        this.userId = userId
        this.profilePic = profilePic
    }

    constructor(userName: String?, email: String?, password: String?) {
        this.userName = userName
        this.email = email
        this.password = password
    }

}