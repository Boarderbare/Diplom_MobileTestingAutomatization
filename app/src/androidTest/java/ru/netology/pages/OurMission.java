package ru.netology.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.netology.data.DataHelper.childAtPosition;
import static ru.netology.data.DataHelper.elementWaiting;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class OurMission {

    public ViewInteraction screenName = onView((withId(R.id.our_mission_title_text_view)));
    public ViewInteraction listOfItems = onView(withId(R.id.our_mission_item_list_recycler_view));

    public void checkOurMissionScreenLoaded() {
            elementWaiting(withText("Love is all"), 5000);
        }

        public  void isOurMissiionScreen() {
            screenName.check(matches(isDisplayed()));
            listOfItems.check(matches(isDisplayed()));
        }

    public void signIn() {

    }

}
