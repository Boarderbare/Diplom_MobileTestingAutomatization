package ru.netology.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.netology.data.DataHelper.elementWaiting;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class CreateClaimPage {

    public ViewInteraction creatingClaimsScreenName = onView(allOf(
            withId(R.id.custom_app_bar_title_text_view), withText("Creating")));
    public ViewInteraction creatingClaimsScreenName2 = onView(allOf(
            withId(R.id.custom_app_bar_sub_title_text_view), withText("Claims")));
    public ViewInteraction titleField = onView(withId(R.id.title_edit_text));
    public ViewInteraction executorField = onView(withId(R.id.executor_drop_menu_auto_complete_text_view));
    public ViewInteraction claimDateField = onView(withId(R.id.date_in_plan_text_input_edit_text));
    public ViewInteraction claimTimeField = onView(withId(R.id.time_in_plan_text_input_edit_text));
    public ViewInteraction claimDescriptionField = onView(withId(R.id.description_edit_text));
    public ViewInteraction saveButton = onView(withId(R.id.save_button));
    public ViewInteraction cancelButton = onView(withId(R.id.cancel_button));

    public void checkCreateClaimScreenLoaded() {
        elementWaiting(withText("Creating"), 2000);
        creatingClaimsScreenName.check(matches(isDisplayed()));
        creatingClaimsScreenName2.check(matches(isDisplayed()));
        titleField.check(matches(isDisplayed()));
        claimDateField.check(matches(isDisplayed()));
        claimTimeField.check(matches(isDisplayed()));
        claimDescriptionField.check(matches(isDisplayed()));
        saveButton.check(matches(isDisplayed()));
        cancelButton.check(matches(isDisplayed()));
      }
}
