package com.briangalarza.mvvmkotlin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.briangalarza.mvvmkotlin.R
import com.briangalarza.mvvmkotlin.model.Country
import com.briangalarza.mvvmkotlin.util.getProgressDrawable
import com.briangalarza.mvvmkotlin.util.loadImage
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter (var countries:ArrayList<Country>):RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {


    /**
     * Metodo para actualizar el listado de elementos en el recycler
     *
    */
    fun updateCountries(newCountries:List<Country>){
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    //Mandamos nuestro formato de elementos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_country,parent,false))

    //Obtenemos la cantidad de elementos
    override fun getItemCount() = countries.size

    //Metodo para insertar en el recycler los distintos elementos
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])

    }


    //Colocamos el valor en el elemento name
    class CountryViewHolder(view: View):RecyclerView.ViewHolder(view){

        private val imageView = view.imageView
        private val countryName = view.name
        private val countryCapital = view.capital
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(country: Country){
            countryName.text = country.countryName
            countryCapital.text = country.capital
            imageView.loadImage(country.flag,progressDrawable)
        }



    }


}