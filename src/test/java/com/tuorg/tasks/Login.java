// login a bo2


package com.tuorg.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.Actor;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import com.tuorg.ui.LoginPageUI;

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

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(user).into(LoginPageUI.USER_FIELD),
                Enter.theValue(pass).into(LoginPageUI.PASS_FIELD),
                Click.on(LoginPageUI.LOGIN_BUTTON)
        );
    }
}
