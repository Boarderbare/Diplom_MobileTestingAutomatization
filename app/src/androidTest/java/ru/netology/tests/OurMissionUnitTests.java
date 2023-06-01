package ru.netology.tests;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.not;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.netology.data.ReadyScreen;
import ru.netology.pages.OurMissionPage;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class OurMissionUnitTests {
    OurMissionPage ourMissionPage = new OurMissionPage();
    ReadyScreen readyScreen = new ReadyScreen();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void readyScreen() {
        readyScreen.readyAboutScreen();
    }

    @Test
    @DisplayName("52. OurMission: Развернуть сернуть описание")
    public void testOpenTopic() {
        int numberTopic = 1;
        ourMissionPage.missionList.check(matches(isDisplayed()));
        ourMissionPage.missionList.perform(actionOnItemAtPosition(numberTopic, click()));
        ourMissionPage.secondTopic.check(matches(isDisplayed()));
        ourMissionPage.missionList.perform(actionOnItemAtPosition(numberTopic, click()));
        ourMissionPage.checkOurMissionScreenLoaded();
        ourMissionPage.secondTopic.check(matches(not(isDisplayed())));
    }
}
