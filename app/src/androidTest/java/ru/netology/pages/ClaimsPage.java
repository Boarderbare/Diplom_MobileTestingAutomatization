package ru.netology.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.core.IsAnything.anything;
import static org.hamcrest.core.IsInstanceOf.any;
import static org.hamcrest.core.IsNot.not;
import static ru.netology.data.DataHelper.childAtPosition;
import static ru.netology.data.DataHelper.elementWaiting;
import static ru.netology.data.DataHelper.withIndex;

import android.view.View;

import androidx.test.espresso.AmbiguousViewMatcherException;
import androidx.test.espresso.FailureHandler;
import androidx.test.espresso.NoMatchingRootException;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.base.DefaultFailureHandler;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;

public class ClaimsPage {
    public ViewInteraction claimsScreenName = onView(withText("Claims"));
    public ViewInteraction claimsFilterButton = onView(withId(R.id.filters_material_button));
    public ViewInteraction createClaimButton = onView(withId(R.id.add_new_claim_material_button));
    public int openClaimButton = R.id.claim_list_card;
    public ViewInteraction claimsList = onView(withId(R.id.claim_list_recycler_view));
    public int emptyList = R.id.empty_claim_list_image_view;
    public ViewInteraction nothingToShowWarning = onView(withText("There is nothing here yet…"));
    public ViewInteraction refreshButton = onView(withText("Refresh"));
    public ViewInteraction butterflyImageClaims = onView(withId(R.id.empty_claim_list_image_view));

    ClaimPage claimPage = new ClaimPage();

    public void checkClaimsScreenLoaded() {
        elementWaiting(withText("Claims"), 2000);
        claimsScreenName.check(matches(isDisplayed()));
        claimsFilterButton.check(matches(isDisplayed()));
        createClaimButton.check(matches(isDisplayed()));
        claimsList.check(matches(isDisplayed()));
    }

    public void openClaim(int index) {
        onView(withIndex(withId(openClaimButton), index)).perform(click());
        claimPage.checkClaimScreenLoaded();

    }

    public void emptyClaimList() {
        elementWaiting(withId(emptyList), 3000);
        nothingToShowWarning.check(matches(isDisplayed()));
        refreshButton.check(matches(isDisplayed()));
        butterflyImageClaims.check(matches(isDisplayed()));
    }


    public static class FilterClaimsWindow {
        public ViewInteraction screenName = onView(withId(R.id.claim_filter_dialog_title));
        public ViewInteraction openCheckBox = onView(allOf(withId(R.id.item_filter_open), withText("Open")));
        public ViewInteraction inProgressCheckBox = onView(allOf(withId(R.id.item_filter_in_progress), withText("In progress")));
        public ViewInteraction executedCheckBox = onView(allOf(withId(R.id.item_filter_executed), withText("Executed")));
        public ViewInteraction cancelledCheckBox = onView(allOf(withId(R.id.item_filter_cancelled), withText("Cancelled")));
        public ViewInteraction okButton = onView(allOf(withId(R.id.claim_list_filter_ok_material_button)));
        public ViewInteraction cancelButton = onView(allOf(withId(R.id.claim_filter_cancel_material_button)));


        public void checkFilterScreenLoaded() {
            elementWaiting(withText("Filtering"), 1000);
            screenName.check(matches(isDisplayed()));
            openCheckBox.check(matches(isDisplayed()));
            inProgressCheckBox.check(matches(isDisplayed()));
            executedCheckBox.check(matches(isDisplayed()));
            cancelledCheckBox.check(matches(isDisplayed()));
            okButton.check(matches(isDisplayed()));
            cancelButton.check(matches(isDisplayed()));
        }

        public void checkOpen() {
            openCheckBox.check(matches(isChecked()));
        }
        public void uncheckOpen() {
            openCheckBox.check(matches(isChecked())).perform(click());
        }
        public void checkInProgress() {
            inProgressCheckBox.check(matches(isChecked()));
        }
        public void uncheckInProgress() {
            inProgressCheckBox.check(matches(isChecked())).perform(click());
        }
        public void checkExecuted() {
            executedCheckBox.check(matches(isNotChecked())).perform(click());
        }
        public void uncheckExecuted() {
            executedCheckBox.check(matches(isNotChecked()));
        }
        public void checkCanceled() {
            cancelledCheckBox.check(matches(isNotChecked())).perform(click());
        }
        public void uncheckCanceled() {
            cancelledCheckBox.check(matches(isNotChecked()));
        }
        public void cLickOk() {
            okButton.perform(click());
            elementWaiting(withId(R.id.claim_list_recycler_view), 2000);
        }
    }
}
