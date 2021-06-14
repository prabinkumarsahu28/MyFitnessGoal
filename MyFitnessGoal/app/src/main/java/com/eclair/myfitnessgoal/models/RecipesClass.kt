package com.eclair.myfitnessgoal.models

import java.io.Serializable
import java.util.*

class RecipesClass : Serializable{
    var cal : String? = null
    var CarbsPer : String? = null
    var CarbsWt : String? = null
    var FatPer : String? = null
    var FatWt : String? = null
    var ImgLink : String? = null
    var Name : String? = null
    var ProteinPer : String? = null
    var ProteinWt : String? = null

    constructor(){

    }
    constructor(cal : String?,CarbsPer : String?,CarbsWt : String?,FatPer : String?,FatWt : String?,ImgLink : String?,
                Name : String?,ProteinPer : String?,ProteinWt : String?) {
        this.cal = cal
        this.CarbsPer = CarbsPer
        this.CarbsWt = CarbsWt
        this.FatPer = FatPer
        this.FatWt = FatWt
        this.ImgLink = ImgLink
        this.Name = Name
        this.ProteinPer = ProteinPer
        this.ProteinWt = ProteinWt

    }

}