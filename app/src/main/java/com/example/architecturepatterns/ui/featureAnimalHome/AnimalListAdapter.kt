package com.example.architecturepatterns.ui.featureAnimalHome


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.architecturepatterns.data.model.Animal
import com.example.architecturepatterns.data.server.AnimalService
import com.example.architecturepatterns.databinding.AnimalItemBinding

class AnimalListAdapter(private val animals: ArrayList<Animal>) :
    RecyclerView.Adapter<AnimalListAdapter.DataViewHolder>() {

    private lateinit var layoutInflater: LayoutInflater

    fun newAnimals(newAnimals: List<Animal>) {
        animals.clear()
        animals.addAll(newAnimals)
        notifyDataSetChanged()
    }

    class DataViewHolder(private val itemViewAnimal: AnimalItemBinding) : RecyclerView.ViewHolder(itemViewAnimal.root) {
        fun bind(animal: Animal) = with(itemViewAnimal) {
            animalName.text = animal.name
            animalLocation.text = animal.location
            val url = AnimalService.BASE_URL + animal.image
            Glide.with(animalImage.context)
                .load(url)
                .into(animalImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)
        val binding: AnimalItemBinding = AnimalItemBinding.inflate(
            layoutInflater,
            parent, false
        )
        return DataViewHolder(binding)
    }


    override fun getItemCount() = animals.size


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(animals[position])
    }
}