package ru.netology.tests;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.not;

import android.os.SystemClock;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.netology.pages.AuthPage;
import ru.netology.pages.MainScreenPage;
import ru.netology.pages.OurMissionPage;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class OurMissionUnitTests {
    AuthPage pageAuth = new AuthPage();
    MainScreenPage mainScreenPage = new MainScreenPage();
    OurMissionPage ourMissionPage = new OurMissionPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void readyScreen() {
        SystemClock.sleep(5000);
        try {
            mainScreenPage.checkMainScreenLoaded();
            mainScreenPage.goOurMission();
        } catch (Exception e) {
            pageAuth.isAuthScreen();
            pageAuth.login();
            mainScreenPage.checkMainScreenLoaded();
            mainScreenPage.goOurMission();
        } finally {
            ourMissionPage.checkOurMissionScreenLoaded();
        }
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
