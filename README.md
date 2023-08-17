# Patrón de Arquitectura MVI en Android

## Descripción

Este repositorio contiene una aplicación de ejemplo que implementa el patrón de arquitectura MVI (Model-View-Intent) en el contexto de aplicaciones Android. El propósito de esta aplicación es ilustrar cómo se puede estructurar el código utilizando el patrón MVI para lograr una separación clara entre la lógica de negocio y la interfaz de usuario.

## Patrón de Arquitectura MVI

MVI es un patrón de arquitectura que busca mejorar la modularidad, la reactividad y el mantenimiento del código en aplicaciones Android. Algunas características clave de MVI son:

- Unidireccionalidad de datos.
- Estado inmutable.
- Reactividad en la actualización de la interfaz de usuario.
- Separación de responsabilidades entre Model, View y ViewModel.

## Estructura de la Aplicación

La aplicación de ejemplo consta de los siguientes componentes:

- **Model:** Gestiona la lógica de negocio y obtiene los datos.
- **View:** Representa la interfaz de usuario y muestra el estado actual.
- **ViewModel:** Actúa como intermediario, maneja las intenciones del usuario y actualiza el estado.

## Ejecutar la Aplicación

1. Clona este repositorio en tu máquina local.
2. Abre el proyecto en Android Studio.
3. Navega a la carpeta del módulo `app`.
4. Ejecuta la aplicación en un emulador o dispositivo Android.

## Uso

Explora cómo se ha implementado el patrón MVI en esta aplicación de ejemplo. Observa cómo fluyen los datos y cómo las intenciones del usuario se traducen en cambios en la interfaz de usuario y el estado.
