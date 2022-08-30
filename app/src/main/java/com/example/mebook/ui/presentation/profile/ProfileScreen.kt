package com.example.mebook.ui.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.mebook.ui.presentation.profile.ProfileAction.ChangePassword
import com.example.mebook.ui.presentation.profile.ProfileAction.Logout
import com.example.mebook.ui.presentation.profile.ProfileAction.NavigateUp
import com.example.mebook.ui.presentation.profile.ProfileAction.ToggleFollowState
import com.example.mebook.ui.util.doOnFalse
import com.example.mebook.ui.util.doOnTrue

@Composable
fun ProfileScreen(
    navController: NavController
) {
    ProfileScreen(
        navController = navController,
        viewModel = hiltViewModel()
    )
}

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = uiState.logout) {
        if (uiState.logout) {
            navController.navigate(MeBookScreens.AuthNavRoute.route) {
                popUpTo(HomeNavRoute.route) {
                    inclusive = true
                }
            }
        }
    }

    ProfileScreen(uiState) { action ->
        when (action) {
            is NavigateUp -> navController.navigateUp()
            else -> viewModel.submitAction(action)
        }
    }
}

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    action: (ProfileAction) -> Unit
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
                                action(ChangePassword("87654321"))
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