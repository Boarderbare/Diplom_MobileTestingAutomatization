package ru.netology.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import android.os.SystemClock;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.netology.data.DataHelper;
import ru.netology.data.Resources;
import ru.netology.pages.AuthPage;
import ru.netology.pages.ControlPanelPage;
import ru.netology.pages.FilterNewsPage;
import ru.netology.pages.MainScreenPage;
import ru.netology.pages.NewsPage;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class NewsUnitTests {
    AuthPage pageAuth = new AuthPage();
    MainScreenPage mainScreenPage = new MainScreenPage();
    NewsPage newsPage = new NewsPage();
    ControlPanelPage controlPanelPage = new ControlPanelPage();
    FilterNewsPage filterNewsPage = new FilterNewsPage();
    Resources resources = new Resources();

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

    String startDate = resources.startDate;
    String endDate = resources.endDate;

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
        controlPanelPage.chooseFirstNews();
        controlPanelPage.newsDescription.check(matches(isDisplayed()));
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
    @Description("Падает. В приложении не работает пустой фильтр")
    public void testCpFiltersEmpty() {
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
    @DisplayName("45. Control Panel: Развернуть описание")
    public void testCpOpenOneNews() {
        newsPage.goToControlPanel();
        controlPanelPage.chooseFirstNews();
        controlPanelPage.newsDescription.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("46. Control Panel: Удаление новости")
    public void testCpDeleteNews() {
        newsPage.goToControlPanel();
        String title = DataHelper.Text.getText(controlPanelPage.newsItemTitle);
        controlPanelPage.deleteNewsButton.perform(click());
        controlPanelPage.cancelButton.check(matches(isDisplayed()));
        controlPanelPage.okButton.check(matches(isDisplayed()));
        controlPanelPage.okButton.perform(click());
        controlPanelPage.checkListNewsLoaded();
        String title2 = DataHelper.Text.getText(controlPanelPage.newsItemTitleAfterDelete);
        assertNotEquals(title, title2);
    }
}
