//exportar los reportes de excel interactuar con el botón export de los reportes

package com.tuorg.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static com.tuorg.ui.ConciliationPlayerUI.BTN_EXPORT;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class ExportTimes implements Task {
    private final int times;
    public ExportTimes(int times){ this.times = times; }
    public static ExportTimes of(int times){ return instrumented(ExportTimes.class, times); }
    @Override public <T extends Actor> void performAs(T actor) {
        for (int i = 0; i < times; i++) {
            actor.attemptsTo( WaitUntil.the(BTN_EXPORT, isClickable()).forNoMoreThan(10).seconds(),
                    Click.on(BTN_EXPORT) );
        }
    }
}

