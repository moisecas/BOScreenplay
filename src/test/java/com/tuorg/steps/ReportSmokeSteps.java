// src/test/java/com/tuorg/steps/ReportSmokeSteps.java
package com.tuorg.steps;

import com.tuorg.questions.CurrentUrlText;
import com.tuorg.questions.ReportBreadcrumb;
import com.tuorg.tasks.*;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

public class ReportSmokeSteps {



    @Cuando("abro directamente el reporte por slug {string}")
    public void abroPorSlug(String slug) {
        String base = System.getProperty("base.url",
                System.getenv().getOrDefault("BASE_URL", "https://backoffice-v2.qa.wcbackoffice.com"));
        OnStage.theActorInTheSpotlight().attemptsTo(
                OpenApplication.at(base + slug)
        );
    }


    @Entonces("la URL actual contiene {string}")
    public void urlContiene(String slug) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Ensure.that(CurrentUrlText.value()).contains(slug)
        );
    }

    // en ReportSmokeSteps
    @Entonces("deberia visualizar el reporte {string}")
    public void validaBreadcrumb(String esperado) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Ensure.that(ReportBreadcrumb.text()).contains(esperado) // <= más tolerante
        );
    }


    @Cuando("establezco fecha inicio {string} y fecha fin {string} y aplico el filtro")
    public void setFechasYFiltrar(String inicio, String fin) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                SetStartDate.to(inicio),
                SetEndDate.to(fin),
                ClickFilter.now()
        );
    }

    @Cuando("aplico el filtro")
    public void Filtrar() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ClickFilter.now()
        );
    }

    @Y("descargo el reporte {int} veces")
    public void exportoNVeces(int veces) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ExportTimes.of(veces)
        );
    }
}
