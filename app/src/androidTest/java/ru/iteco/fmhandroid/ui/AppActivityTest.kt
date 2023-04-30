package ru.iteco.fmhandroid.ui


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.iteco.fmhandroid.R

@LargeTest
@RunWith(AndroidJUnit4::class)
class AppActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(AppActivity::class.java)

    @Test
    fun appActivityTest() {
        val textView = onView(
            allOf(
                withText("Authorization"),
                withParent(withParent(withId(R.id.nav_host_fragment))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Authorization")))
    }
}
