package com.briangalarza.mvvmkotlin.model

import com.briangalarza.mvvmkotlin.dependencyInjection.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject


//Se encarga de obtener la información del backend por medio de retrofit
class CountriesService {

    @Inject
    lateinit var  api:CountriesApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    //Obtenemos los paises
    fun getCountries(): Single<List<Country>>{
        return api.getCountries()
    }
}