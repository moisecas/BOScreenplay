// asignar fecha de los datepicker del bo2 


package com.tuorg.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.tuorg.ui.ConciliationPlayerUI.DATE_INPUT_HIDDEN;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SetStartDate implements Task {
    private final String isoDate;
    public SetStartDate(String isoDate){ this.isoDate = isoDate; }
    public static SetStartDate to(String isoDate){ return instrumented(SetStartDate.class, isoDate); }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WebElement hiddenInput = DATE_INPUT_HIDDEN.resolveFor(actor);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0]._flatpickr.setDate(arguments[1]);",
                        hiddenInput, isoDate);
    }
}
