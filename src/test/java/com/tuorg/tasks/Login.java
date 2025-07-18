package com.tuorg.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.Actor;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Login implements Task {

    private final String user;
    private final String pass;

    public Login(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public static Login with(String user, String pass) {
        return instrumented(Login.class, user, pass);
    }

    private static final Target USER_FIELD =
            Target.the("username field").locatedBy("#username");
    private static final Target PASS_FIELD =
            Target.the("password field").locatedBy("#password");
    private static final Target LOGIN_BUTTON =
            Target.the("login button").locatedBy("#login");

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(user).into(USER_FIELD),
                Enter.theValue(pass).into(PASS_FIELD),
                Click.on(LOGIN_BUTTON)
        );
    }
}

