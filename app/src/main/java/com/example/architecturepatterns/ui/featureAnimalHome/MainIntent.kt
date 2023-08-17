package com.example.architecturepatterns.ui.featureAnimalHome


/**
 * La razón detrás de usar una clase sellada para representar intenciones es que puedes definir diferentes tipos de intenciones
 * como subclases de MainIntent. Esto proporciona una estructura clara para manejar y distinguir las diferentes acciones que un
 * usuario puede realizar en tu aplicación. Cada subclase podría tener información adicional si es necesario (por ejemplo, parámetros para una acción específica).
 */

sealed class MainIntent {
    object FetchAnimals : MainIntent()
}