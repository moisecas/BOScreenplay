package com.tuorg.ui;

import net.serenitybdd.screenplay.targets.Target;

public class ConciliationPlayerUI {

    // Menú
    public static final Target MENU_CONCILIACION_JUGADORES = Target.the("opción Conciliación de Jugadores")
            .locatedBy("//nav//*[self::a or self::span][normalize-space()='Conciliación de Jugadores']");

    // Breadcrumb / título
    public static final Target BREADCRUMB_ACTIVE = Target.the("breadcrumb activo")
            .locatedBy("li.breadcrumb-item.active");

    // Botones
    public static final Target BTN_EXPORT = Target.the("botón exportar")
            .locatedBy("//a[@class='btn waves-effect waves-light btn-primary']");
    public static final Target BTN_FILTER = Target.the("botón filtrar")
            .locatedBy("//button[normalize-space()='Filtrar']");

    // Datepicker / filtros
    public static final Target DATE_INPUT_VISIBLE = Target.the("input visible flatpickr")
            .locatedBy("input.datetime-flatpickr");
    public static final Target DATE_INPUT_HIDDEN = Target.the("input hidden start-date (flatpickr)")
            .locatedBy("#table-filter-start-date-filter");
    public static final Target CALENDAR_OPEN = Target.the("contenedor calendario abierto")
            .locatedBy(".flatpickr-calendar.open");

    public static final Target DATE_END_INPUT_HIDDEN = Target.the("fecha fin (hidden)")
            .locatedBy("//*[@id='table-filter-end-date-filter' or @name='endDate']");
}
