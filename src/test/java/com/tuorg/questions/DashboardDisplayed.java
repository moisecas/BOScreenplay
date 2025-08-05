package com.tuorg.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.questions.Visibility;
import com.tuorg.ui.LoginPageUI;

public class DashboardDisplayed implements Question<Boolean> {

    public static Question<Boolean> now() { // realiza la pregunta para verificar si el dashboard está visible
        return new DashboardDisplayed();
    }

    @Override // override del método answeredBy para verificar la visibilidad del dashboard 
    public Boolean answeredBy(Actor actor) {
        return Visibility.of(LoginPageUI.DASHBOARD) //retorna true si el dashboard es visible 
               
                .asBoolean() // se convierte a booleano
                .answeredBy(actor);
    }
}

