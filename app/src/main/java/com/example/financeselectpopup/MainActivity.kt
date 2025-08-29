package com.example.financeselectpopup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isFinanceSelectPopupShow by remember { mutableStateOf(false) }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    isFinanceSelectPopupShow = true
                }) {
                    Text("은행/증권 팝업")
                }
            }

            if (isFinanceSelectPopupShow) {
                FinanceSelectPopup(
                    onFinanceClick = { financeCode ->
                        isFinanceSelectPopupShow = false
                    },
                    onDismiss = {
                        isFinanceSelectPopupShow = false
                    })
            }
        }
    }
}