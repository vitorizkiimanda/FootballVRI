package com.example.vitorizkiimanda.footballvri.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Player(
        @SerializedName("idPlayer") var idTeam: String?,
        @SerializedName("strPlayer") var strPlayer: String?,
        @SerializedName("strCutout") var strCutout: String?,
        @SerializedName("strThumb") var strThumb: String?,
        @SerializedName("strWeight") var strWeight: String?,
        @SerializedName("strHeight") var strHeight: String?,
        @SerializedName("strPosition") var strPosition: String?,
        @SerializedName("strDescriptionEN") var strDescriptionEN: String?
): Serializable, Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idTeam)
        parcel.writeString(strPlayer)
        parcel.writeString(strCutout)
        parcel.writeString(strThumb)
        parcel.writeString(strWeight)
        parcel.writeString(strHeight)
        parcel.writeString(strPosition)
        parcel.writeString(strDescriptionEN)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }
}