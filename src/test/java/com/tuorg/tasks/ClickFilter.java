//clic sobre el botón filtrar de los reportes

package com.tuorg.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static com.tuorg.ui.ConciliationPlayerUI.BTN_FILTER;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class ClickFilter implements Task {
    public static ClickFilter now(){ return instrumented(ClickFilter.class); }
    @Override public <T extends net.serenitybdd.screenplay.Actor> void performAs(T actor) {
        actor.attemptsTo( WaitUntil.the(BTN_FILTER, isClickable()).forNoMoreThan(10).seconds(),
                Click.on(BTN_FILTER) );
    }
}
