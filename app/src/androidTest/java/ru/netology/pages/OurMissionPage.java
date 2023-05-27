package ru.netology.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.Is.is;
import static ru.netology.data.DataHelper.childAtPosition;
import static ru.netology.data.DataHelper.elementWaiting;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class OurMissionPage {

    public ViewInteraction screenName = onView((withId(R.id.our_mission_title_text_view)));
    public ViewInteraction listOfItems = onView(withId(R.id.our_mission_item_list_recycler_view));
    public ViewInteraction missionList = onView(allOf(withId(R.id.our_mission_item_list_recycler_view),
            childAtPosition(withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                    0)));
    public ViewInteraction secondTopic = onView(allOf(
            withId(R.id.our_mission_item_description_text_view),
            withText("Нет шаблона и стандарта, есть только дух, " +
                    "который живет в разных домах по-разному. Но всегда он добрый, любящий и помогающий.")));

    public void checkOurMissionScreenLoaded() {
        Allure.step("Проверка загрузки экрана \"Наша миссия\"");
        elementWaiting(withText("Love is all"), 5000);
    }

    public void isOurMissionScreen() {
        Allure.step("Проверка загрузки элементов экрана \"Наша миссия\"");
        screenName.check(matches(isDisplayed()));
        listOfItems.check(matches(isDisplayed()));
    }

}

