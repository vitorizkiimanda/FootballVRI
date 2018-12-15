package com.example.vitorizkiimanda.footballschedulevri.database

data class FavouriteMatch(
        val id: Int?,
        val eventId: String?,
        val eventDate: String?,
        val homeId: String?,
        val homeName: String?,
        val homeScore: String?,
        val homeGoals: String?,
        val homeShots: String?,
        val homeKeeper: String?,
        val homeDefense: String?,
        val homeMidfield: String?,
        val homeForward: String?,
        val homeSubs: String?,
        val awayId: String?,
        val awayName: String?,
        val awayScore: String?,
        val awayGoals: String?,
        val awayShots: String?,
        val awayKeeper: String?,
        val awayDefense: String?,
        val awayMidfield: String?,
        val awayForward: String?,
        val awaySubs: String?) {

    companion object {
        const val TABLE_FAVORITE_MATCH: String = "TABLE_FAVORITE_MATCH"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val HOME_ID: String = "MATCH_HOME_ID"
        const val HOME_NAME: String = "MATCH_HOME_NAME"
        const val HOME_SCORE: String = "MATCH_HOME_SCORE"
        const val HOME_GOALS: String = "MATCH_HOME_GOALS"
        const val HOME_SHOTS: String = "MATCH_HOME_SHOTS"
        const val HOME_KEEPER: String = "MATCH_HOME_KEEPER"
        const val HOME_DEFENSE: String = "MATCH_HOME_DEFENSE"
        const val HOME_MIDFIELD: String = "MATCH_HOME_MIDFIELD"
        const val HOME_FORWARD: String = "MATCH_HOME_FORWARD"
        const val HOME_SUBS: String = "MATCH_HOME_SUBS"
        const val AWAY_ID: String = "MATCH_AWAY_ID"
        const val AWAY_NAME: String = "MATCH_AWAY_NAME"
        const val AWAY_SCORE: String = "MATCH_AWAY_SCORE"
        const val AWAY_GOALS: String = "MATCH_AWAY_GOALS"
        const val AWAY_SHOTS: String = "MATCH_AWAY_SHOTS"
        const val AWAY_KEEPER: String = "MATCH_AWAY_KEEPER"
        const val AWAY_DEFENSE: String = "MATCH_AWAY_DEFENSE"
        const val AWAY_MIDFIELD: String = "MATCH_AWAY_MIDFIELD"
        const val AWAY_FORWARD: String = "MATCH_AWAY_FORWARD"
        const val AWAY_SUBS: String = "MATCH_AWAY_SUBS"
    }
}