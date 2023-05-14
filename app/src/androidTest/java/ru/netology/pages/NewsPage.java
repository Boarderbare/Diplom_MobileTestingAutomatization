package ru.netology.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.netology.data.DataHelper.childAtPosition;
import static ru.netology.data.DataHelper.elementWaiting;
import static ru.netology.data.DataHelper.withIndex;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;

public class NewsPage {
    ControlPanelPage controlPanelPage = new ControlPanelPage();
    public ViewInteraction newsScreenName = onView(withText("News"));
    public ViewInteraction sortNewsButton = onView(withId(R.id.sort_news_material_button));
    public ViewInteraction filterNewsButton = onView(withId(R.id.filter_news_material_button));
    public ViewInteraction allNewsList = onView(withId(R.id.all_news_cards_block_constraint_layout));
    public  int newsList = R.id.news_list_recycler_view;
//    public ViewInteraction editButton = onView(
//            allOf(withId(R.id.edit_news_material_button),
//                    childAtPosition(
//                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
//                                    withClassName(is("android.widget.LinearLayout")),
//                                    withId(R.id.container_list_news_include),
//                                    0),
//                            3)));
    public ViewInteraction editButton = onView(withId(R.id.edit_news_material_button));
    public ViewInteraction expandNewsButton = onView(
            allOf(withId(R.id.news_list_recycler_view),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")),
                            withId(R.id.all_news_cards_block_constraint_layout),
                            0)));
    public ViewInteraction dateNewsField = onView(withIndex(withId(R.id.news_item_date_text_view), 0));
    public ViewInteraction dateNewsFieldAfterSort = onView(withIndex(withId(R.id.news_item_date_text_view), 0));
    public ViewInteraction titleFirstNews = onView(withIndex(withId(R.id.news_item_title_text_view), 0));
    public ViewInteraction descriptionFirstNews = onView(withIndex(withId(R.id.news_item_description_text_view), 0));


    public void checkNewsScreenLoaded() {
        elementWaiting(withText("News"), 2000);
        newsScreenName.check(matches(isDisplayed()));
        sortNewsButton.check(matches(isDisplayed()));
        filterNewsButton.check(matches(isDisplayed()));
        allNewsList.check(matches(isDisplayed()));
    }


    public void checkListNewsLoaded() {
    elementWaiting(withId(newsList), 10000);
    }

    public void checkDescriptionView(String text) {
        onView(allOf(withId(R.id.news_item_title_text_view), withText(text))).
                check(matches(isDisplayed()));
    }

    public void goToControlPanel() {
        editButton.perform(click());
        controlPanelPage.checkControlPanelScreenLoaded();
    }

}
