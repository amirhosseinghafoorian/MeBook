package com.example.mebook.ui.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mebook.navigation.MeBookScreens
import com.example.mebook.navigation.MeBookScreens.HOME_NAV_ROUTE
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(64.dp))

            Text("AmirHossein Ghafoorian")

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colors.error)
                    .clickable {
                        viewModel.submitAction(Logout)
                        navController.navigate(MeBookScreens.AUTH_NAV_ROUTE) {
                            popUpTo(HOME_NAV_ROUTE) {
                                inclusive = true
                            }
                        }
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Logout",
                    style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.surface)
                )
            }

            Spacer(modifier = Modifier.height(64.dp))

        }
    }
}