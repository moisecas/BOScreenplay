// src/main/java/com/tuorg/ui/ReportColumnsUI.java
package com.tuorg.ui;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.AnonymousPerformableFunction;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.Performable;
import java.time.Duration;
import java.util.List;

import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import org.openqa.selenium.WebElement;

/**
 * UI simple para columnas del reporte Lista de Jugadores
 * + método que valida que estén visibles.
 */
public class ReportColumnsUI {

    // ====== XPATHS DE COLUMNAS LISTA DE JUGADORES ======
    public static final Target ID_USUARIO       = Target.the("Id usuario").locatedBy("//th[normalize-space()='Id usuario']");
    public static final Target USUARIO          = Target.the("Usuario").locatedBy("//th[normalize-space()='Usuario']");
    public static final Target BALANCE          = Target.the("Balance").locatedBy("//th[normalize-space()='Balance']");
    public static final Target ACCIONES_BALANCE = Target.the("Acciones de balance").locatedBy("//th[normalize-space()='Acciones de balance']");
    public static final Target CORREO           = Target.the("Correo").locatedBy("//th[normalize-space()='Correo']");
    public static final Target NRO_DOCUMENTO    = Target.the("Nro. Documento").locatedBy("//th[normalize-space()='Nro. Documento']");
    public static final Target NOMBRE           = Target.the("Nombre").locatedBy("//th[normalize-space()='Nombre']");
    public static final Target APELLIDO         = Target.the("Apellido").locatedBy("//th[normalize-space()='Apellido']");
    public static final Target TELEFONO         = Target.the("Teléfono").locatedBy("//th[normalize-space()='Teléfono']");
    public static final Target SKIN             = Target.the("Skin").locatedBy("//th[normalize-space()='Skin']");
    public static final Target DIRECCION_IP     = Target.the("Dirección IP").locatedBy("//th[normalize-space()='Dirección IP']");
    public static final Target ESTADO           = Target.the("Estado").locatedBy("//th[normalize-space()='Estado']");
    public static final Target OBSERVACION      = Target.the("Observación").locatedBy("//th[normalize-space()='Observación']");

    public static Performable verificarColumnasVisibles() {
        return Task.where("Verificar columnas visibles en Lista de Jugadores",
                actor -> {
                    Target[] columnas = {
                            ID_USUARIO, USUARIO, BALANCE, ACCIONES_BALANCE, CORREO,
                            NRO_DOCUMENTO, NOMBRE, APELLIDO, TELEFONO, SKIN,
                            DIRECCION_IP, ESTADO, OBSERVACION
                    };

                    for (Target columna : columnas) {
                        actor.attemptsTo(
                                Scroll.to(columna),
                                WaitUntil.the(columna, isVisible()).forNoMoreThan(10).seconds()
                        );
                    }
                }
        );
    }

    // === PLAYER BALANCE ===

    private static final String PB_ROOT =
            "(//div[contains(@class,'card-block') and contains(@class,'table-border-style')]//div[starts-with(@id,'datatable-')])[last()]";

    // Panel de filtros visible dentro del ROOT
    private static final String PB_FILTERS_PANEL =
            PB_ROOT + "//div[@x-show='filtersOpen' or contains(@x-show,'filtersOpen')]";
    // --- Targets Jugada (label + input) ---



    private static final String[] PB_FILTROS_REQUERIDOS_XPATH = new String[] {
            PB_FILTERS_PANEL + "//*[(self::label and @for='table-filter-start-date-filter') or @id='table-filter-start-date-filter'][1]",
            PB_FILTERS_PANEL + "//*[(self::label and @for='table-filter-end-date-filter')   or @id='table-filter-end-date-filter'][1]",
            PB_FILTERS_PANEL + "//*[(self::label and (@for='table-filter-player-filter' or normalize-space()='Usuario')) or @id='table-filter-player-filter' or (@type='text' and contains(@placeholder,'Filtrar Usuario'))][1]",
            PB_FILTERS_PANEL + "//*[(self::label and @for='table-filter-movement-type-small-filter') or @id='table-filter-movement-type-small-filter'][1]",
            PB_FILTERS_PANEL + "//*[(self::label and (@for='table-filter-round-filter' or normalize-space()='Ronda')) or @id='table-filter-round-filter'][1]",
            PB_FILTERS_PANEL + "//*[(self::label and (@for='table-filter-provider-filter' or normalize-space()='Proveedor')) or @id='table-filter-provider-filter'][1]"
    };


    // THEAD scoped a la misma tabla (evita cabezales de otras tablas)
    private static final String THEAD = PB_ROOT + "//table//thead";
    public static final Target PB_C_ID            = Target.the("PB col Id").locatedBy(THEAD + "//th[normalize-space()='Id']");
    public static final Target PB_C_FECHA         = Target.the("PB col Fecha").locatedBy(THEAD + "//th[.//span[normalize-space()='Fecha']]");
    public static final Target PB_C_PROVEEDOR     = Target.the("PB col Proveedor").locatedBy(THEAD + "//th[.//span[normalize-space()='Proveedor']]");
    public static final Target PB_C_TIPO          = Target.the("PB col Tipo").locatedBy(THEAD + "//th[normalize-space()='Tipo']");
    public static final Target PB_C_MONTO         = Target.the("PB col Monto").locatedBy(THEAD + "//th[normalize-space()='Monto']");
    public static final Target PB_C_BALANCE       = Target.the("PB col Balance").locatedBy(THEAD + "//th[normalize-space()='Balance']");
    public static final Target PB_C_JUGADA        = Target.the("PB col Jugada").locatedBy(THEAD + "//th[.//span[normalize-space()='Jugada']]");
    public static final Target PB_C_RONDA         = Target.the("PB col Ronda").locatedBy(THEAD + "//th[.//span[normalize-space()='Ronda']]");
    public static final Target PB_C_OBSERVACIONES = Target.the("PB col Observaciones").locatedBy(THEAD + "//th[normalize-space()='Observaciones']");

    // --- FILTROS: XPaths SCOPED + fallback GLOBAL ---
    private static final String[] FILTROS_ANY_SCOPED = new String[] {
            PB_FILTERS_PANEL + "//*[(self::label and @for='table-filter-start-date-filter') or @id='table-filter-start-date-filter'][1]",
            PB_FILTERS_PANEL + "//*[(self::label and @for='table-filter-end-date-filter')   or @id='table-filter-end-date-filter'][1]",
            PB_FILTERS_PANEL + "//*[(self::label and (@for='table-filter-player-filter' or normalize-space()='Usuario')) or @id='table-filter-player-filter' or (@type='text' and contains(@placeholder,'Filtrar Usuario'))][1]",
            PB_FILTERS_PANEL + "//*[(self::label and @for='table-filter-movement-type-small-filter') or @id='table-filter-movement-type-small-filter'][1]",
            PB_FILTERS_PANEL + "//*[(self::label and (@for='table-filter-round-filter' or normalize-space()='Ronda')) or @id='table-filter-round-filter'][1]",
            PB_FILTERS_PANEL + "//*[(self::label and (@for='table-filter-move-filter'  or normalize-space()='Jugada')) or @id='table-filter-move-filter'][1]",
            PB_FILTERS_PANEL + "//*[(self::label and @for='table-filter-amount-greater-than-filter') or @id='table-filter-amount-greater-than-filter'][1]",
            PB_FILTERS_PANEL + "//*[(self::label and (@for='table-filter-provider-filter' or normalize-space()='Proveedor')) or @id='table-filter-provider-filter'][1]"
    };

    private static final String[] FILTROS_ANY_GLOBAL = new String[] {
            "((//label[@for='table-filter-start-date-filter']) | (//*[@id='table-filter-start-date-filter']))[1]",
            "((//label[@for='table-filter-end-date-filter'])   | (//*[@id='table-filter-end-date-filter']))[1]",
            "((//label[@for='table-filter-player-filter' or normalize-space()='Usuario']) | (//*[@id='table-filter-player-filter']) | //input[@type='text' and contains(@placeholder,'Filtrar Usuario')])[1]",
            "((//label[@for='table-filter-movement-type-small-filter']) | (//*[@id='table-filter-movement-type-small-filter']))[1]",
            "((//label[@for='table-filter-round-filter' or normalize-space()='Ronda']) | (//*[@id='table-filter-round-filter']))[1]",
            "((//label[@for='table-filter-move-filter'  or normalize-space()='Jugada']) | (//*[@id='table-filter-move-filter']))[1]",
            "((//label[@for='table-filter-amount-greater-than-filter']) | (//*[@id='table-filter-amount-greater-than-filter']))[1]",
            "((//label[@for='table-filter-provider-filter' or normalize-space()='Proveedor']) | (//*[@id='table-filter-provider-filter']))[1]"
    };

    // ===== MÉTODO SIMPLE: validar COLUMNAS de Player Balance =====
    public static Performable verificarColumnasVisiblesPlayerBalance() {
        return Task.where("Verificar columnas visibles en Player Balance",
                actor -> {
                    Target[] columnas = {
                            PB_C_ID, PB_C_FECHA, PB_C_PROVEEDOR, PB_C_TIPO, PB_C_MONTO,
                            PB_C_BALANCE, PB_C_JUGADA, PB_C_RONDA, PB_C_OBSERVACIONES
                    };
                    for (Target columna : columnas) {
                        actor.attemptsTo(
                                Scroll.to(columna),
                                WaitUntil.the(columna, isVisible()).forNoMoreThan(10).seconds()
                        );
                    }
                }
        );
    }

    // XPaths robustos para Jugada (label + input)
    private static final String LBL_JUGADA_XP =
            "(" + PB_FILTERS_PANEL + "//label[@for='table-filter-move-filter' or normalize-space()='Jugada']"
                    + " | //label[normalize-space()='Jugada'])";

    private static final String INP_JUGADA_XP =
            "(//*[@id='table-filter-move-filter']"
                    + " | //*[@id='table-filter-bet-reference-filter']"
                    + " | //input[@type='text' and contains(translate(@placeholder,'JUGADA','jugada'),'jugada')])[1]";

    public static Performable verificarJugadaSiPresente() {
        return Task.where("Verificar filtro 'Jugada' SOLO si existe en DOM",
                actor -> {
                    WebDriver d = BrowseTheWeb.as(actor).getDriver();
                    JavascriptExecutor js = (JavascriptExecutor) d;

                    // 1) ¿Existe el label 'Jugada'?
                    List<WebElement> lbls = d.findElements(By.xpath(LBL_JUGADA_XP));
                    if (lbls.isEmpty()) {
                        System.out.println("[INFO] Jugada no está presente. Posible falta de acceso (p.ej. usuario PROD) o UI condicional. Verificar manualmente si aplica.");
                        return; // NO romper
                    }

                    WebElement lbl = lbls.get(0);

                    // 2) ¿Label visible realmente?
                    boolean lblVisible = Boolean.TRUE.equals(js.executeScript(
                            "const e=arguments[0], s=getComputedStyle(e);"
                                    + "if(s.display==='none'||s.visibility==='hidden'||s.opacity==='0') return false;"
                                    + "const r=e.getBoundingClientRect(); return r.width>0 && r.height>0;", lbl));
                    if (!lblVisible) {
                        System.out.println("[INFO] Label 'Jugada' está en el DOM pero oculto. Verificar manualmente si corresponde por permisos/Movimiento.");
                        return; // NO romper
                    }

                    // 3) Si el label está visible, comprobamos el input asociado
                    List<WebElement> inps = d.findElements(By.xpath(INP_JUGADA_XP));
                    if (inps.isEmpty()) {
                        System.out.println("[INFO] Label 'Jugada' visible pero no se encontró input asociado. Posible restricción de acceso. Verificar manualmente.");
                        return; // NO romper
                    }

                    WebElement inp = inps.get(0);
                    boolean inpVisible = Boolean.TRUE.equals(js.executeScript(
                            "const e=arguments[0], s=getComputedStyle(e);"
                                    + "if(s.display==='none'||s.visibility==='hidden'||s.opacity==='0') return false;"
                                    + "const r=e.getBoundingClientRect(); return r.width>0 && r.height>0;", inp));

                    if (!inpVisible) {
                        System.out.println("[INFO] Label 'Jugada' visible pero el input está oculto. Posible falta de permisos. Verificar manualmente.");
                        return; // NO romper
                    }

                    // 4) Todo OK: llevar a vista
                    js.executeScript("arguments[0].scrollIntoView({block:'center'});", lbl);
                    js.executeScript("arguments[0].scrollIntoView({block:'center'});", inp);
                }
        );
    }

    // --- XPaths robustos para "Monto Mayor Que" (label + input) ---
    private static final String LBL_MONTO_GT_XP =
            "(" + PB_FILTERS_PANEL + "//label[@for='table-filter-amount-greater-than-filter' or contains(normalize-space(),'Monto')]"
                    + " | //label[contains(translate(normalize-space(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'monto mayor que')])";

    private static final String INP_MONTO_GT_XP =
            "(//*[@id='table-filter-amount-greater-than-filter']"
                    + " | //input[@type='text' and contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'monto mayor que')])[1]";

    // --- Check condicional ---
    public static Performable verificarMontoMayorQueSiPresente() {
        return Task.where("Verificar filtro 'Monto Mayor Que' SOLO si existe en DOM",
                actor -> {
                    WebDriver d = BrowseTheWeb.as(actor).getDriver();
                    JavascriptExecutor js = (JavascriptExecutor) d;

                    // 1) ¿Existe el label?
                    List<WebElement> lbls = d.findElements(By.xpath(LBL_MONTO_GT_XP));
                    if (lbls.isEmpty()) {
                        System.out.println("[INFO] 'Monto Mayor Que' no está presente. Posible falta de acceso o UI condicional. Verificar manualmente si aplica.");
                        return; // NO romper
                    }

                    WebElement lbl = lbls.get(0);
                    boolean lblVisible = Boolean.TRUE.equals(js.executeScript(
                            "const e=arguments[0], s=getComputedStyle(e);"
                                    + "if(s.display==='none'||s.visibility==='hidden'||s.opacity==='0') return false;"
                                    + "const r=e.getBoundingClientRect(); return r.width>0 && r.height>0;", lbl));
                    if (!lblVisible) {
                        System.out.println("[INFO] Label 'Monto Mayor Que' está en DOM pero oculto. Verificar manualmente permisos/estado del panel.");
                        return; // NO romper
                    }

                    // 2) Input asociado
                    List<WebElement> inps = d.findElements(By.xpath(INP_MONTO_GT_XP));
                    if (inps.isEmpty()) {
                        System.out.println("[INFO] Label 'Monto Mayor Que' visible pero no se encontró input asociado. Verificar manualmente.");
                        return; // NO romper
                    }

                    WebElement inp = inps.get(0);
                    boolean inpVisible = Boolean.TRUE.equals(js.executeScript(
                            "const e=arguments[0], s=getComputedStyle(e);"
                                    + "if(s.display==='none'||s.visibility==='hidden'||s.opacity==='0') return false;"
                                    + "const r=e.getBoundingClientRect(); return r.width>0 && r.height>0;", inp));
                    if (!inpVisible) {
                        System.out.println("[INFO] Label 'Monto Mayor Que' visible pero el input está oculto. Verificar manualmente.");
                        return; // NO romper
                    }

                    // 3) Scroll estético
                    js.executeScript("arguments[0].scrollIntoView({block:'center'});", lbl);
                    js.executeScript("arguments[0].scrollIntoView({block:'center'});", inp);
                }
        );
    }


    // ===== MÉTODO SIMPLE: validar FILTROS de Player Balance (ANY label|input/select) =====
    // 2) Verifica requeridos con polling duro, y luego opcionales con métodos suaves
    public static Performable verificarFiltrosVisiblesPlayerBalance() {
        return Task.where("Verificar filtros visibles en Player Balance",
                actor -> {
                    WebDriver d = BrowseTheWeb.as(actor).getDriver();
                    JavascriptExecutor js = (JavascriptExecutor) d;

                    // llevar a vista el panel (si existe)
                    List<WebElement> panels = d.findElements(By.xpath(PB_FILTERS_PANEL));
                    if (!panels.isEmpty()) js.executeScript("arguments[0].scrollIntoView({block:'start'});", panels.get(0));

                    // --- Requeridos (duros) ---
                    for (String xp : PB_FILTROS_REQUERIDOS_XPATH) {
                        long end = System.currentTimeMillis() + 8000; // 8s
                        WebElement el = null;
                        while (System.currentTimeMillis() < end) {
                            List<WebElement> els = d.findElements(By.xpath(xp));
                            if (!els.isEmpty()) {
                                WebElement e = els.get(0);
                                Boolean shown = (Boolean) js.executeScript(
                                        "const e=arguments[0], s=getComputedStyle(e);"
                                                + "if(s.display==='none'||s.visibility==='hidden'||s.opacity==='0') return false;"
                                                + "const r=e.getBoundingClientRect(); return r.width>0 && r.height>0;", e);
                                if (Boolean.TRUE.equals(shown)) { el = e; break; }
                            }
                            try { Thread.sleep(150); } catch (InterruptedException ignored) {}
                        }
                        if (el == null) throw new AssertionError("No visible: " + xp);
                        js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
                    }

                    // --- Opcionales (suaves, NO rompen): Jugada y Monto Mayor Que ---
                    actor.attemptsTo(
                            ReportColumnsUI.verificarJugadaSiPresente(),
                            ReportColumnsUI.verificarMontoMayorQueSiPresente()
                    );
                }
        );
    }

    public static final Target PB_INP_USUARIO =
            Target.the("Input Filtrar Usuario")
                    .locatedBy(PB_FILTERS_PANEL + "//input[@placeholder='Filtrar Usuario']");

    public static final Target PB_BTN_FILTRAR =
            Target.the("Botón Filtrar")
                    .locatedBy("//button[normalize-space()[contains(.,'Filtrar')]]");

    // === Método: escribir usuario y seleccionar match del dropdown (o ENTER + Filtrar) ===
    public static Performable filtrarUsuarioPorNombre(String usuario) {
        return Task.where("Filtrar por usuario: " + usuario, actor -> {
            WebDriver d = BrowseTheWeb.as(actor).getDriver();
            JavascriptExecutor js = (JavascriptExecutor) d;

            // 1) Input visible
            actor.attemptsTo(
                    Scroll.to(PB_INP_USUARIO),
                    WaitUntil.the(PB_INP_USUARIO, isVisible()).forNoMoreThan(8).seconds()
            );
            WebElement inp = d.findElement(By.xpath(PB_INP_USUARIO.getCssOrXPathSelector()));

            // 2) Limpiar y escribir
            js.executeScript("arguments[0].value=''; arguments[0].dispatchEvent(new Event('input',{bubbles:true}));", inp);
            inp.sendKeys(usuario);

            // 3) Debounce (mantener)
            try { Thread.sleep(5500); } catch (InterruptedException ignored) {}

            // 4) Buscar y clicar el link exacto del player
            String linkScopedXp = PB_FILTERS_PANEL + "//a[normalize-space()='" + usuario + "']";
            String linkGlobalXp = "//a[normalize-space()='" + usuario + "']";

            List<WebElement> links = d.findElements(By.xpath(linkScopedXp));
            if (links.isEmpty()) {
                links = d.findElements(By.xpath(linkGlobalXp));
            }

            if (links.isEmpty()) {
                throw new AssertionError("No se encontró la sugerencia del usuario: " + usuario);
            }

            WebElement link = links.get(0);
            Boolean vis = (Boolean) js.executeScript(
                    "const e=arguments[0], s=getComputedStyle(e);"
                            + "if(s.display==='none'||s.visibility==='hidden'||s.opacity==='0') return false;"
                            + "const r=e.getBoundingClientRect(); return r.width>0 && r.height>0;", link);

            if (!Boolean.TRUE.equals(vis)) {
                throw new AssertionError("La sugerencia del usuario existe pero no es visible: " + usuario);
            }

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", link);
            link.click();
        });
    }


    // ====================== balance de cajeros ======================

    private static final String OR_ROOT =
            "(//div[contains(@class,'card-block') and contains(@class,'table-border-style')]//div[starts-with(@id,'datatable-')])[last()]";
    private static final String OR_FILTERS_PANEL =
            OR_ROOT + "//div[@x-show='filtersOpen' or contains(@x-show,'filtersOpen')]";
    private static final String OR_THEAD = OR_ROOT + "//table//thead";

    // ---------- LABELS ----------
    public static final Target OR_F_START_DATE_LBL = Target.the("OR filtro Fecha inicio (label)")
            .locatedBy(OR_FILTERS_PANEL + "//label[@for='table-filter-start-date-filter']");
    public static final Target OR_F_END_DATE_LBL = Target.the("OR filtro Fecha fin (label)")
            .locatedBy(OR_FILTERS_PANEL + "//label[@for='table-filter-end-date-filter']");
    public static final Target OR_F_SKIN_LBL = Target.the("OR filtro Skin (label)")
            .locatedBy(OR_FILTERS_PANEL + "//label[@for='table-filter-skin-filter']");
    public static final Target OR_F_PLAYER_LBL = Target.the("OR filtro Usuario (label)")
            .locatedBy(OR_FILTERS_PANEL + "//label[@for='table-filter-player-filter']");
    public static final Target OR_F_CASHIER_LBL = Target.the("OR filtro Cajero (label)")
            .locatedBy(OR_FILTERS_PANEL + "//label[@for='table-filter-cashier-filter']");
    public static final Target OR_F_TRANSACTION_LBL = Target.the("OR filtro Transacción (label)")
            .locatedBy(OR_FILTERS_PANEL + "//label[@for='table-filter-transaction-filter']");
    public static final Target OR_F_MOV_TYPE_LBL = Target.the("OR filtro Tipo de movimiento (label)")
            .locatedBy(OR_FILTERS_PANEL + "//label[@for='table-filter-movement-type-filter']");

    // ---------- INPUTS VISIBLES (no los hidden) ----------
    public static final Target OR_F_START_DATE_INP = Target.the("OR input visible Fecha inicio")
            .locatedBy(OR_FILTERS_PANEL
                    + "//label[@for='table-filter-start-date-filter']"
                    + "/following::div[contains(@class,'input-group')][1]"
                    + "//input[@type='text' and contains(@class,'datetime-flatpickr')]");
    public static final Target OR_F_END_DATE_INP = Target.the("OR input visible Fecha fin")
            .locatedBy(OR_FILTERS_PANEL
                    + "//label[@for='table-filter-end-date-filter']"
                    + "/following::div[contains(@class,'input-group')][1]"
                    + "//input[@type='text' and contains(@class,'datetime-flatpickr')]");
    public static final Target OR_F_SKIN_SEL = Target.the("OR select Skin")
            .locatedBy(OR_FILTERS_PANEL + "//*[@id='table-filter-skin-filter']");
    public static final Target OR_F_PLAYER_INP = Target.the("OR input visible Usuario")
            .locatedBy(OR_FILTERS_PANEL + "//input[@type='text' and contains(@placeholder,'Filtrar Usuario')]");

    // Reemplaza el Target de Cajero por este
    public static final Target OR_F_CASHIER_INP =
            Target.the("OR input/selector visible Cajero")
                    .located(By.xpath(
                            "("
                                    + OR_FILTERS_PANEL + "//*[@id='table-filter-cashier-filter' and (self::input or self::select or self::textarea) and not(@type='hidden')]"
                                    + " | " + OR_FILTERS_PANEL + "//label[@for='table-filter-cashier-filter']/following::div[contains(@class,'input-group')][1]//input[not(@type='hidden')]"
                                    + " | " + OR_FILTERS_PANEL + "//input[@type='text' and ("
                                    + "contains(translate(@placeholder,'ÁÉÍÓÚáéíóú','AEIOUaeiou'),'cajero')"
                                    + " or contains(translate(@placeholder,'ÁÉÍÓÚáéíóú','AEIOUaeiou'),'filtrar cajero')"
                                    + ")]"
                                    + ")[1]"
                    ));


    public static final Target OR_F_TRANSACTION_INP =
            Target.the("OR input/selector Transacción")
                    .located(By.xpath("("
                            // 1) control con id conocido (input/select/textarea) y no hidden
                            + OR_FILTERS_PANEL + "//*[@id='table-filter-transaction-filter' and (self::input or self::select or self::textarea) and not(@type='hidden')]"
                            // 2) primer input/select visible después del label for
                            + " | " + OR_FILTERS_PANEL + "//label[@for='table-filter-transaction-filter']"
                            +        "/following::div[contains(@class,'input-group')][1]//*[self::input or self::select][not(@type='hidden')]"
                            // 3) fallback por placeholder (normalizando acentos)
                            + " | " + OR_FILTERS_PANEL + "//input[@type='text' and contains(translate(@placeholder,'ÁÉÍÓÚáéíóú','AEIOUaeiou'),"
                            +                                      "translate('Transacción','ÁÉÍÓÚáéíóú','AEIOUaeiou'))]"
                            // 4) ultra-fallback: el propio label (por si solo quieres comprobar presencia)
                            + " | " + OR_FILTERS_PANEL + "//label[normalize-space()= 'Transacción']"
                            + ")[1]"));
    public static final Target OR_F_MOV_TYPE_SEL = Target.the("OR select Tipo de movimiento")
            .locatedBy(OR_FILTERS_PANEL + "//*[@id='table-filter-movement-type-filter']");

    // ---------- COLUMNAS ----------
    public static final Target OR_C_FECHA         = Target.the("OR col Fecha").locatedBy(OR_THEAD + "//th[normalize-space()='Fecha']");
    public static final Target OR_C_USUARIO       = Target.the("OR col Usuario").locatedBy(OR_THEAD + "//th[normalize-space()='Usuario']");
    public static final Target OR_C_MONTO         = Target.the("OR col Monto").locatedBy(OR_THEAD + "//th[normalize-space()='Monto']");
    public static final Target OR_C_BALANCE       = Target.the("OR col Balance").locatedBy(OR_THEAD + "//th[normalize-space()='Balance']");
    public static final Target OR_C_TRANSACCION   = Target.the("OR col Transacción").locatedBy(OR_THEAD + "//th[normalize-space()='Transacción']");
    public static final Target OR_C_TIPO_MOV      = Target.the("OR col Tipo de Movimiento").locatedBy(OR_THEAD + "//th[normalize-space()='Tipo de Movimiento']");
    public static final Target OR_C_TIPO_BONO     = Target.the("OR col Tipo de Bono").locatedBy(OR_THEAD + "//th[normalize-space()='Tipo de Bono']");
    public static final Target OR_C_NRO_DOC       = Target.the("OR col Nro. Documento").locatedBy(OR_THEAD + "//th[normalize-space()='Nro. Documento']");
    public static final Target OR_C_CAJERO        = Target.the("OR col Cajero").locatedBy(OR_THEAD + "//th[normalize-space()='Cajero']");
    public static final Target OR_C_OBS           = Target.the("OR col Observaciones").locatedBy(OR_THEAD + "//th[normalize-space()='Observaciones']");

    // ---------- VERIFICACIÓN ESTRUCTURA (labels + inputs visibles + columnas) ----------
    public static Performable verificarFiltrosYColumnasOtroReporte() {
        return Task.where("Verificar filtros (labels+inputs visibles) y columnas del otro reporte", actor -> {
            WebDriver d = BrowseTheWeb.as(actor).getDriver();
            List<WebElement> panels = d.findElements(By.xpath(OR_FILTERS_PANEL));
            if (!panels.isEmpty()) ((JavascriptExecutor)d).executeScript(
                    "arguments[0].scrollIntoView({block:'start'});", panels.get(0));

            Target[] labels = {
                    OR_F_START_DATE_LBL, OR_F_END_DATE_LBL, OR_F_SKIN_LBL,
                    OR_F_PLAYER_LBL, OR_F_CASHIER_LBL, OR_F_TRANSACTION_LBL, OR_F_MOV_TYPE_LBL
            };
            for (Target t : labels) {
                actor.attemptsTo(Scroll.to(t), WaitUntil.the(t, isVisible()).forNoMoreThan(10).seconds());
            }

            Target[] inputs = {
                    OR_F_START_DATE_INP, OR_F_END_DATE_INP, OR_F_SKIN_SEL,
                    OR_F_PLAYER_INP, OR_F_CASHIER_INP, OR_F_TRANSACTION_INP, OR_F_MOV_TYPE_SEL
            };
            for (Target t : inputs) {
                actor.attemptsTo(Scroll.to(t), WaitUntil.the(t, isVisible()).forNoMoreThan(10).seconds());
            }

            Target[] columnas = {
                    OR_C_FECHA, OR_C_USUARIO, OR_C_MONTO, OR_C_BALANCE,
                    OR_C_TRANSACCION, OR_C_TIPO_MOV, OR_C_TIPO_BONO,
                    OR_C_NRO_DOC, OR_C_CAJERO, OR_C_OBS
            };
            for (Target th : columnas) {
                actor.attemptsTo(Scroll.to(th), WaitUntil.the(th, isVisible()).forNoMoreThan(10).seconds());
            }
        });
    }
    // ---------- ESCRIBIR & CLICAR Usuario ----------
    public static Performable escribirYSeleccionarUsuario(String usuario) {
        return Task.where("Escribir y seleccionar Usuario: " + usuario, actor -> {
            WebDriver d = BrowseTheWeb.as(actor).getDriver();
            JavascriptExecutor js = (JavascriptExecutor) d;

            actor.attemptsTo(
                    Scroll.to(OR_F_PLAYER_INP),
                    WaitUntil.the(OR_F_PLAYER_INP, isVisible()).forNoMoreThan(8).seconds()
            );
            WebElement inp = d.findElement(By.xpath(OR_F_PLAYER_INP.getCssOrXPathSelector()));
            js.executeScript("arguments[0].value=''; arguments[0].dispatchEvent(new Event('input',{bubbles:true}));", inp);
            inp.sendKeys(usuario);

            try { Thread.sleep(5500); } catch (InterruptedException ignored) {}

            String linkScopedXp = OR_FILTERS_PANEL + "//a[normalize-space()='" + usuario + "']";
            String linkGlobalXp = "//a[normalize-space()='" + usuario + "']";
            List<WebElement> links = d.findElements(By.xpath(linkScopedXp));
            if (links.isEmpty()) links = d.findElements(By.xpath(linkGlobalXp));
            if (links.isEmpty()) throw new AssertionError("No se encontró sugerencia para Usuario: " + usuario);

            WebElement link = links.get(0);
            ((JavascriptExecutor)d).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
            link.click();
        });
    }

    // ---------- ESCRIBIR & CLICAR Cajero ----------
    public static Performable escribirUsuarioOtroReporte(String usuario) {
        return Task.where("Escribir Usuario: " + usuario, actor -> {
            WebDriver d = BrowseTheWeb.as(actor).getDriver();
            JavascriptExecutor js = (JavascriptExecutor) d;

            actor.attemptsTo(
                    Scroll.to(OR_F_PLAYER_INP),
                    WaitUntil.the(OR_F_PLAYER_INP, isVisible()).forNoMoreThan(8).seconds()
            );

            WebElement inp = d.findElement(By.xpath(OR_F_PLAYER_INP.getCssOrXPathSelector()));
            js.executeScript(
                    "arguments[0].value=''; arguments[0].dispatchEvent(new Event('input',{bubbles:true}));", inp
            );
            inp.sendKeys(usuario);

            try { Thread.sleep(5500); } catch (InterruptedException ignored) {}
        });
    }

    // Solo escribir en "Cajero"
    public static Performable escribirCajeroOtroReporte(String cajero) {
        return Task.where("Escribir Cajero: " + cajero, actor -> {
            WebDriver d = BrowseTheWeb.as(actor).getDriver();
            JavascriptExecutor js = (JavascriptExecutor) d;

            actor.attemptsTo(
                    Scroll.to(OR_F_CASHIER_INP),
                    WaitUntil.the(OR_F_CASHIER_INP, isVisible()).forNoMoreThan(8).seconds()
            );

            WebElement inp = d.findElement(By.xpath(OR_F_CASHIER_INP.getCssOrXPathSelector()));
            js.executeScript(
                    "arguments[0].value=''; arguments[0].dispatchEvent(new Event('input',{bubbles:true}));", inp
            );
            inp.sendKeys(cajero);

            try { Thread.sleep(5500); } catch (InterruptedException ignored) {}
        });
    }





}