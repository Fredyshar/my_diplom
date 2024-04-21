package ru.iteco.fmhandroid.ui;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.common.BaseSteps;
import ru.iteco.fmhandroid.ui.common.TestData;
import ru.iteco.fmhandroid.ui.pages.AboutPage;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AboutPageTests {
    private final BaseSteps baseSteps = new BaseSteps();
    private final AuthorizationPage authPage = new AuthorizationPage();
    private final MainPage mainPage = new MainPage();
    private final TestData testData = new TestData();
    private final AboutPage aboutPage = new AboutPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before

    public void logIn() {
        authPage.logIn(testData.getValidLogin(), testData.getValidPassword());
    }

    @After
    public void logOut() {
        baseSteps.logout();
    }

    @Test
    @DisplayName("Отображение элементов на странице 'О приложении', и клик по кнопке назад")
    public void test_displayed_main_elements_on_the_page_and_click_back() {
        mainPage.goToAboutPage();

        aboutPage.checkDisplayedVersionAndCompanyInfo();
        aboutPage.checkDisplayedTitlesAndLink();

        aboutPage.clickBackButton();
        mainPage.checkInitStatePage();
    }

    @Test
    @DisplayName("Перехода по первой ссылке - privacy_policy")
    public void test_open_page_with_privacy_policy() {
        mainPage.goToAboutPage();
        aboutPage.checkOpenOfPageByLink(testData.getLinkContainingSubstring("privacy"));
        aboutPage.clickBackButton();
    }

    @Test
    @DisplayName("Перехода по второй ссылке - terms_of_use")
    public void test_open_page_with_terms_of_use() {
        mainPage.goToAboutPage();
        aboutPage.checkOpenOfPageByLink(testData.getLinkContainingSubstring("terms"));
        aboutPage.clickBackButton();
    }
}