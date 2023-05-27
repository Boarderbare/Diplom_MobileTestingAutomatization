package ru.netology.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.netology.data.DataHelper.elementWaiting;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class CreateClaimPage {

    public ViewInteraction creatingClaimsScreenName = onView(allOf(
            withId(R.id.custom_app_bar_title_text_view), withText("Creating")));
    public ViewInteraction editingClaimsScreenName = onView(allOf(
            withId(R.id.custom_app_bar_title_text_view), withText("Editing")));
    public ViewInteraction creatingClaimsScreenName2 = onView(allOf(
            withId(R.id.custom_app_bar_sub_title_text_view), withText("Claims")));
    public ViewInteraction titleField = onView(withId(R.id.title_edit_text));
    public ViewInteraction executorField = onView(withId(R.id.executor_drop_menu_auto_complete_text_view));
    public ViewInteraction claimDateField = onView(withId(R.id.date_in_plan_text_input_edit_text));
    public ViewInteraction claimTimeField = onView(withId(R.id.time_in_plan_text_input_edit_text));
    public ViewInteraction claimDescriptionField = onView(withId(R.id.description_edit_text));
    public ViewInteraction saveButton = onView(withId(R.id.save_button));
    public ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    ClaimPage claimPage = new ClaimPage();

    public void checkCreateClaimScreenLoaded() {
        Allure.step("Проверка загрузки экарана создания заявки");
        elementWaiting(withText("Creating"), 2000);
        creatingClaimsScreenName.check(matches(isDisplayed()));
        creatingClaimsScreenName2.check(matches(isDisplayed()));
        titleField.check(matches(isDisplayed()));
        executorField.check(matches(isDisplayed()));
        claimDateField.check(matches(isDisplayed()));
        claimTimeField.check(matches(isDisplayed()));
        claimDescriptionField.check(matches(isDisplayed()));
        saveButton.check(matches(isDisplayed()));
        cancelButton.check(matches(isDisplayed()));
    }

    public void checkEditClaimScreenLoaded() {
        Allure.step("Проверка загрузки экарана редактирования заявки");
        elementWaiting(withText("Editing"), 2000);
        editingClaimsScreenName.check(matches(isDisplayed()));
        creatingClaimsScreenName2.check(matches(isDisplayed()));
        titleField.check(matches(isDisplayed()));
        executorField.check(matches(isDisplayed()));
        claimDateField.check(matches(isDisplayed()));
        claimTimeField.check(matches(isDisplayed()));
        claimDescriptionField.check(matches(isDisplayed()));
        saveButton.check(matches(isDisplayed()));
        cancelButton.check(matches(isDisplayed()));
    }

    public void fillInTitle(String title) {
        Allure.step("Заполнение поля title");
        titleField.perform(replaceText(title));
    }

    public void fillInExecutor(String executor) {
        Allure.step("Заполнение поля executor");
        executorField.perform(replaceText(executor));
    }

    public void fillInDate(String date) {
        Allure.step("Заполнение поля date");
        claimDateField.perform(replaceText(date));
    }

    public void fillInTime(String time) {
        Allure.step("Заполнение поля time");
        claimTimeField.perform(replaceText(time));
    }

    public void fillItDescription(String description) {
        Allure.step("Заполнение поля description");
        claimDescriptionField.perform(replaceText(description));
    }

    public void saveClaim() {
        Allure.step("Сохранить заявку");
        saveButton.perform(click());
    }
}
