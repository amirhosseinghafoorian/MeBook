package com.example.mebook.ui.presentation.write

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mebook.navigation.MeBookScreens
import com.example.mebook.ui.components.ArrowBackBox
import com.example.mebook.ui.components.ArticleTextField
import com.example.mebook.ui.components.MeBookButton
import com.example.mebook.ui.components.MeBookScaffold
import com.example.mebook.ui.components.rememberAnimationState
import com.example.mebook.ui.components.text_field_util.BasicTextFieldState
import com.example.mebook.ui.presentation.write.WriteAction.NavigateUp
import com.example.mebook.ui.presentation.write.WriteAction.Publish

@Composable
fun WriteScreen(
    navController: NavController
) {
    WriteScreen(
        navController = navController,
        viewModel = hiltViewModel()
    )
}

@Composable
fun WriteScreen(
    navController: NavController,
    viewModel: WriteViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isPublished) {
        if (uiState.isPublished) {
            navController.navigate(MeBookScreens.HomeNavRoute.route) {
                popUpTo(MeBookScreens.HomeNavRoute.route) {
                    inclusive = true
                }
            }
        }
    }

    // todo add a dialog that warns user about discarding changes
    //  keyboard opening should be handled
    WriteScreen { action ->
        when (action) {
            NavigateUp -> navController.navigateUp()
            else -> viewModel.submitAction(action)
        }
    }

}

@Composable
fun WriteScreen(
    action: (WriteAction) -> Unit
) {
    // todo should be moved to uiState
    val titleState = remember { BasicTextFieldState() }
    val bodyState = remember { BasicTextFieldState() }

    val animationState = rememberAnimationState(blockStartUp = true)

    LaunchedEffect(key1 = titleState.text.isNotEmpty(), key2 = bodyState.text.isNotEmpty()) {
        animationState.targetState = titleState.text.isNotEmpty() && bodyState.text.isNotEmpty()
    }

    MeBookScaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ArrowBackBox {
                    action(NavigateUp)
                }

                Spacer(modifier = Modifier.weight(1f))

                AnimatedVisibility(
                    animationState,
                    enter = fadeIn(tween()),
                    exit = fadeOut(tween())
                ) {
                    MeBookButton(
                        shape = MaterialTheme.shapes.large,
                        onClick = {
                            action(
                                Publish(
                                    title = titleState.text,
                                    body = bodyState.text
                                )
                            )
                        }
                    ) {
                        Text(
                            text = "Publish",
                            color = MaterialTheme.colors.surface
                        )
                    }
                }
            }

            ArticleTextField(
                text = titleState.text,
                hint = "Title ...",
                onValueChange = {
                    titleState.onChanged(it)
                },
                isHintVisible = titleState.text.isEmpty(),
                textStyle = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                ),
                modifier = Modifier.weight(0.2f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            ArticleTextField(
                text = bodyState.text,
                hint = "Body ...",
                onValueChange = {
                    bodyState.onChanged(it)
                },
                isHintVisible = bodyState.text.isEmpty(),
                textStyle = MaterialTheme.typography.h6.copy(
                    color = MaterialTheme.colors.onBackground
                ),
                modifier = Modifier.weight(0.8f)
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}