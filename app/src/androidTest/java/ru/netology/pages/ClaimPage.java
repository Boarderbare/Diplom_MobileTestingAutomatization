package ru.netology.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.netology.data.DataHelper.childAtPosition;
import static ru.netology.data.DataHelper.elementWaiting;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.netology.data.DataHelper;

public class ClaimPage {

    public ViewInteraction titleLabel = onView(withId(R.id.title_label_text_view));
    public ViewInteraction title = onView(withId(R.id.title_text_view));
    public ViewInteraction executorLabel = onView(withId(R.id.executor_name_label_text_view));
    public ViewInteraction executorName = onView(withId(R.id.executor_name_text_view));
    public ViewInteraction dateLabel = onView(withId(R.id.plane_date_label_text_view));
    public ViewInteraction claimDate = onView(withId(R.id.plane_date_text_view));
    public ViewInteraction claimTime = onView(withId(R.id.plan_time_text_view));
    public ViewInteraction status = onView(withId(R.id.status_label_text_view));
    public ViewInteraction statusImg = onView(withId(R.id.status_icon_image_view));
//    public ViewInteraction description = onView(withId(R.id.description_material_card_view));
    public ViewInteraction descriptionText = onView(withId(R.id.description_text_view));
    public ViewInteraction authorLabel = onView(withId(R.id.author_label_text_view));
    public ViewInteraction authorName = onView(withId(R.id.author_name_text_view));
    public ViewInteraction createdLabel = onView(withId(R.id.create_data_label_text_view));
    public ViewInteraction dateCreated = onView(withId(R.id.create_data_text_view));
    public ViewInteraction timeCreated = onView(withId(R.id.create_time_text_view));
//    public ViewInteraction addCommentButton = onView(withId(R.id.status_label_text_view));
    public ViewInteraction editClaimButton = onView(withId(R.id.edit_processing_image_button));
    public ViewInteraction statusChangeButton = onView(withId(R.id.status_processing_image_button));
    public ViewInteraction exitClaimButton = onView(withId(R.id.close_image_button));
    public ViewInteraction addCommentButton = onView(withId(R.id.add_comment_image_button));
    public ViewInteraction takeToWork = onView(withText("take to work"));
    public ViewInteraction cancelClaim = onView(withText("Cancel"));
    public ViewInteraction throwOffClaim = onView(withText("Throw off"));
    public ViewInteraction toExecuteClaim = onView(withText("To execute"));
    public ViewInteraction editTextComment = onView(withId(R.id.editText));;
    public ViewInteraction editTextCommentOkButton = onView(withText("OK"));

    public void checkClaimStatusLoaded() {
        elementWaiting(withId(R.id.status_label_text_view), 2000);
        status.check(matches(isDisplayed()));
    }

    public void checkCommentFieldLoaded() {
        elementWaiting(withId(R.id.editText), 2000);
          }

    public void checkClaimScreenLoaded() {
        elementWaiting(withText("Title"), 2000);
        titleLabel.check(matches(isDisplayed()));
        title.check(matches(isDisplayed()));
        executorLabel.check(matches(isDisplayed()));
        executorName.check(matches(isDisplayed()));
        dateLabel.check(matches(isDisplayed()));
        claimDate.check(matches(isDisplayed()));
        claimTime.check(matches(isDisplayed()));
        status.check(matches(isDisplayed()));
        descriptionText.check(matches(isDisplayed()));
        authorLabel.check(matches(isDisplayed()));
        authorName.check(matches(isDisplayed()));
        createdLabel.check(matches(isDisplayed()));
        dateCreated.check(matches(isDisplayed()));
        timeCreated.check(matches(isDisplayed()));
        addCommentButton.check(matches(isDisplayed()));
//      not displayed with long claims
//        editingClaimButton.check(matches(isDisplayed()));
//        statusChangeButton.check(matches(isDisplayed()));
//        exitClaimButton.check(matches(isDisplayed()));
//        exitClaimButton.check(matches(isDisplayed()));
    }

    public String getStatus(){
        String claimStatus = DataHelper.Text.getText(onView(withId(R.id.status_label_text_view)));
        return claimStatus;
    }

    public void addCommentWhenStatusChange(String comment) {
        editTextComment.perform(replaceText(comment));
        editTextCommentOkButton.perform(click());
    }
}
