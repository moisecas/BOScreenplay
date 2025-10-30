
package com.tuorg.ui;
import net.serenitybdd.screenplay.targets.Target;

public class ReportsUI {
    public static final Target BREADCRUMB_ACTIVE = Target.the("breadcrumb activo")
            .locatedBy("//li[@class='breadcrumb-item active']");

    // Botón que despliega/colapsa el panel de filtros
    public static final Target FILTERS_TOGGLE = Target.the("botón Filtros")
            .locatedBy("//button[normalize-space()='Filtros']");

    // Contenedor visible de filtros (cuando filtersOpen == true)
    public static final Target FILTERS_CONTAINER = Target.the("panel de filtros")
            .locatedBy("//*[(@x-show and contains(@x-show,'filtersOpen')) or @id='datatable-']/descendant::*[contains(@class,'container-fluid')][1]");

    /**
     * Control del filtro por etiqueta. Intenta:
     * 1) identifica por <label for=...> → elemento con id=<for>
     * 2) Input/select/textarea siguiente al label/div/span con el texto
     * 3) Fallback por placeholder/aria-label/name
     */
    public static final Target FILTER_CONTROL_BY_LABEL = Target.the("filtro {0}")
            .locatedBy(
                    // 1) usar el for del label: id = //label[@for]
                    "//*[@id=//label[normalize-space()='{0}']/@for]" +
                            " | " +
                            // 2) label/div/span con texto -> primer input/select/textarea siguiente (dentro de filtros)
                            "((//*[contains(@class,'filters') or contains(@class,'filter') or contains(@class,'card') or contains(@class,'form')])" +
                            " //*[self::label or self::div or self::span][normalize-space()='{0}']" +
                            "/following::*[self::input or self::select or self::textarea][1])" +
                            " | " +
                            // 3) fallback directo por atributos del control
                            "(//*[self::input or self::select or self::textarea]" +
                            "[@placeholder='{0}' or @aria-label='{0}' or @name='{0}'])"
            );
}

