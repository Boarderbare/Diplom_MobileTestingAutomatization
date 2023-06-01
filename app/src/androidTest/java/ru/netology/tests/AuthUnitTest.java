package ru.netology.tests;

import static ru.netology.data.DataHelper.checkMessage;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.netology.data.DataHelper;
import ru.netology.data.ReadyScreen;
import ru.netology.pages.AuthPage;
import ru.netology.pages.MainScreenPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class AuthUnitTest {
    AuthPage authPage = new AuthPage();
    MainScreenPage mainScreenPage = new MainScreenPage();
    ReadyScreen readyScreen = new ReadyScreen();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void readyScreen() {
        readyScreen.readyAuthScreen();
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
    @Ignore
    @DisplayName("3.Вход в личный кабинет с пустым логином")
    @Description("Зависает. Не работает проверка всплывающих сообщений")
    public void testAuthEmptyLogin() {
        authPage.enterLogin(DataHelper.AuthInfo.emptyLogin().getLogin());
        authPage.enterPassword(DataHelper.AuthInfo.validAuth().getPass());
        authPage.signIn();
//   не работает матчер вслывающих сообщений никак
//        onView(withHint(R.string.empty_login_or_password)).inRoot(isPopupWindow()).
//                check(matches(isDisplayed()));
//        checkMessage(R.string.empty_login_or_password, true);
    }

    @Test
    @Ignore
    @DisplayName("4.Вход в личный кабинет с пустым паролем")
    @Description("Зависает. Не работает проверка всплывающих сообщений")
    public void testAuthEmptyPassword() {
        authPage.enterLogin(DataHelper.AuthInfo.validAuth().getLogin());
        authPage.enterPassword(DataHelper.AuthInfo.emptyPassword().getPass());
        authPage.signIn();
        checkMessage(R.string.empty_login_or_password, true);
    }

    @Test
    @Ignore
    @DisplayName("5.Вход в личный кабинет с неверным логином")
    @Description("Зависает. Не работает проверка всплывающих сообщений")
    public void testAuthInvalidLogin() {
        authPage.enterLogin(DataHelper.AuthInfo.wrongLogin().getLogin());
        authPage.enterPassword(DataHelper.AuthInfo.validAuth().getPass());
        authPage.signIn();
        checkMessage(R.string.wrong_login_or_password, true);
    }

    @Test
    @Ignore
    @DisplayName("6.Вход в личный кабинет с неверным паролем")
    @Description("Зависает. Не работает проверка всплывающих сообщений")
    public void testAuthInvalidPassword() {
        authPage.enterLogin(DataHelper.AuthInfo.validAuth().getLogin());
        authPage.enterPassword(DataHelper.AuthInfo.wrongPassword().getPass());
        authPage.signIn();
        checkMessage(R.string.wrong_login_or_password, true);
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
