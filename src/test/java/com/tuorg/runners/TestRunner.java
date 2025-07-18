
package com.tuorg.runners;

import net.serenitybdd.cucumber.CucumberWithSerenity;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue     = "com.tuorg.steps",
        plugin   = {"pretty","html:target/cucumber-reports.html"}
)
public class TestRunner {}

