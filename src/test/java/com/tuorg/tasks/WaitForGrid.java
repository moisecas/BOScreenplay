// src/main/java/com/tuorg/tasks/WaitForGrid.java
package com.tuorg.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class WaitForGrid implements Task {
    private static final Target ANY_HEADER = Target.the("encabezado tabla")
            .locatedBy("//table//th[normalize-space()] | //div[contains(@class,'ag-header-cell-text')]");
    private static final Target EMPTY_MSG = Target.the("mensaje vacío")
            .locatedBy("//*[contains(normalize-space(),'Ingrese un filtro') or contains(.,'presione filtrar')]");

    public static WaitForGrid ready(){ return instrumented(WaitForGrid.class); }

    @Override public <T extends Actor> void performAs(T actor) {
        try {
            WaitUntil.the(ANY_HEADER, isVisible()).forNoMoreThan(8).seconds().performAs(actor);
        } catch (AssertionError e) {
            WaitUntil.the(EMPTY_MSG, isVisible()).forNoMoreThan(8).seconds().performAs(actor);
        }
    }
}
