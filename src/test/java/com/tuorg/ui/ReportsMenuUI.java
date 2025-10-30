
package com.tuorg.ui;

import net.serenitybdd.screenplay.targets.Target;

public class ReportsMenuUI {

    public static final Target REPORTES_MENU =
            Target.the("menú Reportes")
                    .locatedBy("//span[normalize-space()='Reportes'] | //a[normalize-space()='Reportes']");

    /** Locator genérico por nombre (span o a con texto normalizado). */
    public static final Target REPORT_OPTION_BY_NAME =
            Target.the("opción de reporte {0}")
                    .locatedBy("//*[self::span or self::a][normalize-space()='{0}']");

    /** Helper genérico por nombre. */
    public static Target byName(String name) {
        return REPORT_OPTION_BY_NAME.of(name);
    }

    // ===== Métodos específicos (evitan typos y dan autocompletado) =====
    public static Target listaDeJugadores()         { return byName("Lista de Jugadores"); }
    public static Target transaccionesJugador()     { return byName("Transacciones Jugador"); }
    public static Target balanceDeCajeros()         { return byName("Balance de Cajeros"); }
    public static Target jugadoresRegistrados()     { return byName("Jugadores Registrados"); }
    public static Target conciliacionDeJugadores()  { return byName("Conciliación de Jugadores"); }
    public static Target jugadoresConectados()      { return byName("Jugadores Conectados"); }
    public static Target listaDeNotificaciones()    { return byName("Lista de Notificaciones"); }
    public static Target cargasAutomaticas()        { return byName("Cargas Automáticas"); }
    public static Target exportarAlBanco()          { return byName("Exportar al banco"); }
    public static Target resumenCargasPorJugador()  { return byName("Resumen Cargas por Jugador"); }
    public static Target reporteDeAuditoria()       { return byName("Reporte de Auditoría"); }
    public static Target transaccionesJugadores()   { return byName("Transacciones Jugadores"); }


}
