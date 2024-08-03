package presentation.Bank

import core.base.viewmodel.BaseViewModel
import core.designSystem.helper.SearchState
import core.network.utils.RequestState
import core.network.utils.RequestState.Idle
import data.model.Bank
import data.model.BankResponse
import domain.usecase.apiUseCases.GetBanksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class BankSelectionViewModel(private val getBanksUseCase: GetBanksUseCase) : BaseViewModel() {
    /** bank state **/
    private val _banks = MutableStateFlow<RequestState<BankResponse>>(Idle)
    val banks: StateFlow<RequestState<BankResponse>> get() = _banks

    /** selected bank state **/
    private val _selectedBank = MutableStateFlow<Bank?>(null)
    val selectedBank: StateFlow<Bank?> get() = _selectedBank

    /** Search state **/
    private val _searchState = MutableStateFlow(SearchState())
    val searchState: StateFlow<SearchState> get() = _searchState

    init {
        getBanks()
    }

    private fun getBanks() {
        executeUseCase(block = { getBanksUseCase.execute() }, onStart = {
            _banks.update { RequestState.Loading }
        }, onError = { error ->
            _banks.update { RequestState.Error(error) }
        }, onSuccess = { response ->
            _banks.update { RequestState.Success(response) }
            _searchState.update { it.copy(filteredBanks = response.banks) }
        })
    }

    fun selectBank(selectedBank: Bank) {
        _banks.value.let { state ->
            if (state is RequestState.Success) {
                val updatedBanks = state.data.banks.map { bank ->
                    if (bank.id == selectedBank.id) {
                        bank.copy(isSelected = !bank.isSelected) // Toggle selection
                    } else {
                        bank.copy(isSelected = false) // Deselect other banks
                    }
                }
                _banks.update { RequestState.Success(BankResponse(updatedBanks)) }
                _searchState.update { it.copy(filteredBanks = updatedBanks) }
                _selectedBank.value = if (updatedBanks.any { it.isSelected }) {
                    updatedBanks.find { it.isSelected }
                } else {
                    null
                }
            }
        }
    }

    fun searchBanks(query: String) {
        _searchState.update {
            val filteredBanks = _banks.value.getSuccessData().banks.filter { bank ->
                bank.name.contains(query, ignoreCase = true)
            }

            it.copy(
                query = query,
                filteredBanks = filteredBanks
            )
        }
    }

    fun resetErrorState() {
        _banks.value = Idle
    }
}

