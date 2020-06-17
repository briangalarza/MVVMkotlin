package com.briangalarza.mvvmkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.briangalarza.mvvmkotlin.R
import com.briangalarza.mvvmkotlin.viewModel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Esta variable se instanciara al momento del onCreate
    lateinit var viewModel: ListViewModel
    //Pasamos un array vacío al adapter
    private val countriesAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //viewModel instanciado
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        //Llammamos al metodo encapsulado
        viewModel.refresh()


        //Declaramos el adapter al recycler view
        countriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }
        observeViewModel()

    }

    /**
     * LLama a las variables del view Model, observer
     */
    fun observeViewModel(){

        //Observables

        //Observer que actualiza la información de los paises
        viewModel.countries.observe(this, Observer { countries ->
            //Si countries no es vacio hacemos que actualice el adapter de countries con el valor que le corresponde a countries
            countries?.let{ countriesAdapter.updateCountries(it) }
        })

        //Observer que carga el mensaje de error en caso de generarse
        viewModel.countryLoadError.observe(this, Observer { isError ->
            isError?.let{list_error.visibility = if(it) View.VISIBLE else View.GONE}
        })

        //Observer que carga muestra la animacion de carga
        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    list_error.visibility = View.GONE
                    countriesList.visibility = View.GONE
                }
            }
        })

    }



}
