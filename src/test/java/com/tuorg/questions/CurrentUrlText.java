// src/main/java/com/tuorg/questions/CurrentUrlText.java
package com.tuorg.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class CurrentUrlText implements Question<String> {

    public static CurrentUrlText value() {
        return new CurrentUrlText();
    }

    @Override
    public String answeredBy(Actor actor) {
        return BrowseTheWeb.as(actor).getDriver().getCurrentUrl();
    }
}
