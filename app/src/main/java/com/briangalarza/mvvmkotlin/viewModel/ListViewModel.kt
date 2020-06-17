package com.briangalarza.mvvmkotlin.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.briangalarza.mvvmkotlin.dependencyInjection.DaggerApiComponent
import com.briangalarza.mvvmkotlin.model.CountriesService
import com.briangalarza.mvvmkotlin.model.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel: ViewModel() {

    //Servicio de retrofit con dagger

    @Inject
    lateinit var  countriesService: CountriesService

    init{
        DaggerApiComponent.create().inject(this)
    }

    //RxJava
    private val disposable = CompositeDisposable()

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
        //Cargamos true
        loading.value = true

        //Usamos el disposable para subcribirnos al servicio para obtener la información
        disposable.add(
            countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Country>>(){

                    // En caso de que funcione
                    override fun onSuccess(value: List<Country>?) {
                        countries.value = value
                        countryLoadError.value = false
                        loading.value = false
                    }

                    // En caso de error
                    override fun onError(e: Throwable?) {
                        countryLoadError.value = true
                        loading.value = false
                    }

                })
        )




       /*
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

        */
    }
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}