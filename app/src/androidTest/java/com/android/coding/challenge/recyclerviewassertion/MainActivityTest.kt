package com.android.coding.challenge.recyclerviewassertion


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.android.coding.challenge.R
import com.android.coding.challenge.module.view.activity.HomeActivity
import com.android.coding.challenge.recyclerviewassertion.CustomAssertions.Companion.hasItemCount
import org.junit.Rule
import org.junit.Test

/**
 * Created on 9/28/2019
 * Email ynaseem@an10.io
 * Organization AN10
 */

class MainActivityTest {
  @Rule
  @JvmField
  var activityRule = ActivityTestRule<HomeActivity>(HomeActivity::class.java)


  @Test
  fun countProgramsWithViewAssertion() {
    onView(withId(R.id.rvMoviesData))
        .check(hasItemCount(24))
  }
}
