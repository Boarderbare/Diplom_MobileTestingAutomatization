package ru.netology.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.netology.data.DataHelper.elementWaiting;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AboutPage {

    public ViewInteraction tradeMarkImage = onView(withId(R.id.trademark_image_view));
    public ViewInteraction version = onView(withId(R.id.about_version_title_text_view));
    public ViewInteraction versionValue = onView(withId(R.id.about_version_value_text_view));
    public ViewInteraction privacyPolicy = onView(withId(R.id.about_privacy_policy_label_text_view));
    public ViewInteraction privacyPolicyLink = onView(withId(R.id.about_privacy_policy_value_text_view));
    public ViewInteraction termsOfUse = onView(withId(R.id.about_terms_of_use_label_text_view));
    public ViewInteraction termsOfUseLink = onView(withId(R.id.about_privacy_policy_value_text_view));
    public ViewInteraction companyInfo = onView(withId(R.id.about_company_info_label_text_view));
    public ViewInteraction returnBtn = onView(withId(R.id.about_back_image_button));

    public void checkAboutScreenLoaded() {
            elementWaiting(withText("Love is all"), 5000);
        }

        public  void isOurMissiionScreen() {
//            screenName.check(matches(isDisplayed()));
//            listOfItems.check(matches(isDisplayed()));
        }

    public void signIn() {

    }

}
