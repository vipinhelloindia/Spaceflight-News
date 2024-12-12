package com.spaceflight.design.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.spaceflight.design.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Appbar(
    title: String,
    navIcon: ImageVector,
    onNav: () -> Unit = {}
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = { onNav() }) {
                Icon(navIcon, contentDescription = stringResource(R.string.common_appbar_des))
            }
        }
    )
}

