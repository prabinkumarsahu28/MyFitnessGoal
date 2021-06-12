package com.eclair.myfitnessgoal.models

class HomeBlogs {
    var heading: String? = null
    var img: String? = null
    var link: String? = null
    var type: String? = null

    constructor() {}
    constructor(heading: String?, img: String?, link: String?, type: String?) {
        this.heading = heading
        this.img = img
        this.link = link
        this.type = type
    }

}