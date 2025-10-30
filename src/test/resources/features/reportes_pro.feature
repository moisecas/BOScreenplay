# src/test/resources/features/reportes_pro.feature
@smoke
Feature: Acceso y filtro de reportes (menú por texto)
  Background: Usuario autenticado
    Given ingreso a la pagina de login
    When ingreso con el usuario de entorno y la contraseña de entorno
    Then deberia acceder exitosamente

  Scenario: Estructura base "Lista de Jugadores" con filtro y export
    When navego a la seccion Reportes "Lista de Jugadores"
    Then la URL actual contiene "/reports/player-list"
    And deberia visualizar el reporte "Reporte Listado de Jugadores"
    And al menos estos filtros estan visibles:
      | Usuario          |
      | Id usuario       |
      | Nombre           |
      | Apellido         |
      | Nro. Documento   |
      | Correo           |
      | Teléfono         |
      | IP               |
      | Estado           |
      | Skin             |
      | Afiliado         |
      | Vendedor         |
      | Balance mayor que|
    And aplico el filtro


  Scenario: Estructura base "Balance de Jugadores" con filtro y export
    When navego a la seccion Reportes "Transacciones Jugador"
    Then la URL actual contiene "/reports/player-balance"
    And deberia visualizar el reporte "Reporte de Balance de Jugador"
    And al menos estas columnas estan visibles en player balance:
      | columna       |
      | Id            |
      | Fecha         |
      | Proveedor     |
      | Tipo          |
      | Monto         |
      | Balance       |
      | Jugada        |
      | Ronda         |
      | Observaciones |
    And al menos estos filtros estan visibles en player balance:
      | filtro               |
      | Fecha de Inicio      |
      | Fecha de Finalización|
      | Usuario              |
      | Movimiento           |
      | Ronda                |
      | Jugada               |
      | Monto Mayor Que      |
      | Proveedor            |
    And filtro por usuario "TEST_CHRISTIAN"
    When establezco fecha inicio "2025-10-01" y fecha fin "2025-10-15" y aplico el filtro
    And descargo el reporte 1 veces

  Scenario: Estructura base "Reporte Balance de Cajeros" con filtro y export
    When navego a la seccion Reportes "Balance de Cajeros"
    Then la URL actual contiene "/reports/balance-skin"
    And deberia visualizar el reporte "Reporte Balance de Cajeros"
    And valido estructura del otro reporte
    And filtro usuario en otro reporte "TEST_CHRISTIAN"
    And filtro cajero en otro reporte "CARGA_AUTOMATICA"
    When establezco fecha inicio "2025-10-01" y fecha fin "2025-10-15" y aplico el filtro
    And descargo el reporte 1 veces

