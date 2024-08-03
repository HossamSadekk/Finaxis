package presentation.bank

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.designSystem.components.AppBar
import core.designSystem.components.CustomErrorDialog
import core.designSystem.components.FinaxisButton
import core.network.utils.RequestState.Error
import core.network.utils.RequestState.Idle
import core.network.utils.RequestState.Loading
import core.network.utils.RequestState.Success
import core.sharedPlatform.PlatformColors
import data.model.Bank
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.Res.string
import finaxis.composeapp.generated.resources.kyc_first_step_description
import finaxis.composeapp.generated.resources.kyc_first_step_title
import finaxis.composeapp.generated.resources.poppins_regular
import finaxis.composeapp.generated.resources.poppins_semibold
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.bank.components.BankComponent
import presentation.bank.components.BanksShimmerItem
import presentation.bank.components.SearchSection

@OptIn(KoinExperimentalAPI::class)
@Composable
fun BankSelectionScreen(onBackPressed: () -> Unit, onProceedSuccess: () -> Unit) {
    PlatformColors(
        statusBarColor = MaterialTheme.colorScheme.onBackground,
        navBarColor = MaterialTheme.colorScheme.onBackground
    )

    val viewModel = koinViewModel<BankSelectionViewModel>()
    val banksState by viewModel.banks.collectAsState()
    val searchState by viewModel.searchState.collectAsState()
    val submitSelectedBank by viewModel.submitSelectBanks.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Scaffold(
        topBar = { AppBar(modifier = Modifier.fillMaxWidth()) { onBackPressed() } }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(resource = string.kyc_first_step_title),
                    style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily(Font(font.poppins_semibold))),
                    color = MaterialTheme.colorScheme.inversePrimary,
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = stringResource(string.kyc_first_step_description),
                    style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(font.poppins_regular))),
                    color = MaterialTheme.colorScheme.inversePrimary
                )
                Spacer(modifier = Modifier.height(15.dp))
                SearchSection(modifier = Modifier.fillMaxWidth(),
                    state = searchState,
                    onSearch = { newSearchState ->
                        viewModel.searchBanks(newSearchState)
                    })
                Spacer(modifier = Modifier.height(15.dp))

                when (banksState) {
                    Idle -> {
                        Unit
                    }

                    is Loading -> {
                        BanksLoadingState()
                    }

                    is Error -> {
                        val error = (banksState as Error).message
                        errorMessage = error
                        showDialog = true
                    }

                    is Success -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(3.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(searchState.filteredBanks) { bank ->
                                BankComponent(
                                    bank = bank,
                                    onBankSelected = { selectedBank ->
                                        viewModel.selectBank(selectedBank)
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }
                if (showDialog) {
                    CustomErrorDialog(
                        title = "Error",
                        message = errorMessage,
                        onDismiss = {
                            showDialog = false
                            viewModel.resetErrorState()
                        }
                    )
                }

            }
            // button
            ButtonSection(
                onButtonClick = { viewModel.submitSelectedBank() },
                selectedBank = viewModel.selectedBank.value,
                isLoading = submitSelectedBank.isLoading()
            )
            if (submitSelectedBank.isSuccess()) {
                /** Navigate to Next Screen **/
                onProceedSuccess()
                viewModel.resetSubmitSelectedBank()
            }
        }
    }
}

@Composable
fun BanksLoadingState() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(3.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(10) { // Number of shimmer placeholder items
            BanksShimmerItem()
        }
    }
}

@Composable
fun ButtonSection(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
    selectedBank: Bank?,
    isLoading: Boolean,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        Divider(color = MaterialTheme.colorScheme.inverseSurface)

        Box(
            modifier = modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 30.dp
                )
        ) {
            FinaxisButton(
                onClick = { onButtonClick() },
                buttonText = "Proceed",
                isClickable = selectedBank != null,
                isLoading = isLoading
            )
        }
    }
}
