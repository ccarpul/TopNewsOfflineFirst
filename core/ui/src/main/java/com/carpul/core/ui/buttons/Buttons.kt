package com.carpul.core.ui.buttons

import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.carpul.core.ui.R

@Composable
fun IconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable () -> Unit,
    checkedIcon: @Composable () -> Unit = icon,
) {
    FilledIconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        colors = IconButtonDefaults.iconToggleButtonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            checkedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            checkedContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.primaryContainer

        ),
    ) {
        if (checked) checkedIcon() else icon()
    }
}

@Composable
fun SaveButton(
    isSaved: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconToggleButton(
        checked = isSaved,
        onCheckedChange = { onClick() },
        modifier = modifier,
        icon = {
            Icon(
                painter = painterResource(R.drawable.ic_bookmark_border),
                contentDescription = null,
            )
        },
        checkedIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_bookmarks),
                contentDescription = null,
            )
        },
    )
}