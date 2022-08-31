package com.example.mebook.ui.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mebook.navigation.MeBookScreens
import com.example.mebook.navigation.MeBookScreens.HomeNavRoute
import com.example.mebook.ui.components.ArrowBackBox
import com.example.mebook.ui.components.MeBookButton
import com.example.mebook.ui.components.MeBookScaffold
import com.example.mebook.ui.components.MeBookTextField
import com.example.mebook.ui.components.text_field_util.PasswordTextFieldState
import com.example.mebook.ui.presentation.profile.ProfileAction.ChangePassword
import com.example.mebook.ui.presentation.profile.ProfileAction.Logout
import com.example.mebook.ui.presentation.profile.ProfileAction.NavigateUp
import com.example.mebook.ui.presentation.profile.ProfileAction.ToggleFollowState
import com.example.mebook.ui.util.doOnFalse
import com.example.mebook.ui.util.doOnTrue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    navController: NavController
) {
    ProfileScreen(
        navController = navController,
        viewModel = hiltViewModel()
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    val scope = rememberCoroutineScope()

    viewModel.passwordSheetFlow.onEach {
        modalBottomSheetState.hide()
    }.launchIn(scope)

    LaunchedEffect(uiState.logout) {
        if (uiState.logout) {
            navController.navigate(MeBookScreens.AuthNavRoute.route) {
                popUpTo(HomeNavRoute.route) {
                    inclusive = true
                }
            }
        }
    }

    ProfileScreen(
        uiState = uiState,
        scope = scope,
        sheetState = modalBottomSheetState
    ) { action ->
        when (action) {
            is NavigateUp -> navController.navigateUp()
            else -> viewModel.submitAction(action)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    scope: CoroutineScope,
    sheetState: ModalBottomSheetState,
    action: (ProfileAction) -> Unit
) {
    ModalBottomSheetLayout(
        sheetContent = {
            ChangePasswordBottomSheet { newPassword ->
                action(ChangePassword(newPassword))
            }
        },
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetState = sheetState,
        sheetShape = MaterialTheme.shapes.medium.copy(
            bottomEnd = CornerSize(0.dp),
            bottomStart = CornerSize(0.dp)
        )
    ) {
        MeBookScaffold {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                ArrowBackBox {
                    action(NavigateUp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                uiState.username?.let { username ->
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = username,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h6.copy(
                            color = MaterialTheme.colors.secondary,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                uiState.isOwnProfile?.let { isOwnProfile ->
                    isOwnProfile
                        .doOnTrue {
                            MeBookButton(
                                modifier = Modifier.fillMaxWidth(),
                                backgroundColor = MaterialTheme.colors.secondary,
                                onClick = {
                                    scope.launch {
                                        sheetState.show()
                                    }
                                }
                            ) {
                                Text(
                                    text = "Change password",
                                    style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.surface)
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            MeBookButton(
                                modifier = Modifier.fillMaxWidth(),
                                backgroundColor = MaterialTheme.colors.error,
                                onClick = { action(Logout) }
                            ) {
                                Text(
                                    text = "Logout",
                                    style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.surface)
                                )
                            }
                        }
                        .doOnFalse {
                            uiState.isFollowingUser?.let { isFollowing ->
                                MeBookButton(
                                    modifier = Modifier.fillMaxWidth(),
                                    backgroundColor = if (isFollowing)
                                        MaterialTheme.colors.secondary
                                    else MaterialTheme.colors.primary,
                                    onClick = {
                                        action(ToggleFollowState(isFollowing))
                                    }
                                ) {
                                    Text(
                                        text = if (isFollowing)
                                            "Following"
                                        else "Follow",
                                        style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.surface)
                                    )
                                }
                            }
                        }
                }

                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}

@Composable
fun ChangePasswordBottomSheet(
    onClick: (String) -> Unit
) {
    val passwordState = remember { PasswordTextFieldState() }

    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))

        MeBookTextField(
            state = passwordState,
            onValueChange = {
                passwordState.onChanged(it)
            },
            placeholder = "Enter your new password"
        )

        Spacer(modifier = Modifier.height(16.dp))

        MeBookButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (passwordState.validate()) onClick(passwordState.text)
            }
        ) {
            Text("Change password")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}