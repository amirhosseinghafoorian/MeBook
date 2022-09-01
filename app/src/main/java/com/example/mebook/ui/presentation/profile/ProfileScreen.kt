package com.example.mebook.ui.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mebook.AppConstants.FULL_ARTICLE_USER_TYPE
import com.example.mebook.R
import com.example.mebook.navigation.MeBookScreens
import com.example.mebook.navigation.MeBookScreens.HomeNavRoute
import com.example.mebook.ui.components.ArrowBackBox
import com.example.mebook.ui.components.LottieBox
import com.example.mebook.ui.components.MeBookButton
import com.example.mebook.ui.components.MeBookScaffold
import com.example.mebook.ui.components.MeBookSnackbarObserver
import com.example.mebook.ui.components.MeBookTextField
import com.example.mebook.ui.components.ShowMoreItem
import com.example.mebook.ui.components.text_field_util.PasswordTextFieldState
import com.example.mebook.ui.presentation.profile.ProfileAction.ChangePassword
import com.example.mebook.ui.presentation.profile.ProfileAction.Logout
import com.example.mebook.ui.presentation.profile.ProfileAction.Navigate
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
    val scaffoldState = rememberScaffoldState()

    MeBookSnackbarObserver(
        scaffoldState = scaffoldState,
        snackbarFlow = viewModel.snackbarFlow
    )

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
        scaffoldState = scaffoldState,
        sheetState = modalBottomSheetState
    ) { action ->
        when (action) {
            is NavigateUp -> navController.navigateUp()
            is Navigate -> navController.navigate(action.route)
            else -> viewModel.submitAction(action)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    sheetState: ModalBottomSheetState,
    action: (ProfileAction) -> Unit
) {
    var sheetContentState by remember {
        mutableStateOf(true)
    }

    ModalBottomSheetLayout(
        sheetContent = {
            if (sheetContentState) {
                LogoutBottomSheet {
                    action(Logout)
                }
            } else {
                ChangePasswordBottomSheet(
                    isLoading = uiState.isLoadingChangePassword,
                    sheetState = sheetState
                ) { newPassword ->
                    action(ChangePassword(newPassword))
                }
            }
        },
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetState = sheetState,
        sheetShape = MaterialTheme.shapes.medium.copy(
            bottomEnd = CornerSize(0.dp),
            bottomStart = CornerSize(0.dp)
        )
    ) {
        MeBookScaffold(scaffoldState = scaffoldState) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                ArrowBackBox {
                    action(NavigateUp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                uiState.isLoadingUserProfile.doOnFalse {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.medium)
                            .background(MaterialTheme.colors.primary)
                            .padding(16.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(MaterialTheme.shapes.medium)
                                .background(MaterialTheme.colors.background)
                                .padding(16.dp),
                            text = uiState.username ?: "-",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.h5.copy(
                                color = MaterialTheme.colors.onBackground,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Serif,
                                fontSize = 28.sp
                            )
                        )

                        uiState.userProfile?.let { profile ->
                            Spacer(modifier = Modifier.height(32.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(MaterialTheme.shapes.medium)
                                        .background(MaterialTheme.colors.background)
                                        .padding(16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Articles",
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.h6.copy(
                                            color = MaterialTheme.colors.onBackground,
                                            fontWeight = FontWeight.Bold,
                                        )
                                    )

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Text(
                                        text = profile.first.toString(),
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.h6,
                                        color = MaterialTheme.colors.onBackground
                                    )
                                }

                                Spacer(modifier = Modifier.width(16.dp))

                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(MaterialTheme.shapes.medium)
                                        .background(MaterialTheme.colors.background)
                                        .padding(16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Followers",
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.h6.copy(
                                            color = MaterialTheme.colors.onBackground,
                                            fontWeight = FontWeight.Bold,
                                        )
                                    )

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Text(
                                        text = profile.second.toString(),
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.h6,
                                        color = MaterialTheme.colors.onBackground
                                    )
                                }
                            }
                        }
                    }
                }.doOnTrue {
                    LottieBox(
                        resourceId = R.raw.loading,
                        modifier = Modifier.size(128.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                (uiState.userProfile?.first != 0).doOnTrue {
                    Spacer(modifier = Modifier.height(32.dp))

                    ShowMoreItem("Tap to see articles") {
                        uiState.username?.let { username ->
                            action(
                                Navigate(
                                    MeBookScreens.FullArticlesRoute.generateRoute(
                                        type = FULL_ARTICLE_USER_TYPE,
                                        username = username
                                    )
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                uiState.isOwnProfile?.let { isOwnProfile ->
                    isOwnProfile
                        .doOnTrue {
                            MeBookButton(
                                modifier = Modifier.fillMaxWidth(),
                                backgroundColor = MaterialTheme.colors.secondary,
                                onClick = {
                                    sheetContentState = false
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
                                onClick = {
                                    sheetContentState = true
                                    scope.launch {
                                        sheetState.show()
                                    }
                                }
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
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChangePasswordBottomSheet(
    isLoading: Boolean,
    sheetState: ModalBottomSheetState,
    onClick: (String) -> Unit
) {
    val passwordState = remember { PasswordTextFieldState() }

    LaunchedEffect(sheetState.isVisible) {
        if (!sheetState.isVisible) {
            passwordState.onChanged("")
        }
    }

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
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (passwordState.validate()) onClick(passwordState.text)
            }
        ) {
            if (isLoading) {
                LottieBox(
                    resourceId = R.raw.loading,
                    modifier = Modifier.size(128.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Text("Confirm")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun LogoutBottomSheet(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Do you want to logout ?",
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        MeBookButton(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.error,
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onClick()
            }
        ) {
            Text("Confirm")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}