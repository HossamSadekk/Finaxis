package presentation.home

import core.base.viewmodel.BaseViewModel
import domain.usecase.apiUseCases.GetAccountDetailsUseCase

class HomeScreenViewModel(private val getAccountDetailsUseCase: GetAccountDetailsUseCase) : BaseViewModel() {
}