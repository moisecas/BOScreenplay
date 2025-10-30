// src/main/java/com/tuorg/tasks/OpenReport.java
package com.tuorg.tasks;

import com.tuorg.ui.ReportsMenuUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class OpenReport implements Task {
    private final String reportName;
    public OpenReport(String reportName){ this.reportName = reportName; }
    public static OpenReport named(String reportName){ return instrumented(OpenReport.class, reportName); }

    @Override public <T extends Actor> void performAs(T actor) {
        Target option = ReportsMenuUI.byName(reportName);
        actor.attemptsTo(
                WaitUntil.the(option, isVisible()).forNoMoreThan(10).seconds(),
                Scroll.to(option),
                Click.on(option),
                WaitForGrid.ready()
        );
    }
}
