package com.spaceflight.design

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.spaceflight.design.ui.Appbar
import com.spaceflight.design.ui.ErrorScreen
import com.spaceflight.design.ui.LoadingIndicator
import com.spaceflight.design.ui.MessageScreen
import com.spaceflight.design.ui.NoInternetScreen

@Composable
@Preview
fun AppbarPreview() {
    Appbar(
        title = "SpaceFlight",
        navIcon = Icons.TwoTone.Home,
        onNav = {}
    )
}

@Composable
@Preview
fun ErrorScreenPreview() {
    ErrorScreen("Network is not reachable")
}

@Composable
@Preview
fun LoadingIndicatorPreview() {
    LoadingIndicator()
}

@Composable
@Preview
fun MessageScreenPreview() {
    MessageScreen("This is Message")
}

@Composable
@Preview
fun NoInternetScreenPreview() {
    NoInternetScreen()
}