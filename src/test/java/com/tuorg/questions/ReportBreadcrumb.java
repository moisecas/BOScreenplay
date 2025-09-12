package com.tuorg.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import static com.tuorg.ui.ListPlayersUI.BREADCRUMB_ACTIVE;

public class ReportBreadcrumb {

    public static Question<String> text() {
        return actor -> Text.of(BREADCRUMB_ACTIVE).answeredBy(actor).trim();
    }
}

