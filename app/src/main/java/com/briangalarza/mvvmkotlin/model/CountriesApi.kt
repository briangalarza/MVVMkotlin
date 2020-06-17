package com.briangalarza.mvvmkotlin.model

import io.reactivex.Single
import retrofit2.http.GET


//Interface para trabajar con retrofit
interface CountriesApi {
    //Obtenemos por medio de GET
    //Endpoint ofrecido por el curso para obtener el listado de paises
    @GET ("DevTides/countries/master/countriesV2.json")
    fun getCountries():Single<List<Country>>




}