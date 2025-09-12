package com.tuorg.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.tuorg.ui.ListPlayersUI.MENU_REPORTES;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class NavigateToReports implements Task {

    public static NavigateToReports section() {
        return instrumented(NavigateToReports.class);
    }

    @Override
    public <T extends net.serenitybdd.screenplay.Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(MENU_REPORTES, isClickable()).forNoMoreThan(15).seconds(),
                Scroll.to(MENU_REPORTES),
                Click.on(MENU_REPORTES)
        );
    }
}

