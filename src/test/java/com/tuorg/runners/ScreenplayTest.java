package com.tuorg.runners;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import com.tuorg.actors.ActorBase;
import com.tuorg.tasks.OpenApplication;
import com.tuorg.tasks.Login;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

@RunWith(SerenityRunner.class)
public class ScreenplayTest {

    private WebDriver browser;

    @Before
    public void setUp() {
        // 1) Inicializa el Stage
        OnStage.setTheStage(ActorBase.THE_CAST);

        // 2) Levanta el driver
        WebDriverManager.chromedriver().setup();
        browser = new ChromeDriver();

        // 3) Llama al actor y dale la habilidad
        OnStage.theActorCalled("Moises")
                .can(BrowseTheWeb.with(browser));
    }

    @Test
    public void usuarioPuedeLoguearse() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                OpenApplication.at(System.getProperty("base.url")),
                Login.with("usuario","password")
        );
    }
}
