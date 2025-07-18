
// Definición de targets o locators o los botones mapeados a interacturar

package com.tuorg.UI;

import net.serenitybdd.screenplay.targets.Target; //target para definir y referenciar elementos de la interfaz
import org.openqa.selenium.By;

public class LoginPageUI {
    public static final Target USER_FIELD = Target
            .the("campo usuario")
            .located(By.xpath("//input[@placeholder='Usuario']"));

    public static final Target PASS_FIELD = Target
            .the("campo contraseña")
            .located(By.xpath("//input[@placeholder='Contraseña']"));

    public static final Target LOGIN_BUTTON = Target
            .the("botón ingresar")
            .located(By.xpath("//input[@value='Ingresar']"));

    public static final Target DASHBOARD = Target
            .the("logo del dashboard")
            .located(By.xpath("//img[@alt='Theme-Logo']"));

    public static final Target ERROR_INVALID = Target
            .the("mensaje credenciales inválidas")
            .located(By.xpath("//span[contains(text(),'Combinación de usuario y contraseña incorrecta')]"));

    public static final Target ERROR_EMPTY = Target
            .the("mensaje campos obligatorios")
            .located(By.xpath("//div[@class='auth-box card']//div[2]//div[1]"));

    public static final Target ERROR_CREDENTIALS = Target
            .the("mensaje usuario o contraseña incorrectos")
            .located(By.xpath("//div[@class='form-group has-error']"));
}

