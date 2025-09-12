package com.tuorg.ui;

import net.serenitybdd.screenplay.targets.Target;

public class ListPlayersUI {

    // Menú
    public static final Target MENU_REPORTES = Target.the("sección Reportes")
            .locatedBy("//nav//*[self::a or self::span or self::div][normalize-space()='Reportes']");
    public static final Target MENU_LISTA_JUGADORES = Target.the("opción Lista de Jugadores")
            .locatedBy("//body/div[@id='pcoded']/div[@class='pcoded-container navbar-wrapper']/div[@class='pcoded-main-container']/div[@class='pcoded-wrapper']/nav[@class='pcoded-navbar']/div[@class='nav-list']/div[@class='slimScrollDiv']/div[@class='pcoded-inner-navbar main-menu']/ul[@class='pcoded-item pcoded-left-item']/li[2]/a[1]");

    // Breadcrumb / título del reporte
    public static final Target BREADCRUMB_ACTIVE = Target.the("breadcrumb activo")
            .locatedBy("//li[@class='breadcrumb-item active']");

    // Filtros
    public static final Target FILTER_USERNAME = Target.the("Usuario")
            .locatedBy("//input[@id='table-filter-user-name-filter']");
    public static final Target FILTER_IDUSER = Target.the("Id usuario")
            .locatedBy("//input[@id='table-filter-id-filter']");
    public static final Target FILTER_NAME = Target.the("Nombre")
            .locatedBy("//input[@id='table-filter-name-filter']");
    public static final Target FILTER_LASTNAME = Target.the("Apellido")
            .locatedBy("//input[@id='table-filter-last-name-filter']");
    public static final Target FILTER_DOCUMENT_NUMBER = Target.the("Nro. Documento")
            .locatedBy("//input[@id='table-filter-document-number-filter']");
    public static final Target FILTER_EMAIL = Target.the("Email")
            .locatedBy("//input[@id='table-filter-email-filter']");
    public static final Target FILTER_PHONE = Target.the("Teléfono")
            .locatedBy("//input[@id='table-filter-phone-filter']");
    public static final Target FILTER_IP = Target.the("IP")
            .locatedBy("//input[@id='table-filter-ip-filter']");
    public static final Target FILTER_STATUS = Target.the("Estado")
            .locatedBy("//select[@id='table-filter-status-filter']");
    public static final Target FILTER_SKIN = Target.the("Skin")
            .locatedBy("//select[@id='table-filter-skin-filter']");
    public static final Target FILTER_AFFILIATE = Target.the("Afiliado")
            .locatedBy("//input[@id='table-filter-affiliate-filter']");
    public static final Target FILTER_SELLER = Target.the("Vendedor")
            .locatedBy("//input[@id='table-filter-seller-filter']");
    public static final Target FILTER_BALANCE_GT = Target.the("Balance mayor que")
            .locatedBy("//input[@id='table-filter-balance-greater-than-filter']");
    public static final Target BTN_FILTER = Target.the("Filtrar")
            .locatedBy("//i[@class='fa fa-filter']");
    public static final Target BTN_COLUMNS = Target.the("Seleccionar columnas")
            .locatedBy("//button[@id='columnSelect-table']");

    // Columnas
    public static final Target COL_IDUSER = Target.the("columna Id usuario")
            .locatedBy("//th[normalize-space()='Id usuario']");
    public static final Target COL_USER = Target.the("columna Usuario")
            .locatedBy("//th[normalize-space()='Usuario']");
    public static final Target COL_BALANCE = Target.the("columna Balance")
            .locatedBy("//th[normalize-space()='Balance']");
    public static final Target COL_BALANCE_ACTIONS = Target.the("columna Acciones de balance")
            .locatedBy("//th[normalize-space()='Acciones de balance']");
    public static final Target COL_EMAIL = Target.the("columna Correo")
            .locatedBy("//th[normalize-space()='Correo']");
    public static final Target COL_DOCUMENT = Target.the("columna Nro. Documento")
            .locatedBy("//th[normalize-space()='Nro. Documento']");
    public static final Target COL_NAME = Target.the("columna Nombre")
            .locatedBy("//th[normalize-space()='Nombre']");
    public static final Target COL_LASTNAME = Target.the("columna Apellido")
            .locatedBy("//th[normalize-space()='Apellido']");
    public static final Target COL_PHONE = Target.the("columna Teléfono")
            .locatedBy("//th[normalize-space()='Teléfono']");
    public static final Target COL_SKIN = Target.the("columna Skin")
            .locatedBy("//th[normalize-space()='Skin']");
    public static final Target COL_IP = Target.the("columna Dirección IP")
            .locatedBy("//th[normalize-space()='Dirección IP']");
    public static final Target COL_STATUS = Target.the("columna Estado")
            .locatedBy("//th[normalize-space()='Estado']");
    public static final Target COL_OBS = Target.the("columna Observación")
            .locatedBy("//th[normalize-space()='Observación']");
    public static final Target COL_AFFILIATE = Target.the("columna Afiliado")
            .locatedBy("//th[normalize-space()='Afiliado']");
    public static final Target COL_SELLER = Target.the("columna Vendedor")
            .locatedBy("//th[normalize-space()='Vendedor']");
}

