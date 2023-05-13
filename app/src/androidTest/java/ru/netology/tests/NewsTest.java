package ru.netology.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;
import static ru.netology.data.DataHelper.elementWaiting;
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

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.netology.data.DataHelper;
import ru.netology.pages.AuthPage;
import ru.netology.pages.ClaimPage;
import ru.netology.pages.ClaimsPage;
import ru.netology.pages.ControlPanelPage;
import ru.netology.pages.CreateClaimPage;
import ru.netology.pages.FilterNewsPage;
import ru.netology.pages.MainScreenPage;
import ru.netology.pages.NewsPage;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class NewsTest {
    AuthPage pageAuth = new AuthPage();
    MainScreenPage mainScreenPage = new MainScreenPage();
    NewsPage newsPage = new NewsPage();
    ControlPanelPage controlPanelPage = new ControlPanelPage();
    FilterNewsPage filterNewsPage = new FilterNewsPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void readyScreen() {
        SystemClock.sleep(5000);
        try {
            mainScreenPage.checkMainScreenLoaded();
            mainScreenPage.goToNews();
        } catch (Exception e) {
            pageAuth.isAuthScreen();
            pageAuth.login();
            mainScreenPage.checkMainScreenLoaded();
            mainScreenPage.goToNews();
        } finally {
            newsPage.checkNewsScreenLoaded();
        }
    }
//ToDo перенести данные в Help
    String startDate= "01.05.2023";
    String endDate= "02.05.2023";
//    String description = "description123";
//    String date = "01.06.2023";
//    String time = "12:00";
//    String comment = "comment123456";
//    String comment2 = "comment1234567";

    @Test
    @DisplayName("37. News: Сортировка")
    public void testSortNews() {
        newsPage.filterNewsButton.perform(click());
        filterNewsPage.checkFilterNewsScreenLoaded();
        filterNewsPage.startDateField.perform(replaceText(startDate));
        filterNewsPage.endDateField.perform(replaceText(endDate));
        filterNewsPage.filterButton.perform(click());
        String firstNewsTitle = DataHelper.Text.getText(newsPage.titleFirstNews);
        newsPage.sortNewsButton.perform(click());
        newsPage.allNewsList.perform(swipeUp());
        newsPage.checkListNewsLoaded();
        newsPage.checkDescriptionView(firstNewsTitle);
    }

    @Test
    @DisplayName("38. News: фильтр по дате")
    public void testFilterNews() {
        newsPage.filterNewsButton.perform(click());
        filterNewsPage.checkFilterNewsScreenLoaded();
        filterNewsPage.startDateField.perform(replaceText(startDate));
        filterNewsPage.endDateField.perform(replaceText(endDate));
        filterNewsPage.filterButton.perform(click());
        String firstNewsPublicationDate = DataHelper.Text.getText(newsPage.dateNewsField);
        assertEquals(firstNewsPublicationDate, endDate);
        newsPage.sortNewsButton.perform(click());
        newsPage.allNewsList.perform(swipeDown());
        newsPage.checkListNewsLoaded();
        String secondNewsPublicationDate = DataHelper.Text.getText(newsPage.dateNewsFieldAfterSort);
        assertEquals(secondNewsPublicationDate, startDate);
    }

    @Test
    @DisplayName("39. News: Переход  на экран редактирования новостей Control Panel")
    public void testGoToEditNews() {
        newsPage.editButton.perform(click());
        controlPanelPage.checkControlPanelScreenLoaded();
    }

    @Test
    @DisplayName("40. News: Развернуть описание")
    public void testOpenOneNews() {

    }

    @Test
    @DisplayName("41. Control Panel: сортировка")
    public void testCpSortNews() {
        newsPage.goToControlPanel();
        controlPanelPage.newsFilterButton.perform(click());
        filterNewsPage.checkFilterNewsScreenLoaded();
        filterNewsPage.startDateField.perform(replaceText(startDate));
        filterNewsPage.endDateField.perform(replaceText(endDate));
        filterNewsPage.filterButton.perform(click());
        controlPanelPage.checkListNewsLoaded();
        String firstNewsTitle = DataHelper.Text.getText(controlPanelPage.newsItemTitle);
        String firstNewsDate = DataHelper.Text.getText(controlPanelPage.newsDataPublished);
        controlPanelPage.sortButton.perform(click());
        controlPanelPage.listOfNews.perform(swipeUp());
        controlPanelPage.checkListNewsLoaded();
        controlPanelPage.checkDescriptionAndDate(firstNewsTitle, firstNewsDate);
    }

    @Test
    @DisplayName("42. Control Panel: фильтр Active")
    public void testCpFilterActive() {
        newsPage.goToControlPanel();
        controlPanelPage.newsFilterButton.perform(click());
        filterNewsPage.checkFilterNewsScreenLoaded();
        filterNewsPage.checkBoxNotActive.perform(click());
        filterNewsPage.checkBoxNotActive.check(matches(isNotChecked()));
        filterNewsPage.filterButton.perform(click());
        controlPanelPage.checkListNewsLoaded();
        String firstNewsStatus = DataHelper.Text.getText(controlPanelPage.newsStatus);
        assertEquals(firstNewsStatus, "Active");
    }

    @Test
    @DisplayName("43. Control Panel: фильтр Not Active")
    public void testCpFilterNotActive() {
        newsPage.goToControlPanel();
        controlPanelPage.newsFilterButton.perform(click());
        filterNewsPage.checkFilterNewsScreenLoaded();
        filterNewsPage.checkBoxActive.perform(click());
        filterNewsPage.checkBoxActive.check(matches(isNotChecked()));
        filterNewsPage.filterButton.perform(click());
        controlPanelPage.checkListNewsLoaded();
        String firstNewsStatus = DataHelper.Text.getText(controlPanelPage.newsStatus);
        assertEquals(firstNewsStatus, "Not active");
    }

    @Test
    @DisplayName("44. Control Panel: Фильтры Not Active и Active сняты")
    public void testCpFiltersEmty() {
        newsPage.goToControlPanel();
        controlPanelPage.newsFilterButton.perform(click());
        filterNewsPage.checkFilterNewsScreenLoaded();
        filterNewsPage.checkBoxActive.perform(click());
        filterNewsPage.checkBoxActive.check(matches(isNotChecked()));
        filterNewsPage.checkBoxNotActive.perform(click());
        filterNewsPage.checkBoxNotActive.check(matches(isNotChecked()));
        filterNewsPage.filterButton.perform(click());
        controlPanelPage.checkListNewsLoaded();
        controlPanelPage.butterflyImageNews.check(matches(isDisplayed()));
        onView(withText("There is nothing here yet…"));
        onView(withText("Refresh")).check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("45. Control Panel:Развернуть описание")
    public void testCpOpenOneNews() {

    }
    @Test
    @DisplayName("46. Control Panel: Создание новой объявления")
    public void testCpCreateNews() {

    }
}