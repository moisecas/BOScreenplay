// src/main/java/com/tuorg/tasks/SetEndDate.java
package com.tuorg.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.tuorg.ui.ConciliationPlayerUI.DATE_END_INPUT_HIDDEN; // <-- crea este Target en tu UI
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SetEndDate implements Task {
    private final String isoDate;
    public SetEndDate(String isoDate){ this.isoDate = isoDate; }
    public static SetEndDate to(String isoDate){ return instrumented(SetEndDate.class, isoDate); }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WebElement hiddenInput = DATE_END_INPUT_HIDDEN.resolveFor(actor);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0]._flatpickr.setDate(arguments[1]);", hiddenInput, isoDate);
    }
}
