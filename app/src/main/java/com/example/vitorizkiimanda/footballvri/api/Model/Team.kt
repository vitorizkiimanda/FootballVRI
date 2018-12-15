package com.example.vitorizkiimanda.footballschedulevri.Api.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Team(
        @SerializedName("idTeam") var idTeam: String?,
        @SerializedName("strTeam") var strTeam: String,
        @SerializedName("strTeamBadge") var strTeamBadge: String

) : Serializable, Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(idTeam)
                parcel.writeString(strTeam)
                parcel.writeString(strTeamBadge)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Team> {
                override fun createFromParcel(parcel: Parcel): Team {
                        return Team(parcel)
                }

                override fun newArray(size: Int): Array<Team?> {
                        return arrayOfNulls(size)
                }
        }
}