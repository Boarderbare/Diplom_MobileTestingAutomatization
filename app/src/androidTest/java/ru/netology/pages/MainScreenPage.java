package ru.netology.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.core.IsInstanceOf;

import ru.iteco.fmhandroid.R;
import ru.netology.data.DataHelper;
import static ru.netology.data.DataHelper.*;

public class MainScreenPage {

    //top
    public ViewInteraction menuButton = onView(withId(R.id.main_menu_image_button));
    public ViewInteraction tradeMark = onView(withId(R.id.trademark_image_view));
    public ViewInteraction ourMissionButton = onView(withId(R.id.our_mission_image_button));
    public ViewInteraction logOutButton = onView(withId(R.id.authorization_image_button));

    //Text menu
    public ViewInteraction menuMain = onView(withText("Main"));
    public ViewInteraction menuClaims = onView(withText("Claims"));
    public ViewInteraction menuNews = onView(withText("News"));
    public ViewInteraction menuAbout = onView(withText("About"));
    public ViewInteraction logOut = onView(withText("Log out"));

    // news
    public ViewInteraction news = onView(withText("News"));
    public ViewInteraction newsUnit = onView(withId(R.id.news_list_recycler_view));
    public ViewInteraction allNewsButton = onView(withId(R.id.all_news_text_view));
    public ViewInteraction singleNewsButton = onView(allOf(withId(R.id.news_list_recycler_view),
            childAtPosition(withId(R.id.all_news_cards_block_constraint_layout), 0)));
    public ViewInteraction unitNewsButton = onView(
            allOf(withId(R.id.expand_material_button),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")),
                                        withId(R.id.container_list_news_include_on_fragment_main),
                                    0),
                            4)));

    // Claims
    public ViewInteraction claims = onView(withText("Claims"));
    public ViewInteraction claimsUnit = onView(withId(R.id.claim_list_recycler_view));
    public ViewInteraction allClaimsButton = onView(withId(R.id.all_claims_text_view));
    public ViewInteraction singleClaim = onView(allOf(withId(R.id.claim_list_recycler_view),
            childAtPosition(withId(R.id.all_claims_cards_block_constraint_layout), 4)));
    public ViewInteraction createClaimButton = onView(withId(R.id.add_new_claim_material_button));
    public ViewInteraction unitClaimsButton = onView(
            allOf(withId(R.id.expand_material_button),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")),
                                        withId(R.id.container_list_claim_include_on_fragment_main),
                                    0),
                            3)));
//    public ViewInteraction claimList = onView(
//            allOf(withId(R.id.claim_list_recycler_view),
//                    childAtPosition(
//                            withClassName(is("android.widget.LinearLayout")),
//                            withId(R.id.all_claims_cards_block_constraint_layout),
//                            4)));

    NewsPage newsPage = new NewsPage();
    ClaimsPage claimsPage = new ClaimsPage();

    public void checkMainScreenLoaded() {
            elementWaiting(withText("News"), 5000);
        }

        public void isMainScreen() {
            tradeMark.check(matches(isDisplayed()));
            news.check(matches(isDisplayed()));
            newsUnit.check(matches(isDisplayed()));
            claims.check(matches(isDisplayed()));
            claimsUnit.check(matches(isDisplayed()));
        }

    public void  logOut() {
        logOutButton.perform(click());
        logOut.check(matches(isDisplayed()));
        logOut.perform(click());
    }

    public void  goToClaims() {
        menuButton.check(matches(isDisplayed()));
        menuButton.perform(click());
        menuClaims.check(matches(isDisplayed()));
        menuClaims.perform(click());
//        claimsPage.checkClaimsScreenLoaded();
    }
    public void  goToNews() {
        menuButton.check(matches(isDisplayed()));
        menuButton.perform(click());
        menuNews.check(matches(isDisplayed()));
        menuNews.perform(click());
//        newsPage.checkNewsScreenLoaded();
    }

    public void ourMissionButton() {
        ourMissionButton.perform(click());
    }

    public void descriptionNewsIsDisplayed(int position) {
        String descriptionText = DataHelper.
                Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view),
                        position)));
        ViewInteraction newsDescription = onView(
                allOf(withId(R.id.news_item_description_text_view), withText(descriptionText)));
        newsDescription.check(matches(isDisplayed()));
    }
    public void descriptionNewsIsNotDisplayed(int position) {
        String descriptionText = DataHelper.
                Text.getText(onView(withIndex(withId(R.id.news_item_description_text_view), position)));
        ViewInteraction newsDescription = onView(
                allOf(withId(R.id.news_item_description_text_view), withText(descriptionText)));
        newsDescription.check(matches(not(isDisplayed())));
    }
}
