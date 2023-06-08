package com.neznatnov.allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {

    public static final String PROFILE = "eroshenkoam/";
    public static final String REPOSITORY = "eroshenkoam/allure-example";

    @Test
    @Feature("'Issues' в репозитории")
    @Owner("neznatnov")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Лямбда шаги через step")


    public void testLambdaStep() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com/");
            attachment("Source", webdriver().driver().source());
        });

        step("Ищем профиль " + PROFILE, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(PROFILE);
            $(".header-search-input").submit();
        });

        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Кликаем на вкладку Issue", () -> {
            $("#issues-tab").click();
        });

        step("Проверка названия наличия элемента во вкладке Issue", () -> {
            $(String.format("#issue_%s_link", "81")).shouldHave(text("issue_to_test_allure_report"));

        });
    }
}

