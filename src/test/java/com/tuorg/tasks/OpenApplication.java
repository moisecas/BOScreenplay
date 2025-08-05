package com.tuorg.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.Actor;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenApplication implements Task {

    private final String url;

    public OpenApplication(String url) {
        this.url = url;
    }

    public static OpenApplication at(String url) {
        return instrumented(OpenApplication.class, url);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(url)
        );
    }
}
