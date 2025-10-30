// src/main/java/com/tuorg/questions/VisibleColumns.java
package com.tuorg.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.WebElement;

import java.text.Normalizer;
import java.util.List;
import java.util.stream.Collectors;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class VisibleColumns implements Question<List<String>> {
    private static final Target TH_HEADERS = Target.the("encabezados th")
            .locatedBy("//table[.//thead or .//th]//th[normalize-space()]");
    private static final Target AG_HEADERS = Target.the("encabezados ag-Grid")
            .locatedBy("//div[contains(@class,'ag-header')]//div[contains(@class,'ag-header-cell-text') and normalize-space()]");

    public static VisibleColumns names(){ return new VisibleColumns(); }

    @Override
    public List<String> answeredBy(Actor actor) {
        boolean hasAg = !AG_HEADERS.resolveAllFor(actor).isEmpty();
        WaitUntil.the(hasAg ? AG_HEADERS : TH_HEADERS, isVisible()).forNoMoreThan(10).seconds().performAs(actor);

        List<String> th = TH_HEADERS.resolveAllFor(actor).stream().map(WebElement::getText).map(this::norm).toList();
        List<String> ag = AG_HEADERS.resolveAllFor(actor).stream().map(WebElement::getText).map(this::norm).toList();
        return (ag.isEmpty() ? th : ag).stream().filter(s -> !s.isBlank()).distinct().collect(Collectors.toList());
    }
    private String norm(String s){
        if (s == null) return "";
        return Normalizer.normalize(s, Normalizer.Form.NFKC).replace('\u00A0',' ').trim().replaceAll("\\s+"," ");
    }
}
