package com.example.assign2

import com.google.gson.annotations.SerializedName

data class Barcode(
    @SerializedName("C005") val c005: C005
)

data class C005(
    @SerializedName("total_count") val  totalCount:String,
    @SerializedName("row")  val row:List<Row>,
    @SerializedName("RESULT") val result: Result
)

data class Result(
    @SerializedName("MSG") val msg:String,
    @SerializedName("CODE") val code:String
    )

data class Row(
    @SerializedName("PRDLST_REPORT_NO") val PRDLST_REPORT_NO:String,
    @SerializedName("PRMS_DT") val PRMS_DT:String,
    @SerializedName("END_DT") val END_DT:String,
    @SerializedName("PRDLST_NM") val PRDLST_NM:String,
    @SerializedName("POG_DAYCNT") val POG_DAYCNT:String,
    @SerializedName("PRDLST_DCNM") val PRDLST_DCNM:String,
    @SerializedName("BSSH_NM") val BSSH_NM:String,
    @SerializedName("INDUTY_NM") val INDUTY_NM:String,
    @SerializedName("SITE_ADDR") val SITE_ADDR:String,
    @SerializedName("CLSBIZ_DT") val CLSBIZ_DT:String,
    @SerializedName("BAR_CD") val BAR_CD:String
    )
