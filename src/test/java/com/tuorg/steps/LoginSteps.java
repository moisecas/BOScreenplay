package com.tuorg.steps;

import com.tuorg.ui.LoginPageUI;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.screenplay.waits.WaitUntil;
import com.tuorg.actors.ActorBase;
import com.tuorg.tasks.OpenApplication;
import com.tuorg.tasks.Login;
import com.tuorg.questions.DashboardDisplayed;
import com.tuorg.questions.ErrorMessage;
import io.github.cdimascio.dotenv.Dotenv;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class LoginSteps {

    private WebDriver browser;
    private Dotenv env;

    @Before
    public void setUp() {
        // 1) Inicializa el Stage
        OnStage.setTheStage(ActorBase.THE_CAST);

        // 2) Crea y configura el WebDriver manualmente
        WebDriverManager.chromedriver().setup();
        browser = new ChromeDriver();

        // 3) Da al actor la habilidad de navegar con ese driver
        Actor user = OnStage.theActorCalled("User");
        user.can(BrowseTheWeb.with(browser));

        // 4) Carga el .env
        env = Dotenv.configure()
                .directory("./")
                .ignoreIfMissing()
                .load();
    }

    @After
    public void tearDown() {
        if (browser != null) {
            browser.quit();
        }
    }

    @Given("ingreso a la pagina de login")
    public void openLogin() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                OpenApplication.at(env.get("BASE_URL", System.getProperty("base.url")))
        );
    }

    @When("ingreso con el usuario de entorno y la contraseña de entorno")
    public void loginValid() {
        String u = env.get("TEST_USER");
        String p = env.get("TEST_PASS");
        OnStage.theActorInTheSpotlight()
                .attemptsTo(Login.with(u, p));
    }

    @Then("deberia acceder exitosamente")
    public void shouldSeeDashboard() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(DashboardDisplayed.now(), is(true))
        );
    }

    @When("ingreso con el usuario de entorno y la contraseña fallida de entorno")
    public void loginWrongPass() {
        String u = System.getenv("TEST_USER");
        String p = System.getenv("TEST_PASS_FAIL");
        OnStage.theActorInTheSpotlight()
                .attemptsTo(Login.with(u, p));
    }

    @When("intento iniciar sesion sin ingresar usuario ni contraseña")
    public void loginEmpty() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(Login.with("", ""));
    }

    @When("ingreso con el usuario incorrecto de entorno y la contraseña fallida de entorno")
    public void loginWrongUser() {
        String p = System.getenv("TEST_PASS");
        OnStage.theActorInTheSpotlight()
                .attemptsTo(Login.with("wrongUser", p));
    }

    @Then("deberia mostrar un mensaje de error")
    public void shouldSeeInvalidError() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(ErrorMessage.invalid(), is(true))
        );
    }

    @Then("deberia mostrar un mensaje de error indicando que los campos son obligatorios")
    public void shouldSeeEmptyError() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(ErrorMessage.emptyFields(), is(true))
        );
    }

    @Then("deberia mostrar un mensaje de error por usuario incorrecto")
    public void shouldSeeBadCredsError() {
        Actor user = OnStage.theActorInTheSpotlight();

        // espera hasta que el span dentro de div.alert-danger sea visible
        user.attemptsTo(
                WaitUntil.the(LoginPageUI.ERROR_INVALID, isVisible())
                        .forNoMoreThan(5).seconds()
        );

        //comprueba que esté presente
        user.should(
                seeThat(ErrorMessage.badCreds(), is(true))
        );
    }
}
