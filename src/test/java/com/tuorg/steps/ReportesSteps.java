package com.tuorg.steps;

import io.cucumber.java.es.*;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.tuorg.tasks.NavigateToReports.section;
import static com.tuorg.tasks.OpenListPlayers.page;
import static com.tuorg.ui.ListPlayersUI.BREADCRUMB_ACTIVE;
import static com.tuorg.questions.ReportBreadcrumb.text;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.equalTo;

public class ReportesSteps {

    @Dado("navego a la seccion Reportes")
    public void navegoALaSeccionReportes() {
        OnStage.theActorInTheSpotlight().attemptsTo(section());
    }

    @Cuando("selecciono la opcion Lista de Jugadores")
    public void seleccionoLaOpcionListaDeJugadores() {
        OnStage.theActorInTheSpotlight().attemptsTo(page());
    }

    @Entonces("deberia visualizar el reporte de lista de jugadores {string}")
    public void deberiaVisualizarElReporteDeListaDeJugadores(String expectedTitle) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(BREADCRUMB_ACTIVE, isVisible()).forNoMoreThan(20).seconds()
        );
        OnStage.theActorInTheSpotlight().should(
                seeThat(text(), equalTo(expectedTitle))
        );
    }
}
