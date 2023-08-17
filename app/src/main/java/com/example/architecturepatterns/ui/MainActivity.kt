package com.example.architecturepatterns.ui


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturepatterns.data.server.AnimalService
import com.example.architecturepatterns.databinding.ActivityMainBinding
import com.example.architecturepatterns.ui.featureAnimalHome.AnimalListAdapter
import com.example.architecturepatterns.ui.featureAnimalHome.MainIntent
import com.example.architecturepatterns.ui.featureAnimalHome.MainState
import com.example.architecturepatterns.ui.featureAnimalHome.MainViewModel
import com.example.architecturepatterns.ui.featureAnimalHome.MainViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private var adapter = AnimalListAdapter(arrayListOf())
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObservables()
    }

    /**
     *Como paso uno, haremos clic en "buttonFetch", que ejecutará una intención.
     *Como @param [MainIntent.FetchAnimals] tendremos que enviar la Clase que generamos para la intención
     *y acceder a la intención, que en este caso es FetchAnimals
     */
     private fun setupUI() {
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(AnimalService.api)
        )[MainViewModel::class.java]
        binding.apply {
            animalRecycler.layoutManager = LinearLayoutManager(this@MainActivity)
            animalRecycler.run {
                addItemDecoration(
                    DividerItemDecoration(
                        animalRecycler.context,
                        (animalRecycler.layoutManager as LinearLayoutManager).orientation
                    )
                )
            }

            animalRecycler.adapter = adapter
            buttonFetch.setOnClickListener {
                lifecycleScope.launch {
                    mainViewModel.userIntent.send(MainIntent.FetchAnimals)
                }
            }
        }

    }

    /**
     * Aqui se encarga de observar segun el estado recolectado realizar ciertos cambios en la vista del usuario,
     * esto dependiendo del estado en el que se encuentre.
     */

    private fun setupObservables() {
        lifecycleScope.launch {
            mainViewModel.state.collect { collector ->
                when (collector) {
                    is MainState.Idle -> {}

                    is MainState.Loading -> {
                        binding.apply {
                            buttonFetch.visibility = View.GONE
                            progressBar.visibility = View.VISIBLE
                        }
                    }

                    is MainState.Animals -> {
                        binding.apply {
                            progressBar.visibility = View.GONE
                            buttonFetch.visibility = View.GONE
                            animalRecycler.visibility = View.VISIBLE
                            collector.animals.let {
                                adapter.newAnimals(it)
                            }
                        }
                    }

                    is MainState.Error -> {
                        binding.apply {
                            progressBar.visibility = View.GONE
                            buttonFetch.visibility = View.GONE
                            Toast.makeText(this@MainActivity, collector.error, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }

    }
}

