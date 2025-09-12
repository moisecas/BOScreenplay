package com.tuorg.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.tuorg.ui.ListPlayersUI.MENU_LISTA_JUGADORES;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class OpenListPlayers implements Task {

    public static OpenListPlayers page() {
        return instrumented(OpenListPlayers.class);
    }

    @Override
    public <T extends net.serenitybdd.screenplay.Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(MENU_LISTA_JUGADORES, isClickable()).forNoMoreThan(15).seconds(),
                Scroll.to(MENU_LISTA_JUGADORES),
                Click.on(MENU_LISTA_JUGADORES)
        );
    }
}

