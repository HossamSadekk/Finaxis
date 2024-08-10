package presentation.username_money_request

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.designSystem.components.AppBar
import core.designSystem.components.CustomErrorDialog
import core.designSystem.components.FinaxisButton
import core.designSystem.components.NoteSection
import core.designSystem.components.UsernameSection
import core.designSystem.helper.NoteState
import core.designSystem.helper.UsernameState
import core.extensions.isValidKycUsername
import core.network.utils.RequestState
import core.sharedPlatform.PlatformColors
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.poppins_semibold
import org.jetbrains.compose.resources.Font
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.home.nav.Request

@OptIn(KoinExperimentalAPI::class)
@Composable
fun RequestScreen(request: Request, onBackPressed: () -> Unit, onProceed: (Request, String, String) -> Unit) {
    PlatformColors(
        statusBarColor = MaterialTheme.colorScheme.onBackground,
        navBarColor = MaterialTheme.colorScheme.onBackground
    )
    var usernameState by remember { mutableStateOf(UsernameState()) }
    var noteState by remember { mutableStateOf(NoteState()) }
    val viewModel = koinViewModel<RequestScreenViewModel>()
    val isUserExist by viewModel.isUserExist.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Scaffold(
        topBar = { AppBar(modifier = Modifier.fillMaxWidth()) { onBackPressed() } }
    ) { paddingValues ->
        Column(
            modifier =
            Modifier.padding(paddingValues).fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 10.dp
                    )
                    .weight(1f)
            ) {
                Text(
                    text = "Please Add Username To Proceed",
                    style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily(Font(font.poppins_semibold))),
                    color = MaterialTheme.colorScheme.inversePrimary,
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.height(15.dp))
                UsernameSection(
                    usernameState = usernameState,
                    onUsernameChange = { newUsername -> usernameState = usernameState.copy(username = newUsername) })
                Spacer(modifier = Modifier.height(15.dp))
                NoteSection(noteState = noteState, onNoteChange = { newNoteState ->
                    noteState = noteState.copy(note = newNoteState)
                })
            }
            FinaxisButton(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                onClick = {
                    viewModel.checkIfUserExist(usernameState.username)
                },
                buttonText = "Proceed",
                isClickable = usernameState.username.isValidKycUsername(),
                isLoading = isUserExist.isLoading()
            )
        }
    }
    if (isUserExist.isSuccess()) {
        if (isUserExist.getSuccessData()) {
            /** Navigate To Next Screen **/
            onProceed(request, usernameState.username, noteState.note)
            viewModel.resetState()
        } else {
            errorMessage = "Username is not exist"
            showDialog = true
        }
    } else if (isUserExist.isError()) {
        val error = (isUserExist as RequestState.Error).message
        errorMessage = error
        showDialog = true
    }
    if (showDialog) {
        CustomErrorDialog(
            title = "Error Occurred",
            message = errorMessage,
            onDismiss = {
                showDialog = false
                viewModel.resetState()
            }
        )
    }
}