package com.ryankoech.krypto.feature_home.presentation.components.success

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import ke.co.sevenskies.feature_home.R

@Composable
fun ConfirmDialog(
    onDismissRequest : () -> Unit,
    onConfirm : () -> Unit,
    onDismiss : () -> Unit,
    modifier : Modifier = Modifier,

){
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        title = { Text(text = stringResource(R.string.title_warning)) },
        text = { Text(text = stringResource(R.string.text_wipe_wallet_confirm_dialog)) },
        confirmButton = {
            TextButton(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xffe44d26),
                ),
                shape = MaterialTheme.shapes.medium,
                onClick = onConfirm
            ) {
                Text(text = stringResource(R.string.button_text_wipe_yes), color = Color.White)
            }
        },
        dismissButton = {
            TextButton(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xffb0b0b0),
                ),
                shape = MaterialTheme.shapes.medium,
                onClick = onDismiss
            ) {
                Text(text = stringResource(R.string.button_text_wipe_no), color = Color.White)
            }
        },
    )
}

@Preview
@Composable
fun ConfirmBoxPreview() {
    KryptoTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                ConfirmDialog(
                    onDismissRequest = { /*TODO*/ },
                    onConfirm = { /*TODO*/ },
                    onDismiss = { /*TODO*/ })
            }
        }
    }
}