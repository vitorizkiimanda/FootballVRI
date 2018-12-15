package com.example.vitorizkiimanda.footballschedulevri.Api.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Match(
        @SerializedName("dateEvent") var dateEvent: String?,
        @SerializedName("idAwayTeam") var idAwayTeam: String,
        @SerializedName("idEvent") var idEvent: String,
        @SerializedName("idHomeTeam") var idHomeTeam: String,
        @SerializedName("idLeague") var idLeague: String?,
        @SerializedName("idSoccerXML") var idSoccerXML: String?,
        @SerializedName("intAwayScore") var intAwayScore: String?,
        @SerializedName("intHomeScore") var intHomeScore: String?,
        @SerializedName("intHomeShots") var intHomeShots: String?,
        @SerializedName("intAwayShots") var intAwayShots: String?,
        @SerializedName("intRound") var intRound: String?,
        @SerializedName("strAwayFormation") var strAwayFormation: String?,
        @SerializedName("strAwayGoalDetails") var strAwayGoalDetails: String?,
        @SerializedName("strAwayLineupDefense") var strAwayLineupDefense: String?,
        @SerializedName("strAwayLineupForward") var strAwayLineupForward: String?,
        @SerializedName("strAwayLineupGoalkeeper") var strAwayLineupGoalkeeper: String?,
        @SerializedName("strAwayLineupMidfield") var strAwayLineupMidfield: String?,
        @SerializedName("strAwayLineupSubstitutes") var strAwayLineupSubstitutes: String?,
        @SerializedName("strAwayRedCards") var strAwayRedCards: String?,
        @SerializedName("strAwayTeam") var strAwayTeam: String?,
        @SerializedName("strAwayYellowCards") var strAwayYellowCards: String?,
        @SerializedName("strDate") var strDate: String?,
        @SerializedName("strEvent") var strEvent: String?,
        @SerializedName("strFilename") var strFilename: String?,
        @SerializedName("strHomeFormation") var strHomeFormation: String?,
        @SerializedName("strHomeGoalDetails") var strHomeGoalDetails: String?,
        @SerializedName("strHomeLineupDefense") var strHomeLineupDefense: String?,
        @SerializedName("strHomeLineupForward") var strHomeLineupForward: String?,
        @SerializedName("strHomeLineupGoalkeeper") var strHomeLineupGoalkeeper: String?,
        @SerializedName("strHomeLineupMidfield") var strHomeLineupMidfield: String?,
        @SerializedName("strHomeLineupSubstitutes") var strHomeLineupSubstitutes: String?,
        @SerializedName("strHomeRedCards") var strHomeRedCards: String?,
        @SerializedName("strHomeTeam") var strHomeTeam: String?,
        @SerializedName("strHomeYellowCards") var strHomeYellowCards: String?,
        @SerializedName("strLeague") var strLeague: String?,
        @SerializedName("strLocked") var strLocked: String?,
        @SerializedName("strSeason") var strSeason: String?,
        @SerializedName("strSport") var strSport: String?,
        @SerializedName("strTime") var strTime: String?
) : Serializable, Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
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
        parcel.writeString(dateEvent)
        parcel.writeString(idAwayTeam)
        parcel.writeString(idEvent)
        parcel.writeString(idHomeTeam)
        parcel.writeString(idLeague)
        parcel.writeString(idSoccerXML)
        parcel.writeString(intAwayScore)
        parcel.writeString(intHomeScore)
        parcel.writeString(intHomeShots)
        parcel.writeString(intAwayShots)
        parcel.writeString(intRound)
        parcel.writeString(strAwayFormation)
        parcel.writeString(strAwayGoalDetails)
        parcel.writeString(strAwayLineupDefense)
        parcel.writeString(strAwayLineupForward)
        parcel.writeString(strAwayLineupGoalkeeper)
        parcel.writeString(strAwayLineupMidfield)
        parcel.writeString(strAwayLineupSubstitutes)
        parcel.writeString(strAwayRedCards)
        parcel.writeString(strAwayTeam)
        parcel.writeString(strAwayYellowCards)
        parcel.writeString(strDate)
        parcel.writeString(strEvent)
        parcel.writeString(strFilename)
        parcel.writeString(strHomeFormation)
        parcel.writeString(strHomeGoalDetails)
        parcel.writeString(strHomeLineupDefense)
        parcel.writeString(strHomeLineupForward)
        parcel.writeString(strHomeLineupGoalkeeper)
        parcel.writeString(strHomeLineupMidfield)
        parcel.writeString(strHomeLineupSubstitutes)
        parcel.writeString(strHomeRedCards)
        parcel.writeString(strHomeTeam)
        parcel.writeString(strHomeYellowCards)
        parcel.writeString(strLeague)
        parcel.writeString(strLocked)
        parcel.writeString(strSeason)
        parcel.writeString(strSport)
        parcel.writeString(strTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Match> {
        override fun createFromParcel(parcel: Parcel): Match {
            return Match(parcel)
        }

        override fun newArray(size: Int): Array<Match?> {
            return arrayOfNulls(size)
        }
    }
}