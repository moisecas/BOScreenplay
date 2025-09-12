@Reportes
Feature: Ir a lista de jugadores
  In order to monitor and manage players on the platform
  As an authenticated user
  I want to view the players list report

  # lista de jugadores, reporte
  Background: Usuario autenticado
    Given ingreso a la pagina de login
    When ingreso con el usuario de entorno y la contraseña de entorno
    Then deberia acceder exitosamente

  Scenario: Visualizar reporte de lista de jugadores
    Given navego a la seccion Reportes
    When selecciono la opcion Lista de Jugadores
    Then deberia visualizar el reporte de lista de jugadores "Reporte Listado de Jugadores"
