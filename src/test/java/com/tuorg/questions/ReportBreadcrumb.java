// src/main/java/com/tuorg/questions/ReportBreadcrumb.java
package com.tuorg.questions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

import java.text.Normalizer;
import java.util.List;
import java.util.stream.Collectors;

public class ReportBreadcrumb implements Question<String> {

    // Contenedor + ítems típicos de breadcrumb
    private static final Target BREADCRUMB_ITEMS = Target.the("items de breadcrumb")
            .locatedBy("((//*[contains(@class,'breadcrumb')] | //*[@aria-label='breadcrumb'])"
                    + "//*[self::li or self::a or self::span][normalize-space()])");

    // Fallbacks por si el breadcrumb no trae item “activo”
    private static final Target CARD_HEADER_HEADING = Target.the("título en card-header")
            .locatedBy("//*[contains(@class,'card-header')]//*[self::h1 or self::h2 or self::h3][normalize-space()]");

    private static final Target MAIN_HEADING = Target.the("título principal")
            .locatedBy("//main//*[self::h1 or self::h2][normalize-space()] | //section//*[self::h1 or self::h2][normalize-space()]");

    public static ReportBreadcrumb text(){ return new ReportBreadcrumb(); }

    @Override
    public String answeredBy(Actor actor) {
        // 1) Toma TODOS los items del breadcrumb y devuelve el ÚLTIMO significativo (≠ “Inicio”)
        List<String> items = BREADCRUMB_ITEMS.resolveAllFor(actor).stream()
                .map(WebElementFacade::getText)
                .map(ReportBreadcrumb::norm)
                .filter(s -> !s.isBlank())
                .collect(Collectors.toList());

        String selected = "";
        if (!items.isEmpty()) {
            // último que NO sea "Inicio"
            for (int i = items.size() - 1; i >= 0; i--) {
                String v = items.get(i);
                if (!"inicio".equalsIgnoreCase(v)) { selected = v; break; }
            }
            if (selected.isBlank()) selected = items.get(items.size() - 1); // por si todo era "Inicio"
        }

        // 2) Fallbacks si quedó vacío
        if (selected.isBlank() && !CARD_HEADER_HEADING.resolveAllFor(actor).isEmpty()) {
            selected = norm(CARD_HEADER_HEADING.resolveFor(actor).getText());
        }
        if (selected.isBlank() && !MAIN_HEADING.resolveAllFor(actor).isEmpty()) {
            selected = norm(MAIN_HEADING.resolveFor(actor).getText());
        }

        return selected;
    }

    private static String norm(String s){
        if (s == null) return "";
        return Normalizer.normalize(s, Normalizer.Form.NFKC)
                .replace('\u00A0',' ')
                .trim()
                .replaceAll("\\s+"," ");
    }
}
