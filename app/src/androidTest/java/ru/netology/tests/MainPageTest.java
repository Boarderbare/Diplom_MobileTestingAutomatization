package ru.netology.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static ru.netology.data.DataHelper.checkMessage;

import android.os.SystemClock;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.netology.pages.AboutPage;
import ru.netology.pages.ClaimPage;
import ru.netology.pages.ClaimsPage;
import ru.netology.pages.CreateClaimPage;
import ru.netology.pages.MainScreenPage;
import ru.netology.pages.NewsPage;
import ru.netology.pages.OurMissionPage;
import ru.netology.pages.AuthPage;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class MainPageTest {
    OurMissionPage ourMission = new OurMissionPage();
    MainScreenPage mainScreenPage = new MainScreenPage();
    AuthPage pageAuth = new AuthPage();
    NewsPage newsPage = new NewsPage();
    ClaimPage claimPage = new ClaimPage();
    ClaimsPage claimsPage = new ClaimsPage();
    CreateClaimPage createClaimPage = new CreateClaimPage();
    AboutPage aboutPage = new AboutPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void readyScreen() {
        SystemClock.sleep(5000);
        try {
            mainScreenPage.checkMainScreenLoaded();
        } catch (Exception e) {
            pageAuth.isAuthScreen();
            pageAuth.login();
        } finally {
            mainScreenPage.checkMainScreenLoaded();
        }
    }

    @Test
    @DisplayName("8.Отображение и открытие раздела \"Our Mission'")
    public void testOpenOurMission() {
        mainScreenPage.ourMissionButton.check(matches(isDisplayed()));
        mainScreenPage.ourMissionButton.perform(click());
        ourMission.checkOurMissionScreenLoaded();
        ourMission.isOurMissionScreen();
    }

    @Test
    @DisplayName("9.Отображение и открытие \"Profile'")
    public void testOpenProfile() {
        mainScreenPage.logOutButton.check(matches(isDisplayed()));
        mainScreenPage.logOutButton.perform(click());
        mainScreenPage.logOut.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("10.Отображение и открытие Menu")
    public void testOpenMenu() {
        mainScreenPage.menuButton.check(matches(isDisplayed()));
        mainScreenPage.menuButton.perform(click());
        mainScreenPage.menuMain.check(matches(isDisplayed()));
        mainScreenPage.menuNews.check(matches(isDisplayed()));
        mainScreenPage.menuClaims.check(matches(isDisplayed()));
        mainScreenPage.menuAbout.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("11.News: свернуть, развернуть вкладку")
    public void testOpenClseUnitNews() {
        mainScreenPage.unitNewsButton.check(matches(isDisplayed()));
        mainScreenPage.unitNewsButton.perform(click());
        mainScreenPage.allNewsButton.check(matches(not(isDisplayed())));
        mainScreenPage.unitNewsButton.perform(click());
        mainScreenPage.allNewsButton.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("12.News: развернуть новость")
    public void testOpenNews() {
        mainScreenPage.singleNewsButton.check(matches(isDisplayed()));
        mainScreenPage.singleNewsButton.perform(actionOnItemAtPosition(0, click()));
        mainScreenPage.descriptionNewsIsDisplayed(0);
//        та же кнопка на сернуть не работает
//        mainScreenPage.singleNewsButton.perform(actionOnItemAtPosition(0, click()));
//        mainScreenPage.descriptionNewsIsNotDisplayed(0);
    }

    @Test
    @DisplayName("13.News: переход в раздел News")
    public void testToAllNews() {
        mainScreenPage.allNewsButton.check(matches(isDisplayed()));
        mainScreenPage.allNewsButton.perform(click());
        newsPage.checkNewsScreenLoaded();
    }

    @Test
    @DisplayName("14.Claims: свернуть/развернуть список")
    public void testOpenCloseUnitClaim() {
        mainScreenPage.unitClaimsButton.check(matches(isDisplayed()));
        mainScreenPage.unitClaimsButton.perform(click());
        mainScreenPage.allClaimsButton.check(matches(not(isDisplayed())));
        mainScreenPage.unitClaimsButton.perform(click());
        mainScreenPage.allClaimsButton.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("15.Claims: развернуть отзыв")
    public void testOpenClaim() {
        mainScreenPage.singleClaim.check(matches(isDisplayed()));
        mainScreenPage.singleClaim.perform(actionOnItemAtPosition(0, click()));
        claimPage.checkClaimScreenLoaded();
    }

    @Test
    @DisplayName("16.Claims: переход в All claims")
    public void testToAllClaims() {
        mainScreenPage.allClaimsButton.check(matches(isDisplayed()));
        mainScreenPage.allClaimsButton.perform(click());
        claimsPage.checkClaimsScreenLoaded();
    }

    @Test
    @DisplayName("17.Claims: добавить новый отзыв")
    public void testAddNewClaim() {
        mainScreenPage.createClaimButton.check(matches(isDisplayed()));
        mainScreenPage.createClaimButton.perform(click());
        createClaimPage.checkCreateClaimScreenLoaded();
    }

    @Test
    @DisplayName("18.Menu: перейти в раздел Main")
    public void testToMain() {
        mainScreenPage.menuButton.check(matches(isDisplayed()));
        mainScreenPage.menuButton.perform(click());
        mainScreenPage.menuClaims.check(matches(isDisplayed()));
        mainScreenPage.menuClaims.perform(click());
        claimsPage.checkClaimsScreenLoaded();
        mainScreenPage.menuButton.check(matches(isDisplayed()));
        mainScreenPage.menuButton.perform(click());
        mainScreenPage.menuMain.check(matches(isDisplayed()));
        mainScreenPage.menuMain.perform(click());
        mainScreenPage.checkMainScreenLoaded();
    }

    @Test
    @DisplayName("19.Menu: перейти в раздел Claims")
    public void testToClaims() {
        mainScreenPage.goToClaims();
        claimsPage.checkClaimsScreenLoaded();
    }

    @Test
    @DisplayName("20.Menu: перейти в раздел News")
    public void testToNews() {
        mainScreenPage.goToNews();
        newsPage.checkNewsScreenLoaded();
    }

    @Test
    @DisplayName("21.Menu: перейти в раздел About")
    public void testToAbout() {
        mainScreenPage.menuButton.check(matches(isDisplayed()));
        mainScreenPage.menuButton.perform(click());
        mainScreenPage.menuAbout.check(matches(isDisplayed()));
        mainScreenPage.menuAbout.perform(click());
        aboutPage.checkAboutScreenLoaded();
    }
}
