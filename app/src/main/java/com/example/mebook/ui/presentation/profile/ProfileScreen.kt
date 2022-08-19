package com.example.mebook.ui.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mebook.navigation.MeBookScreens
import com.example.mebook.navigation.MeBookScreens.HOME_NAV_ROUTE
import com.example.mebook.ui.components.ArrowBackBox
import com.example.mebook.ui.components.MeBookButton
import com.example.mebook.ui.components.MeBookScaffold
import com.example.mebook.ui.presentation.profile.ProfileAction.Logout

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
    MeBookScaffold {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ArrowBackBox {
                navController.navigateUp()
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Username",
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.secondary,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "AmirHossein Ghafoorian",
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.primary,
                        fontWeight = FontWeight.ExtraBold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Divider(
                    modifier = Modifier.clip(MaterialTheme.shapes.medium),
                    color = MaterialTheme.colors.surface,
                    thickness = 4.dp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Join Date",
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.secondary,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "8/20/2022",
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.primary,
                        fontWeight = FontWeight.ExtraBold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.weight(1f))

            MeBookButton(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = MaterialTheme.colors.secondary,
                onClick = {}
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
                    viewModel.submitAction(Logout)
                    navController.navigate(MeBookScreens.AUTH_NAV_ROUTE) {
                        popUpTo(HOME_NAV_ROUTE) {
                            inclusive = true
                        }
                    }
                }
            ) {
                Text(
                    text = "Logout",
                    style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.surface)
                )
            }

            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}