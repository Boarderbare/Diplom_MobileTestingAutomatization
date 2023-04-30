package ru.netology.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
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

import static ru.netology.data.DataHelper.checkMessage;
import static ru.netology.data.DataHelper.waitUntilVisible;

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
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.netology.data.DataHelper;
import ru.netology.pages.MainScreenPage;
import ru.netology.pages.PageAuth;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class AuthTest {
    PageAuth pageAuth = new PageAuth();
    MainScreenPage mainScreenPage = new MainScreenPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Before
    public void readyScreen() {
            SystemClock.sleep(8000);
            try {
                pageAuth.checkLoadScreen();
                pageAuth.isAuthScreen();
            } catch (Exception e) {
                mainScreenPage.logOut();
                pageAuth.isAuthScreen();
            }
        }

    @Test
    @DisplayName("Вход в личный кабинет с валидными данными")
    public void testAuthRight() {
        pageAuth.enterLogin(DataHelper.AuthInfo.validAuth().getLogin());
        pageAuth.enterPassword(DataHelper.AuthInfo.validAuth().getPass());
        pageAuth.signIn();
        mainScreenPage.checkMainScreenLoaded();
        mainScreenPage.isMainScreen();
    }
    @Test
    @DisplayName("Вход в личный кабинет с пустым логином")
    public void testAuthEmptyLogin() {
        pageAuth.enterLogin(DataHelper.AuthInfo.emptyLogin().getLogin());
        pageAuth.enterPassword(DataHelper.AuthInfo.validAuth().getPass());
        pageAuth.signIn();
        onView(withHint(R.string.empty_login_or_password)).inRoot(isDialog()).
                check(matches(isDisplayed()));
//      waitUntilVisible(checkMessage(R.string.empty_login_or_password, true));

    }
//    @Test
//    @DisplayName("Вход в личный кабинет с пустым паролем")
//    public void testAuthEmptyPassword() {
//        pageAuth.enterLogin(DataHelper.AuthInfo.validAuth().getLogin());
//        pageAuth.enterPassword(DataHelper.AuthInfo.emptyPassword().getPass());
//        pageAuth.signIn();
//        mainScreenPage.checkMainScreenLoaded();
//        mainScreenPage.isMainScreen();
//    }
//    @Test
//    @DisplayName("Вход в личный кабинет с неверным логином")
//    public void testAuthInvalidLogin() {
//        pageAuth.enterLogin(DataHelper.AuthInfo.wrongLogin().getLogin());
//        pageAuth.enterPassword(DataHelper.AuthInfo.validAuth().getPass());
//        pageAuth.signIn();
//        mainScreenPage.checkMainScreenLoaded();
//        mainScreenPage.isMainScreen();
//    }
//    @Test
//    @DisplayName("Вход в личный кабинет с неверным паролем")
//    public void testAuthInvalidPassword() {
//        pageAuth.enterLogin(DataHelper.AuthInfo.validAuth().getLogin());
//        pageAuth.enterPassword(DataHelper.AuthInfo.wrongPassword().getPass());
//        pageAuth.signIn();
//        mainScreenPage.checkMainScreenLoaded();
//        mainScreenPage.isMainScreen();
    }

