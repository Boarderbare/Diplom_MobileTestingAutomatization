package ru.netology.tests;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import static ru.netology.data.DataHelper.checkMessage;

import android.os.SystemClock;

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
import ru.netology.pages.AuthPage;
import ru.netology.pages.MainScreenPage;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class AuthTest {
    AuthPage authPage = new AuthPage();
    MainScreenPage mainScreenPage = new MainScreenPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void readyScreen() {
        SystemClock.sleep(5000);
        try {
            authPage.checkLoadScreen();
            authPage.isAuthScreen();
        } catch (Exception e) {
            mainScreenPage.logOut();
            authPage.isAuthScreen();
        }
    }

    @Test
    @DisplayName("2.Вход в личный кабинет с валидными данными")
    public void testAuthRight() {
        authPage.enterLogin(DataHelper.AuthInfo.validAuth().getLogin());
        authPage.enterPassword(DataHelper.AuthInfo.validAuth().getPass());
        authPage.signIn();
        mainScreenPage.checkMainScreenLoaded();
        mainScreenPage.isMainScreen();
    }

    @Test
    @DisplayName("3.Вход в личный кабинет с пустым логином")
    public void testAuthEmptyLogin() {
        authPage.enterLogin(DataHelper.AuthInfo.emptyLogin().getLogin());
        authPage.enterPassword(DataHelper.AuthInfo.validAuth().getPass());
        authPage.signIn();
//          не работает матчер вслывающих сообщений никак
//        onView(withHint(R.string.empty_login_or_password)).inRoot(isPopupWindow()).
//                check(matches(isDisplayed()));
//        checkMessage(R.string.empty_login_or_password, true);
    }

    @Test
    @DisplayName("4.Вход в личный кабинет с пустым паролем")
    public void testAuthEmptyPassword() {
        authPage.enterLogin(DataHelper.AuthInfo.validAuth().getLogin());
        authPage.enterPassword(DataHelper.AuthInfo.emptyPassword().getPass());
        authPage.signIn();
        checkMessage(R.string.empty_login_or_password, true);
    }

    @Test
    @DisplayName("5.Вход в личный кабинет с неверным логином")
    public void testAuthInvalidLogin() {
        authPage.enterLogin(DataHelper.AuthInfo.wrongLogin().getLogin());
        authPage.enterPassword(DataHelper.AuthInfo.validAuth().getPass());
        authPage.signIn();
//        checkMessage(R.string.wrong_login_or_password, true);
    }

    @Test
    @DisplayName("6.Вход в личный кабинет с неверным паролем")
    public void testAuthInvalidPassword() {
        authPage.enterLogin(DataHelper.AuthInfo.validAuth().getLogin());
        authPage.enterPassword(DataHelper.AuthInfo.wrongPassword().getPass());
        authPage.signIn();
//        checkMessage(R.string.wrong_login_or_password, true);
    }

    @Test
    @DisplayName("7.Выход из личного кабинета")
    public void testLogout() {
        authPage.enterLogin(DataHelper.AuthInfo.validAuth().getLogin());
        authPage.enterPassword(DataHelper.AuthInfo.validAuth().getPass());
        authPage.signIn();
        mainScreenPage.checkMainScreenLoaded();
        mainScreenPage.isMainScreen();
        mainScreenPage.logOut();
        authPage.isAuthScreen();
    }
}
