Feature: Prueba de login

  Scenario: Inicio de sesion exitoso
    Given ingreso a la pagina de login
    When ingreso con el usuario de entorno y la contraseña de entorno
    Then deberia acceder exitosamente

  Scenario: Inicio de sesion fallido
    Given ingreso a la pagina de login
    When ingreso con el usuario de entorno y la contraseña fallida de entorno
    Then deberia mostrar un mensaje de error

  Scenario: Inicio de sesion fallido por campos vacios
    Given ingreso a la pagina de login
    When intento iniciar sesion sin ingresar usuario ni contraseña
    Then deberia mostrar un mensaje de error indicando que los campos son obligatorios

  Scenario: Inicio de sesion fallido por usuario incorrecto
    Given ingreso a la pagina de login
    When ingreso con el usuario incorrecto de entorno y la contraseña fallida de entorno
    Then deberia mostrar un mensaje de error por usuario incorrecto
