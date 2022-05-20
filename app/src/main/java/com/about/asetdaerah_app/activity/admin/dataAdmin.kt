package com.about.asetdaerah_app.activity.admin

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class dataAdmin (

    var nip : String?,
    var nama :String ?,
    var password : String ?,
    var notelp : String ?
    ) : Parcelable