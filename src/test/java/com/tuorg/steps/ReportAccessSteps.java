// src/test/java/com/tuorg/steps/ReportAccessSteps.java
package com.tuorg.steps;

import com.tuorg.tasks.NavigateToReports;
import com.tuorg.tasks.WaitForGrid;
import com.tuorg.ui.ReportsMenuUI;
import io.cucumber.java.es.Cuando;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ReportAccessSteps {

    @Cuando("navego a la seccion Reportes {string}")
    public void navegoYAbroReportePorNombre(String reporte) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                // 1) Abrir el menú padre "Reportes"
                WaitUntil.the(ReportsMenuUI.REPORTES_MENU, isClickable()).forNoMoreThan(10).seconds(),
                WaitUntil.the(ReportsMenuUI.REPORTES_MENU, isClickable()).forNoMoreThan(10).seconds(),


                // 2) Click en el reporte por texto
                WaitUntil.the(ReportsMenuUI.byName(reporte), isVisible()).forNoMoreThan(10).seconds(),
                Scroll.to(ReportsMenuUI.byName(reporte)),
                Click.on(ReportsMenuUI.byName(reporte)),

                // 3) Esperar que la grilla/estado del reporte esté listo
                WaitForGrid.ready()
        );
    }
}

