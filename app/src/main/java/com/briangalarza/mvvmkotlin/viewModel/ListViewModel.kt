package com.briangalarza.mvvmkotlin.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.briangalarza.mvvmkotlin.model.Country

class ListViewModel: ViewModel() {


    //Generamos la variable de tipo LiveData para que los suscriptores reciban las actualizaciones
    val countries = MutableLiveData<List<Country>>()
    //Variable de error
    val countryLoadError = MutableLiveData<Boolean>()
    //Variable de carga
    val loading = MutableLiveData<Boolean>()


    //Metodo que invoca a la busqueda, ocultando la funcionalidad
    fun refresh(){
        fetchCountries()
    }


    //Metodo que hace la busqueda de paises
    private fun fetchCountries(){
        //Prueba de datos genericos
        val mockData = listOf(Country("CountryA"),
            Country("CountryB"),
            Country("CountryC"),
            Country("CountryD"),
            Country("CountryE"),
            Country("CountryF"),
            Country("CountryG"),
            Country("CountryH"),
            Country("CountryI"),
            Country("CountryJ")
            )

        //Actualizamos el listado de paises obtenidos e indicamos que finalizo la carga y que no hubo errores
        countryLoadError.value = false
        loading.value = false
        countries.value = mockData
    }
}