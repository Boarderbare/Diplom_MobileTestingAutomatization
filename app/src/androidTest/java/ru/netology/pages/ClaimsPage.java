package ru.netology.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.netology.data.DataHelper.elementWaiting;
import static ru.netology.data.DataHelper.withIndex;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
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
        Allure.step("Проверка загрузки экрана с заявками");
        elementWaiting(withText("Claims"), 5000);
        claimsScreenName.check(matches(isDisplayed()));
        claimsFilterButton.check(matches(isDisplayed()));
        createClaimButton.check(matches(isDisplayed()));
        claimsList.check(matches(isDisplayed()));
    }

    public void openClaim(int index) {
        Allure.step("Открыть заявку");
        onView(withIndex(withId(openClaimButton), index)).perform(click());
        claimPage.checkClaimScreenLoaded();
    }

    public void emptyClaimList() {
        Allure.step("Проверка отображения пустого списка");
        elementWaiting(withId(emptyList), 5000);
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
            Allure.step("Проверка отображения загрузки экрана с фильтрами");
            elementWaiting(withText("Filtering"), 5000);
            screenName.check(matches(isDisplayed()));
            openCheckBox.check(matches(isDisplayed()));
            inProgressCheckBox.check(matches(isDisplayed()));
            executedCheckBox.check(matches(isDisplayed()));
            cancelledCheckBox.check(matches(isDisplayed()));
            okButton.check(matches(isDisplayed()));
            cancelButton.check(matches(isDisplayed()));
        }

        public void checkOpen() {
            Allure.step("Выбрать фильтр open");
            openCheckBox.check(matches(isChecked()));
        }

        public void uncheckOpen() {
            Allure.step("Снять фильтр open");
            openCheckBox.check(matches(isChecked())).perform(click());
        }

        public void checkInProgress() {
            Allure.step("Выбрать фильтр In progress");
            inProgressCheckBox.check(matches(isChecked()));
        }

        public void uncheckInProgress() {
            Allure.step("Снять фильтр In progress");
            inProgressCheckBox.check(matches(isChecked())).perform(click());
        }

        public void checkExecuted() {
            Allure.step("Выбрать фильтр Executed");
            executedCheckBox.check(matches(isNotChecked())).perform(click());
        }

        public void uncheckExecuted() {
            Allure.step("Снять фильтр Executed");
            executedCheckBox.check(matches(isNotChecked()));
        }

        public void checkCanceled() {
            Allure.step("Выбрать фильтр Cancel");
            cancelledCheckBox.check(matches(isNotChecked())).perform(click());
        }

        public void uncheckCanceled() {
            Allure.step("Снть фильтр Cancel");
            cancelledCheckBox.check(matches(isNotChecked()));
        }

        public void cLickOk() {
            Allure.step("Кликнуть ОК");
            okButton.perform(click());
            elementWaiting(withId(R.id.claim_list_recycler_view), 5000);
        }
    }
}
