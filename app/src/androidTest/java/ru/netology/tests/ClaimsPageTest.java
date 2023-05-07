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
import ru.netology.pages.AboutPage;
import ru.netology.pages.AuthPage;
import ru.netology.pages.ClaimPage;
import ru.netology.pages.ClaimsPage;
import ru.netology.pages.CreateClaimPage;
import ru.netology.pages.MainScreenPage;
import ru.netology.pages.NewsPage;
import ru.netology.pages.OurMissionPage;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class ClaimsPageTest {
    MainScreenPage mainScreenPage = new MainScreenPage();
    AuthPage pageAuth = new AuthPage();
    ClaimPage claimPage = new ClaimPage();
    ClaimsPage claimsPage = new ClaimsPage();
    CreateClaimPage createClaimPage = new CreateClaimPage();
    ClaimsPage.FilterClaimsWindow filterClaimsWindow = new ClaimsPage.FilterClaimsWindow();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void readyScreen() {
        SystemClock.sleep(5000);
        try {
            mainScreenPage.checkMainScreenLoaded();
            mainScreenPage.goToClaims();
        } catch (Exception e) {
            pageAuth.isAuthScreen();
            pageAuth.login();
            mainScreenPage.checkMainScreenLoaded();
            mainScreenPage.goToClaims();
        } finally {
            claimsPage.checkClaimsScreenLoaded();
        }
    }

    @Test
    @DisplayName("22. Очистить фильтр")
    public void testFilterClear() {
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.uncheckOpen();
//        filterClaimsWindow.uncheckExecuted();
//        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.uncheckInProgress();
        filterClaimsWindow.cLickOk();
        claimsPage.emptyClaimList();
    }

    @Test
    @DisplayName("23.Выбрать фильтр по статусу  Open")
    public void tesFilterOpen() {
    }

    @Test
    @DisplayName("24.Выбрать фильтр по статусу  In progress")
    public void testFilterInProgress() {

    }

    @Test
    @DisplayName("25.Выбрать фильтр по статусу Ececuted")
    public void testFilterExecuted() {

    }

    @Test
    @DisplayName("26.Выбрать фильтр по статусу  Cancelled")
    public void testFilterCancelled() {

    }

    @Test
    @DisplayName("27.Переход к созданию новой заявки")
    public void testToAllNews() {
        claimsPage.createClaimButton.check(matches(isDisplayed()));
        claimsPage.createClaimButton.perform(click());
        createClaimPage.checkCreateClaimScreenLoaded();
     }

    @Test
    @DisplayName("28.Открыть заявку")
    public void testOpenCloseUnitClaim() {
        claimsPage.openClaim(0);
        claimPage.checkClaimScreenLoaded();

    }

    @Test
    @DisplayName("29.Редактирование  заявки ( в статусе open)")
    public void testOpenClaim() {

    }

    @Test
    @DisplayName("30. Добавление нового комментария к заявке (open)")
    public void testToAllClaims() {

    }

    @Test
    @DisplayName("31. Редактирование комментария в  заявке")
    public void testAddNewClaim() {

    }

    @Test
    @DisplayName("32. Взять в работу заявку Open - Take to work")
    public void testToMain() {

    }

    @Test
    @DisplayName("33. Отклонить заявку Open - Cancel")
    public void testToClaims() {
            }
    @Test
    @DisplayName("34. Выполнить заявку In Progress - To execute")
    public void testToNews() {
            }

    @Test
    @DisplayName("35. Отказаться от заявки  (In Progress) - Throw off")
    public void testToAbout() {

    }
}
