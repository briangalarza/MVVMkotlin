package com.briangalarza.mvvmkotlin.dependencyInjection

import com.briangalarza.mvvmkotlin.model.CountriesService
import com.briangalarza.mvvmkotlin.viewModel.ListViewModel
import dagger.Component

@Component(modules=[ApiModule::class])
interface ApiComponent {
    fun inject(service: CountriesService)

    fun inject(viewModel: ListViewModel)
}