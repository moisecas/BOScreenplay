// src/test/java/com/tuorg/steps/CommonReportStructureSteps.java
package com.tuorg.steps;


import com.tuorg.ui.ReportColumnsUI;


import com.tuorg.questions.VisibleColumns;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;


import java.util.List;
import java.util.stream.Collectors;

public class CommonReportStructureSteps {

    @Y("al menos estos filtros estan visibles:")
    public void filtrosVisibles(DataTable table) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ReportColumnsUI.verificarColumnasVisibles()
        );
    }

    @Y("al menos estas columnas estan visibles en player balance:")
    public void columnasVisiblesPB(io.cucumber.datatable.DataTable table) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ReportColumnsUI.verificarColumnasVisiblesPlayerBalance()
        );
    }

    @Y("al menos estos filtros estan visibles en player balance:")
    public void filtrosVisiblesPB(io.cucumber.datatable.DataTable table) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ReportColumnsUI.verificarJugadaSiPresente(),
                ReportColumnsUI.verificarMontoMayorQueSiPresente(),
                ReportColumnsUI.verificarFiltrosVisiblesPlayerBalance()
        );
    }

    @Y("filtro por usuario {string}")
    public void filtroPorUsuario(String usuario) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ReportColumnsUI.filtrarUsuarioPorNombre(usuario)
        );
    }

    @Y("las columnas visibles son:")
    public void exactColumnas(DataTable table) {
        List<String> expected = norm(table.asList());
        List<String> actual = VisibleColumns.names().answeredBy(OnStage.theActorInTheSpotlight());
        Ensure.that(actual).containsExactlyInAnyOrderElementsFrom(expected);
    }

    private List<String> norm(List<String> raw){
        return raw.stream().map(s -> s == null ? "" : s.replace('\u00A0',' ').trim().replaceAll("\\s+"," "))
                .filter(s -> !s.isBlank()).distinct().collect(Collectors.toList());
    }

    @Y("valido estructura del otro reporte")
    public void validoEstructuraOtroReporte() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ReportColumnsUI.verificarFiltrosYColumnasOtroReporte()
        );
    }

    @Cuando("filtro usuario en otro reporte {string}")
    public void filtroUsuarioOtro(String usuario) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ReportColumnsUI.escribirUsuarioOtroReporte(usuario)
        );
    }

    @Cuando("filtro cajero en otro reporte {string}")
    public void filtroCajeroOtro(String cajero) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ReportColumnsUI.escribirCajeroOtroReporte(cajero)
        );
    }

}
