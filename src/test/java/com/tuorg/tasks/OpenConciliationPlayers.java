package com.tuorg.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.tuorg.ui.ConciliationPlayerUI.MENU_CONCILIACION_JUGADORES;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class OpenConciliationPlayers implements Task {

    public static OpenConciliationPlayers page() { return instrumented(OpenConciliationPlayers.class); }

    @Override
    public <T extends net.serenitybdd.screenplay.Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(MENU_CONCILIACION_JUGADORES, isClickable()).forNoMoreThan(15).seconds(),
                Scroll.to(MENU_CONCILIACION_JUGADORES),
                Click.on(MENU_CONCILIACION_JUGADORES)
        );
    }
}

