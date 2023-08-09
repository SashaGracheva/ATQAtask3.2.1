package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement dashboardField = $("[data-test-id='dashboard']");

    public DashboardPage() {
        dashboardField.shouldBe(Condition.visible);
    }

    public void validFields() {
        dashboardField.shouldHave(exactText("Личный кабинет"));
    }
}