package ru.netology.pages;

import static androidx.test.espresso.Espresso.onView;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.netology.data.DataHelper.elementWaiting;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class FilterNewsPage {

    ControlPanelPage controlPanelPage = new ControlPanelPage();

    public ViewInteraction filterScreenName = onView(withId(R.id.filter_news_title_text_view));
    public ViewInteraction categoryField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public ViewInteraction categoryFieldButton = onView(withId(R.id.text_input_end_icon));
    public ViewInteraction startDateField = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    public ViewInteraction endDateField = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    public ViewInteraction filterButton = onView(withId(R.id.filter_button));
    public ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    public ViewInteraction checkBoxActive = onView(withId(R.id.filter_news_active_material_check_box));
    public ViewInteraction checkBoxNotActive = onView(withId(R.id.filter_news_inactive_material_check_box));

    public void checkFilterNewsScreenLoaded() {
        elementWaiting(withText("Filter news"), 2000);
        categoryField.check(matches(isDisplayed()));
        startDateField.check(matches(isDisplayed()));
        endDateField.check(matches(isDisplayed()));
        filterButton.check(matches(isDisplayed()));
        cancelButton.check(matches(isDisplayed()));
    }
}
