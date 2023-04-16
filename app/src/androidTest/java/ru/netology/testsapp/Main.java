package ru.netology.testsapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class Main {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Test
    public void testAuth() throws InterruptedException {
        Thread.sleep(5000);
        ViewInteraction textInputEditText = onView(withId(R.id.login_text_input_layout));
        textInputEditText.check(matches(isCompletelyDisplayed()));
        textInputEditText.perform(click());
        textInputEditText.perform(ViewActions.typeText("login2"), closeSoftKeyboard());
        Thread.sleep(10000);
        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.password_text_input_layout),isDisplayed()));
        textInputEditText2.perform(replaceText("password2"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.enter_button), withText("Sign in"), withContentDescription("Save"),
                  withClassName(is("android.widget.RelativeLayout")),
                        isDisplayed()));
        materialButton.perform(click());

    }
}
