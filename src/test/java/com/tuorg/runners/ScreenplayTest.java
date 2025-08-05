package com.tuorg.runners;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.cdimascio.dotenv.Dotenv;
import com.tuorg.actors.ActorBase;
import com.tuorg.tasks.OpenApplication;
import com.tuorg.tasks.Login;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

@RunWith(SerenityRunner.class)
public class ScreenplayTest {

    private Dotenv env;

    private WebDriver browser;

    @Before
    public void setUp() {
        //inicio
        OnStage.setTheStage(ActorBase.THE_CAST);

        // carga variables de env
        env = Dotenv.configure()
                .directory("./")    // carpeta donde está .env
                .ignoreIfMissing()  // no falle si no existe
                .load();

        // creación del driver
        WebDriverManager.chromedriver().setup();
        browser = new ChromeDriver();



        // actor y acción
        OnStage.theActorCalled("Moises")
                .can(BrowseTheWeb.with(browser));
    }

    @Test
    public void usuarioPuedeLoguearse() {
        // lee las credenciales desde el env
        String baseUrl = env.get("BASE_URL", System.getProperty("base.url"));
        String user    = env.get("TEST_USER");
        String pass    = env.get("TEST_PASS");

        // ejecuta el flujo
        OnStage.theActorInTheSpotlight().attemptsTo(
                OpenApplication.at(baseUrl),
                Login.with(user, pass)
        );
    }
}
