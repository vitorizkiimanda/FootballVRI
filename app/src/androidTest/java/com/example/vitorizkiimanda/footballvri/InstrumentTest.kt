package com.example.vitorizkiimanda.footballvri


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.vitorizkiimanda.footballvri.R.id.*
import com.example.vitorizkiimanda.footballvri.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InstrumentTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun testAll() {
        delay()

        //Matches Fragment
        onView(withId(rvMatchesNext))
                .check(matches(isDisplayed()))
        delay()
        onView(withId(viewpager_matches)).perform(swipeLeft())
        delay()
        onView(withId(rvMatchesLast))
                .check(matches(isDisplayed()))
        delay()

        //Match Detail
        onView(withId(rvMatchesLast)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(14))
        delay()
        onView(withId(rvMatchesLast)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(14, click()))

        //into Favourite match
        delay()
        onView(withId(image_home)).check(matches(isDisplayed()))
        onView(withId(image_away)).check(matches(isDisplayed()))
        delay()
        onView(withId(add_to_favorite)).perform(click())
        delay()
        pressBack()

        //to Teams
        delay()
        onView(withId(navigation_teams)).perform(click())
        delay()
        onView(withId(list_team)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        //fav team
        delay()
        onView(withId(player_header)).perform(scrollTo())
        delay()
        onView(withId(add_to_favorite)).perform(click())
        delay()

        pressBack()

        //to Fav Fragments
        delay()
        onView(withId(navigation_favourites)).perform(click())
        delay()
        onView(withId(rvMatchesFav)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        delay()
        onView(withId(add_to_favorite)).perform(click())
        delay()

        pressBack()

        delay()
        onView(withId(viewpager_favourites)).perform(swipeLeft())
        delay()
        onView(withId(list_team)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        delay()
        onView(withId(add_to_favorite)).perform(click())
        delay()

        pressBack()
        delay()
    }

    private fun delay(){
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}
