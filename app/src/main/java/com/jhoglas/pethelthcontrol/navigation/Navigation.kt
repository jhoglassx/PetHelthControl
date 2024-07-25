package com.jhoglas.pethelthcontrol.navigation

import kotlinx.serialization.Serializable

@Serializable
open class Navigation(
    val route: String
)

@Serializable
object HomeNavigation : Navigation(route = "home")
@Serializable
object RegisterNavigation : Navigation(route = "register")