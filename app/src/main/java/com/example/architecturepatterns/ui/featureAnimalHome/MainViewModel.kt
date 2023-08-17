package com.example.architecturepatterns.ui.featureAnimalHome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturepatterns.data.server.repository.AnimalRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: AnimalRepository): ViewModel() {


    /**
     * (Channel) que se utilizará para enviar intenciones del usuario al ViewModel.
     * La propiedad es de solo lectura y se configura con un canal que almacena objetos del tipo MainIntent y tiene un tamaño de búfer ilimitado.
     */

    val userIntent = Channel<MainIntent> { Channel.UNLIMITED }


    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state : StateFlow<MainState>
        get() = _state


    /**
     * La función handleIntent() se llama en el bloque de inicialización (init). Esto se hace para asegurarse de que el flujo de intenciones del usuario esté siendo
     * manejado tan pronto como se crea la instancia del ViewModel.
     */

    init {
        handleIntent()
    }

    /**
     * Función handleIntent
     * Este fragmento de código utiliza el Kotlin Coroutines Flow para manejar las intenciones (acciones del usuario) en un patrón MVI (Model-View-Intent)
     *
     * 1.- viewModelScope.launch: viewModelScope es un alcance de coroutine asociado al ciclo de vida del ViewModel. launch es una función para iniciar una
     * nueva coroutine dentro de este alcance.

     * 2.- userIntent.consumeAsFlow().collect: userIntent es un canal (Channel) que se utiliza para enviar las intenciones del usuario al ViewModel.
     * consumeAsFlow() convierte este canal en un flujo (Flow) que puedes recolectar (observar). La función collect es utilizada para recolectar elementos emitidos por el flujo.

     * 3.- when(collector): Esto inicia una estructura de control when basada en el valor del objeto collector que se ha recolectado del flujo.

     * 4.- is MainIntent.FetchAnimals: Esta línea verifica si el valor recolectado es una instancia de MainIntent.FetchAnimals.
     * MainIntent parece ser una clase sellada (sealed class) que define diferentes tipos de intenciones.

     * 5.- fetchAnimals(): Si la intención recolectada es del tipo MainIntent.FetchAnimals, se llama a la función fetchAnimals().
     * Esto indica que se debe realizar una acción para recuperar una lista de animales (probablemente del modelo) y actualizar
     * el estado de la interfaz de usuario correspondiente.
     */

    private fun handleIntent(){
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect{ collector ->
                when(collector){
                    is MainIntent.FetchAnimals -> fetchAnimals()
                }
            }
        }
    }


    /**
     * Función fetchAnimals
     * 1.- _state.value = MainState.Loading: En esta línea, se actualiza el estado del ViewModel para indicar que la carga de datos está en curso. MainState.Loading probablemente
     * sea un estado del sellado MainState que indica que los datos se están cargando.

     * 2.- try { ... } catch (e: Exception) { ... }: Aquí se envuelve el código en un bloque try para manejar posibles excepciones que puedan ocurrir durante la obtención de los datos.

     * 3.- _state.value = try { MainState.Animals(repository.getAnimals()) }: En esta línea, se intenta obtener la lista de animales utilizando el método getAnimals del repositorio.
     * Si la obtención de los datos tiene éxito, se crea un nuevo estado MainState.Animals con la lista de animales como argumento y se actualiza el estado del ViewModel.

     * 4.- MainState.Error(e.localizedMessage): Si ocurre una excepción durante la obtención de los datos, se crea un nuevo estado MainState.Error con el mensaje de error localizado
     * y se actualiza el estado del ViewModel.
     */

    private fun fetchAnimals(){
        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try {
                MainState.Animals(repository.getAnimals())
            } catch (e:Exception){
                MainState.Error(e.localizedMessage)
            }
        }
    }
}