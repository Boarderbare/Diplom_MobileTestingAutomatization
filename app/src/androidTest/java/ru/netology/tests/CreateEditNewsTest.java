package ru.netology.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.repeatedlyUntil;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.eventFrom;
import static ru.netology.data.DataHelper.nestedScrollTo;
import static ru.netology.data.DataHelper.withIndex;

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

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.netology.data.DataHelper;
import ru.netology.pages.AuthPage;
import ru.netology.pages.ClaimPage;
import ru.netology.pages.ClaimsPage;
import ru.netology.pages.ControlPanelPage;
import ru.netology.pages.CreateClaimPage;
import ru.netology.pages.CreateEditNewsPage;
import ru.netology.pages.MainScreenPage;
import ru.netology.pages.NewsPage;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class CreateEditNewsTest {
    AuthPage pageAuth = new AuthPage();
    MainScreenPage mainScreenPage = new MainScreenPage();
    NewsPage newsPage = new NewsPage();
    ControlPanelPage controlPanelPage = new ControlPanelPage();
    CreateEditNewsPage createEditNewsPage = new CreateEditNewsPage();
    DataHelper dataHelper= new DataHelper();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void readyScreen() {
        SystemClock.sleep(5000);
        try {
            mainScreenPage.checkMainScreenLoaded();
            mainScreenPage.goToNews();
            newsPage.goToControlPanel();
        } catch (Exception e) {
            pageAuth.isAuthScreen();
            pageAuth.login();
            mainScreenPage.goToNews();
            newsPage.goToControlPanel();
        } finally {
            controlPanelPage.checkControlPanelScreenLoaded();
        }
    }

    //ToDo перенести данные в Help
    String category = "Зарплата";
    String title = "title12345";
    String description = "description1235";
    String date = "15.06.2023";
    String time = "15:00";
    String emptyField ="";

    @Test
    @DisplayName("47. Control panel. Создание новой объявления")
    @Description ("Может падать, если  в списке новость не в числе первых трех")
    public void testCreateNews() {
        controlPanelPage.createNewsButton.perform(click());
        createEditNewsPage.checkCreateNewsScreenLoaded();
        createEditNewsPage.fillInFormNews(category,title, date,time, description);
        controlPanelPage.checkControlPanelScreenLoaded();
        createEditNewsPage.getEditNewsButtonByTitle(title).perform(click());
        createEditNewsPage.checkEditNewsScreenLoaded();
        createEditNewsPage.checkNewsExists(category,title, date,time, description);
        //удаляем созданную новость
        createEditNewsPage.saveNews();
        controlPanelPage.deleteNewsButton.perform(click());
    }
    @Test
    @Ignore
    @Description("Проверка всплывающих сообщений не рабоает")
    @DisplayName("48. Control panel: Создание новой объявления. Поля не заполнены")
    public void testCreateNewsEmptyField() {
        controlPanelPage.createNewsButton.perform(click());
        createEditNewsPage.checkCreateNewsScreenLoaded();
        createEditNewsPage.fillInFormNews(emptyField,emptyField, emptyField,emptyField, emptyField);
        DataHelper.checkMessage(R.string.empty_fields, true);
    }

    @Test
    @DisplayName("49. Control panel: Отредактировать в объявлении Title")
    public void testEditTitleNews() {
        //открываем первую новость
        controlPanelPage.editNewsButton.perform(click());

    }

    @Test
    @DisplayName("50.  Control panel: Отредактировать в объявлении Date")
    public void testCpEditDateNews() {
    }

    @Test
    @DisplayName("51. Control panel: Отредактировать в объявлении Time")
    public void testCpEditTimeNews() {
    }

    @Test
    @DisplayName("52. Control panel: Отредактировать в объявлении Description")
    public void testCpEditDescriptionNews() {
    }

    @Test
    @DisplayName("53. Отредактировать объявление. Изменит статус.")
    public void testCpChangeStatusNews() {
    }

}
