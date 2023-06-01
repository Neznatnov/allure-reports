package com.neznatnov.allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WebStepsTest {
    public static final String PROFILE = "neznatnov/";
    public static final String REPOSITORY = "Neznatnov/allure-reports";

    @Test
    @DisplayName("Шаги с аннотацией @Step")
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchForProfile(PROFILE);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.checkingTitleInRepository();
        steps.takeScreenshot();
    }
}
