package com.spaceflight.design.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.spaceflight.design.R


@Composable
fun NoInternetScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp), contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.no_internet_connection),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error
        )
    }
}
