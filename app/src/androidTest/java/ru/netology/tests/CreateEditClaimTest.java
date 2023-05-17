package ru.netology.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;
import static ru.netology.data.DataHelper.elementWaiting;
import static ru.netology.data.DataHelper.nestedScrollTo;

import android.os.SystemClock;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.netology.pages.AuthPage;
import ru.netology.pages.ClaimPage;
import ru.netology.pages.ClaimsPage;
import ru.netology.pages.CreateClaimPage;
import ru.netology.pages.MainScreenPage;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class CreateEditClaimTest {
    AuthPage pageAuth = new AuthPage();
    MainScreenPage mainScreenPage = new MainScreenPage();
    ClaimsPage claimsPage = new ClaimsPage();
    ClaimPage claimPage = new ClaimPage();
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

    //ToDo перенести данные в Help
    String executor = "executor123";
    String title = "title123";
    String description = "description123";
    String date = "01.06.2023";
    String time = "12:00";
    String comment = "comment123456";
    String comment2 = "comment1234567";

    @Test
    @Ignore  //в приложении не появляется новая заявка наверху списка.
    @DisplayName("29.Создание новой заявки")
    public void testCreateClaim() {
        claimsPage.createClaimButton.perform(click());
        createClaimPage.checkCreateClaimScreenLoaded();
        createClaimPage.fillInTitle(title);
        createClaimPage.fillInDate(date);
        createClaimPage.fillInTime(time);
        createClaimPage.fillItDescription(description);
        createClaimPage.saveClaim();
        claimsPage.checkClaimsScreenLoaded();
        claimsPage.openClaim(0);
        claimPage.checkCreatedClaimElement(title, description, date, time);
    }

    @Test
    @DisplayName("30.Редактирование  заявки(title,date, time, description) в статусе open")
    public void testEditClaim() {
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.checkOpen();
        filterClaimsWindow.uncheckInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        claimPage.editClaimButton.perform(nestedScrollTo());
        claimPage.editClaimButton.perform(click());
        createClaimPage.checkEditClaimScreenLoaded();
        createClaimPage.fillInTitle(title);
        createClaimPage.fillInDate(date);
        createClaimPage.fillInTime(time);
        createClaimPage.fillItDescription(description);
        createClaimPage.saveClaim();
        claimPage.title.perform(swipeDown());
        claimPage.checkCreatedClaimElement(title, date, time, description);
    }

    @Test
    @DisplayName("31. Добавление нового комментария к заявке в статусе open")
    public void testAddNewComment() {
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.checkOpen();
        filterClaimsWindow.uncheckInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        claimPage.addCommentButton.perform(nestedScrollTo());
        claimPage.addCommentButton.perform(click());
        claimPage.checkCommentLoaded();
        claimPage.addComment(comment);
        claimPage.checkClaimScreenLoaded();
        claimPage.addCommentButton.perform(nestedScrollTo());
        claimPage.checkComment(comment);
    }

    @Test
    @DisplayName("32. Редактирование комментария в заявке в статусе open")
    public void testEditComment() {
        claimsPage.claimsFilterButton.check(matches(isDisplayed()));
        claimsPage.claimsFilterButton.perform(click());
        filterClaimsWindow.checkFilterScreenLoaded();
        filterClaimsWindow.checkOpen();
        filterClaimsWindow.uncheckInProgress();
        filterClaimsWindow.uncheckExecuted();
        filterClaimsWindow.uncheckCanceled();
        filterClaimsWindow.cLickOk();
        claimsPage.openClaim(0);
        // add comment
        claimPage.addCommentButton.perform(nestedScrollTo());
        claimPage.addCommentButton.perform(click());
        claimPage.checkCommentLoaded();
        claimPage.addComment(comment);
        claimPage.checkClaimScreenLoaded();
        // open first comment
        claimPage.openComment(0);
        claimPage.checkCommentLoaded();
        claimPage.addComment(comment2);
        claimPage.checkClaimScreenLoaded();
        claimPage.checkComment(comment2);
    }
}
