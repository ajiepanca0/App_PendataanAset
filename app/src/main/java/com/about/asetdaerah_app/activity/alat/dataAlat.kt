package com.about.asetdaerah_app.activity.alat

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class dataAlat (

    var idasetalat : String?,
    var namaalat :String ?,
    var kondisialat : String ?,
    var jumlahalat : String ?,
    var biayaalat : String ?,
    var lainnya : String ?
    ) : Parcelable