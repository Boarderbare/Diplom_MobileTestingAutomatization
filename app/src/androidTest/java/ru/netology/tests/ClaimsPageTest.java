package ru.netology.tests;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static ru.netology.data.DataHelper.nestedScrollTo;

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
        filterClaimsWindow.uncheckInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.emptyClaimList();
    }

    @Test
    @DisplayName("23.Выбрать фильтр по статусу  Open")
    public void tesFilterOpen() {
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.checkOpen();
        filterClaimsWindow.uncheckInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        claimPage.checkClaimScreenLoaded();
        assertEquals("Open",claimPage.getStatus());
    }

    @Test
    @DisplayName("24.Выбрать фильтр по статусу  In progress")
    public void testFilterInProgress() {
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.uncheckOpen();
        filterClaimsWindow.checkInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        claimPage.checkClaimScreenLoaded();
        assertEquals("In progress",claimPage.getStatus());
    }

    @Test
    @DisplayName("25.Выбрать фильтр по статусу Ececuted")
    public void testFilterExecuted() {
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.uncheckOpen();
        filterClaimsWindow.uncheckInProgress();
        filterClaimsWindow.checkExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        claimPage.checkClaimScreenLoaded();
        assertEquals("Executed",claimPage.getStatus());
    }

    @Test
    @DisplayName("26.Выбрать фильтр по статусу  Cancelled")
    public void testFilterCancelled() {
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.uncheckOpen();
        filterClaimsWindow.uncheckInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.checkCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        claimPage.checkClaimScreenLoaded();
        assertEquals("Canceled",claimPage.getStatus());
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
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.checkOpen();
        filterClaimsWindow.uncheckInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        claimPage.statusChangeButton.perform(click());
        claimPage.takeToWork.perform(click());
        claimPage.checkClaimStatusLoaded();
        assertEquals("In progress",claimPage.getStatus());
    }

    @Test
    @DisplayName("33. Отклонить заявку Open - Cancel")
    public void testToClaims() {
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.checkOpen();
        filterClaimsWindow.uncheckInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        claimPage.statusChangeButton.perform(click());
        claimPage.cancelClaim.perform(click());
        claimPage.checkClaimStatusLoaded();
        assertEquals("Canceled",claimPage.getStatus());
    }
    @Test
    @DisplayName("34. Выполнить заявку In Progress - To execute")
    public void testToNews() {
        String comment = "Done";
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.uncheckOpen();
        filterClaimsWindow.checkInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        claimPage.statusChangeButton.perform(nestedScrollTo());
        claimPage.statusChangeButton.perform(click());
        claimPage.toExecuteClaim.perform(click());
        claimPage.checkCommentFieldLoaded();
        claimPage.addCommentWhenStatusChange(comment);
        assertEquals("Executed",claimPage.getStatus());
    }

    @Test
    @DisplayName("35. Отказаться от заявки  (In Progress) - Throw off")
    public void testToAbout() {
        String comment = "Done";
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.uncheckOpen();
        filterClaimsWindow.checkInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        claimPage.statusChangeButton.perform(nestedScrollTo());
        claimPage.statusChangeButton.perform(click());
        claimPage.throwOffClaim.perform(click());
        claimPage.checkCommentFieldLoaded();
        claimPage.addCommentWhenStatusChange(comment);
        assertEquals("Open",claimPage.getStatus());
    }
}
