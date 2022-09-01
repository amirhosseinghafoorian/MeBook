package com.example.mebook.navigation

sealed class MeBookScreens(val route: String) {
    object SplashRoute : MeBookScreens("splash")
    object AuthenticateRoute : MeBookScreens("authenticate")
    object LoginRoute : MeBookScreens("login")
    object SignUpRoute : MeBookScreens("signUp")
    object HomeRoute : MeBookScreens("home")
    object ProfileRoute : MeBookScreens("profile?username={username}") {
        fun generateRoute(username: String? = null): String {
            return "profile?username=$username"
        }
    }

    object SearchRoute : MeBookScreens("search")
    object WriteRoute : MeBookScreens("write")
    object ArticleRoute : MeBookScreens("article")
    object FullArticlesRoute : MeBookScreens("fullArticles?type={type}&username={username}") {
        fun generateRoute(type: String, username: String? = null): String {
            var result = "fullArticles?type=$type"
            username?.let {
                result += "&username=$username"
            }
            return result
        }
    }

    object AuthNavRoute : MeBookScreens("auth_nav")
    object HomeNavRoute : MeBookScreens("home_nav")
}