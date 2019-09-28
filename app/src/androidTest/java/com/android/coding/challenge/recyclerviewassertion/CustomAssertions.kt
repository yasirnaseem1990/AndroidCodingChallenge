package com.android.coding.challenge.recyclerviewassertion


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers

/**
 * Created on 9/28/2019
 * Email ynaseem@an10.io
 * Organization AN10
 */

class CustomAssertions {
  companion object {
    fun hasItemCount(count: Int): ViewAssertion {
      return RecyclerViewItemCountAssertion(count)
    }
  }

  private class RecyclerViewItemCountAssertion(private val count: Int) : ViewAssertion {

    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
      if (noViewFoundException != null) {
        throw noViewFoundException
      }

      check(view is RecyclerView) { "The asserted view is not RecyclerView" }

      checkNotNull(view.adapter) { "No adapter is assigned to RecyclerView" }

      ViewMatchers.assertThat("RecyclerView item count", view.adapter!!.itemCount, CoreMatchers.equalTo(count))
    }
  }
}
