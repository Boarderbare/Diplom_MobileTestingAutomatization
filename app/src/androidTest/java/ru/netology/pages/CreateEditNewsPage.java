package ru.netology.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.netology.data.DataHelper.elementWaiting;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class CreateEditNewsPage {

    public ViewInteraction creatingNewsScreenName = onView(withText("Creating"));
    public ViewInteraction editingNewsScreenName = onView(withText("Editing"));
    public ViewInteraction newsScreenName = onView(withText("News"));
    public ViewInteraction categoryField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public ViewInteraction titleField = onView(withId(R.id.news_item_title_text_input_edit_text));
    public ViewInteraction publicationDateField = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    public ViewInteraction timeField = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    public ViewInteraction descriptionField = onView(withId(R.id.news_item_description_text_input_edit_text));
    public ViewInteraction saveButton = onView(withId(R.id.save_button));
    public ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    public ViewInteraction okButton = onView(withText("OK"));
    public ViewInteraction switcher = onView(withId(R.id.switcher));

    public ViewInteraction getEditNewsButtonByTitle(String title) {
        return onView(allOf(withId(R.id.edit_news_item_image_view),
                withParent(withParent(allOf(withId(R.id.news_item_material_card_view),
                        withChild(withChild(withText(title))))))));
    }

    ControlPanelPage controlPanelPage = new ControlPanelPage();

    public void checkCreateNewsScreenLoaded() {
        elementWaiting(withText("Creating"), 5000);
        creatingNewsScreenName.check(matches(isDisplayed()));
        newsScreenName.check(matches(isDisplayed()));
        categoryField.check(matches(isDisplayed()));
        titleField.check(matches(isDisplayed()));
        publicationDateField.check(matches(isDisplayed()));
        timeField.check(matches(isDisplayed()));
        descriptionField.check(matches(isDisplayed()));
        saveButton.check(matches(isDisplayed()));
        cancelButton.check(matches(isDisplayed()));
    }

    public void checkEditNewsScreenLoaded() {
        elementWaiting(withText("Editing"), 5000);
        editingNewsScreenName.check(matches(isDisplayed()));
        newsScreenName.check(matches(isDisplayed()));
        categoryField.check(matches(isDisplayed()));
        titleField.check(matches(isDisplayed()));
        publicationDateField.check(matches(isDisplayed()));
        timeField.check(matches(isDisplayed()));
        descriptionField.check(matches(isDisplayed()));
        switcher.check(matches(isDisplayed()));
    }

    public void checkNewsExists(String category, String title, String date, String time, String description) {
        titleField.check(matches(withText(title)));
        publicationDateField.check(matches(withText(date)));
        timeField.check(matches(withText(time)));
        descriptionField.check(matches(withText(description)));
    }

    public void saveNews() {
        saveButton.perform(click());
    }

    public void cancelEdit() {
        cancelButton.perform(click());
        okButton.perform(click());
    }

    public void fillInFormNews(String category, String title, String date, String time, String description) {
        // не удалось найти слектор в дропдауне категорий поэтому категория вручную заполняется.
        categoryField.perform(replaceText(category));
        titleField.perform(replaceText(title));
        publicationDateField.perform(replaceText(date));
        timeField.perform(replaceText(time));
        descriptionField.perform(replaceText(description));
    }
}
