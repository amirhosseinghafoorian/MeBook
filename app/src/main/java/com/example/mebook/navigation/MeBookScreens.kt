package com.example.mebook.navigation

sealed class MeBookScreens(val route: String) {
    object SplashRoute : MeBookScreens("splash")
    object AuthenticateRoute : MeBookScreens("authenticate")
    object LoginRoute : MeBookScreens("login")
    object SignUpRoute : MeBookScreens("signUp")
    object HomeRoute : MeBookScreens("home")
    object ProfileRoute : MeBookScreens("profile")
    object SearchRoute : MeBookScreens("search")
    object WriteRoute : MeBookScreens("write")
    object ArticleRoute : MeBookScreens("article")
    object FullArticlesRoute : MeBookScreens("fullArticles?type={type}") {
        fun generateRoute(type: String): String {
            return "fullArticles?type=$type"
        }
    }

    object AuthNavRoute : MeBookScreens("auth_nav")
    object HomeNavRoute : MeBookScreens("home_nav")
}