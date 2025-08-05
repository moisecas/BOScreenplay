
package com.tuorg.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.questions.Visibility;
import com.tuorg.ui.LoginPageUI;

public class ErrorMessage implements Question<Boolean> {

    public enum Type { INVALID, EMPTY, CREDENTIALS }
    private final Type type;

    public ErrorMessage(Type type) { this.type = type; }

    public static Question<Boolean> invalid()     { return new ErrorMessage(Type.INVALID); }
    public static Question<Boolean> emptyFields(){ return new ErrorMessage(Type.EMPTY); }
    public static Question<Boolean> badCreds()    { return new ErrorMessage(Type.CREDENTIALS); }

    @Override
    public Boolean answeredBy(Actor actor) {
        switch (type) {
            case EMPTY:
                return Visibility.of(LoginPageUI.INVALID_FEEDBACK)
                        .answeredBy(actor);
            case INVALID:
            case CREDENTIALS:
            default:
                return Visibility.of(LoginPageUI.ALERT_DANGER)
                        .answeredBy(actor);
        }
    }
}

