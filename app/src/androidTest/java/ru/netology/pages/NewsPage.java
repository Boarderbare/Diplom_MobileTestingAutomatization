package ru.netology.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.netology.data.DataHelper.childAtPosition;
import static ru.netology.data.DataHelper.elementWaiting;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NewsPage {
    public ViewInteraction newsScreenName = onView(withText("News"));
    public ViewInteraction sortNewsButton = onView(withId(R.id.sort_news_material_button));
    public ViewInteraction filterNewsButton = onView(withId(R.id.filter_news_material_button));
    public ViewInteraction allNews = onView(withId(R.id.all_news_cards_block_constraint_layout));
    public ViewInteraction editButton = onView(
            allOf(withId(R.id.edit_news_material_button),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")),
                                    withId(R.id.container_list_news_include),
                                    0),
                            3)));

    public ViewInteraction expandNewsButton = onView(
            allOf(withId(R.id.news_list_recycler_view),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")),
                            withId(R.id.all_news_cards_block_constraint_layout),
                            0)));


    public void checkNewsScreenLoaded() {
        elementWaiting(withText("News"), 2000);
        newsScreenName.check(matches(isDisplayed()));
        sortNewsButton.check(matches(isDisplayed()));
        filterNewsButton.check(matches(isDisplayed()));
        allNews.check(matches(isDisplayed()));
    }
}
