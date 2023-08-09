package test;

import com.codeborne.selenide.Configuration;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import data.DataHelper;
import data.SqlRequest;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class AppTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void clear() {SqlRequest.clearBD();}

    @Test
    void shouldRunAppWithValidData() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = SqlRequest.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.validFields();
    }

    @Test
    void shouldWarnIfRunAppWithInvalidLogin() {
        val loginPage = new LoginPage();
        val invalidAuthInfo = DataHelper.getInvalidAuthInfo();
        loginPage.invalidLogin(invalidAuthInfo);
    }

    @Test
    public void shouldGetLockIfAnInvalidPasswordIsEnteredThreeTimes() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getInvalidAuthInfo();
        loginPage.invalidPassword(authInfo);
    }

    @Test
    void shouldWarnIfRunWithInvalidVerCode() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val invalidVerificationCode = DataHelper.getInvalidVerificationCode();
        verificationPage.invalidVerify(invalidVerificationCode);
    }
}