package ru.netology.data;

import android.os.SystemClock;

import ru.netology.pages.AuthPage;
import ru.netology.pages.ClaimPage;
import ru.netology.pages.ClaimsPage;
import ru.netology.pages.ControlPanelPage;
import ru.netology.pages.MainScreenPage;
import ru.netology.pages.NewsPage;
import ru.netology.pages.OurMissionPage;

public class ReadyScreen {
    AuthPage authPage = new AuthPage();
    MainScreenPage mainScreenPage = new MainScreenPage();
    ClaimPage claimPage = new ClaimPage();
    ClaimsPage claimsPage = new ClaimsPage();
    NewsPage newsPage = new NewsPage();
    ControlPanelPage controlPanelPage = new ControlPanelPage();
    OurMissionPage ourMissionPage = new OurMissionPage();

    public void readyAuthScreen() {
        SystemClock.sleep(5000);
        try {
            authPage.checkLoadScreen();
            authPage.isAuthScreen();
        }
        catch (Exception e) {
            mainScreenPage.logOut();
            authPage.isAuthScreen();
        }
    }
    public void readyClaimsScreen() {
        SystemClock.sleep(5000);
        try {
            mainScreenPage.checkMainScreenLoaded();
            mainScreenPage.goToClaims();
        } catch (Exception e) {
            authPage.login();
            mainScreenPage.goToClaims();
        } finally {
            claimsPage.checkClaimsScreenLoaded();
        }
    }
    public void readyControlPanelScreen() {
        SystemClock.sleep(5000);
        try {
            mainScreenPage.checkMainScreenLoaded();
            mainScreenPage.goToNews();
            newsPage.goToControlPanel();
        } catch (Exception e) {
            authPage.login();
            mainScreenPage.goToNews();
            newsPage.goToControlPanel();
        } finally {
            controlPanelPage.checkControlPanelScreenLoaded();
        }
    }
    public void readyMainScreen() {
        SystemClock.sleep(5000);
        try {
            mainScreenPage.checkMainScreenLoaded();
        } catch (Exception e) {
            authPage.login();
        } finally {
            mainScreenPage.checkMainScreenLoaded();
        }
    }
    public void readyNewsScreen() {
        SystemClock.sleep(5000);
        try {
            mainScreenPage.checkMainScreenLoaded();
            mainScreenPage.goToNews();
        } catch (Exception e) {
            authPage.login();
            mainScreenPage.goToNews();
        } finally {
            newsPage.checkNewsScreenLoaded();
        }
    }
    public void readyAboutScreen() {
        SystemClock.sleep(5000);
        try {
            mainScreenPage.checkMainScreenLoaded();
            mainScreenPage.goOurMission();
        } catch (Exception e) {
            authPage.login();
            mainScreenPage.goOurMission();
        } finally {
            ourMissionPage.checkOurMissionScreenLoaded();
        }
    }
}
