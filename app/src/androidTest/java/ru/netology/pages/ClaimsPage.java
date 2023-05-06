package ru.netology.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static ru.netology.data.DataHelper.childAtPosition;
import static ru.netology.data.DataHelper.elementWaiting;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ClaimsPage {
    public ViewInteraction claimsScreenName = onView(withText("Claims"));
    public ViewInteraction claimsFilterButton = onView(withId(R.id.filters_material_button));
    public ViewInteraction createClaimButton = onView(withId(R.id.add_new_claim_material_button));
//    public ViewInteraction openClaimButton = onView(withId(R.id.));
    public ViewInteraction claimsList = onView(withId(R.id.claim_list_recycler_view));



    public void checkClaimsScreenLoaded() {
        elementWaiting(withText("Claims"), 2000);
        claimsScreenName.check(matches(isDisplayed()));
        claimsFilterButton.check(matches(isDisplayed()));
        createClaimButton.check(matches(isDisplayed()));
        claimsList.check(matches(isDisplayed()));
      }
}
