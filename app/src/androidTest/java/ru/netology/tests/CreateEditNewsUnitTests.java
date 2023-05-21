package ru.netology.tests;

import static androidx.test.espresso.action.ViewActions.click;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import android.os.SystemClock;

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
import ru.netology.data.Resources;
import ru.netology.pages.AuthPage;
import ru.netology.pages.ControlPanelPage;
import ru.netology.pages.CreateEditNewsPage;
import ru.netology.pages.MainScreenPage;
import ru.netology.pages.NewsPage;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class CreateEditNewsUnitTests {
    AuthPage pageAuth = new AuthPage();
    MainScreenPage mainScreenPage = new MainScreenPage();
    NewsPage newsPage = new NewsPage();
    ControlPanelPage controlPanelPage = new ControlPanelPage();
    CreateEditNewsPage createEditNewsPage = new CreateEditNewsPage();
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

    String category = resources.category;
    String title = resources.title;
    String description = resources.description;
    String date = resources.date;
    String time = resources.time;
    String emptyField = resources.emptyField;

    @Test
    @DisplayName("47. Control panel. Создание новой объявления")
    @Description("Может падать, если  в списке новость не в числе первых трех")
    public void testCreateNews() {
        controlPanelPage.createNewsButton.perform(click());
        createEditNewsPage.checkCreateNewsScreenLoaded();
        createEditNewsPage.fillInFormNews(category, title, date, time, description);
        createEditNewsPage.saveNews();
        controlPanelPage.checkControlPanelScreenLoaded();
        createEditNewsPage.getEditNewsButtonByTitle(title).perform(click());
        createEditNewsPage.checkEditNewsScreenLoaded();
        createEditNewsPage.checkNewsExists(category, title, date, time, description);
        createEditNewsPage.saveNews();
        controlPanelPage.checkControlPanelScreenLoaded();
        // после проверки удаляем созданную новость
        controlPanelPage.deleteNews();
    }

    @Test
//    @Ignore
    @Description("Проверка всплывающих сообщений не рабоает")
    @DisplayName("48. Control panel: Создание новой объявления. Поля не заполнены")
    public void testCreateNewsEmptyField() {
        controlPanelPage.createNewsButton.perform(click());
        createEditNewsPage.checkCreateNewsScreenLoaded();
        createEditNewsPage.fillInFormNews(emptyField, emptyField, emptyField, emptyField, emptyField);
        createEditNewsPage.saveNews();
        DataHelper.checkMessage(R.string.empty_fields, true);
    }

    @Test
    @DisplayName("49. Control panel: Отредактировать новость")
    public void testEditTitleNews() {
        //открываем первую новость
        controlPanelPage.editNewsButton.perform(click());
        createEditNewsPage.checkEditNewsScreenLoaded();
        createEditNewsPage.fillInFormNews(category, title, date, time, description);
        createEditNewsPage.saveNews();
        controlPanelPage.checkControlPanelScreenLoaded();
        controlPanelPage.editNewsButtonAfterEdit.perform(click());
        createEditNewsPage.checkEditNewsScreenLoaded();
        createEditNewsPage.checkNewsExists(category, title, date, time, description);
        createEditNewsPage.saveNews();
        controlPanelPage.checkControlPanelScreenLoaded();
        // после проверки удаляем созданную новость
        controlPanelPage.deleteNews();
    }

    @Test
    @DisplayName("50.  Control panel: Отредактировать новость и отменить редактирование")
    public void testCancelEditNews() {
        controlPanelPage.editNewsButton.perform(click());
        createEditNewsPage.checkEditNewsScreenLoaded();
        String titleBefore = DataHelper.Text.getText(createEditNewsPage.titleField);
        createEditNewsPage.fillInFormNews(category, title, date, time, description);
        createEditNewsPage.cancelEdit();
        controlPanelPage.checkControlPanelScreenLoaded();
        controlPanelPage.editNewsButtonAfterEdit.perform(click());
        createEditNewsPage.checkEditNewsScreenLoaded();
        String titleAfter = DataHelper.Text.getText(createEditNewsPage.titleField);
        assertEquals(titleBefore, titleAfter);
    }

    @Test
    @DisplayName("51. Control panel: Отредактировать объявление. Изменит статус.")
    public void testChangeStatusNews() {
        String statusBefore = DataHelper.Text.getText(controlPanelPage.newsStatus);
        controlPanelPage.editNewsButton.perform(click());
        createEditNewsPage.checkEditNewsScreenLoaded();
        createEditNewsPage.switcher.perform(click());
        createEditNewsPage.saveNews();
        controlPanelPage.checkControlPanelScreenLoaded();
        String statusAfter = DataHelper.Text.getText(controlPanelPage.newsStatusAfterChange);
        assertNotEquals(statusBefore, statusAfter);
    }
}
