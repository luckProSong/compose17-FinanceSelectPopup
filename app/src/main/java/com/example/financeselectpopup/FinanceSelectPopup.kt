package com.example.financeselectpopup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun FinanceSelectPopup(
    onFinanceClick: (String) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(6.dp),
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        ) {
            FinanceContent(
                onFinanceClick = onFinanceClick,
                onDismiss = onDismiss
            )
        }
    }
}

@Composable
fun FinanceContent(
    onFinanceClick: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val bankList = parseFinanceList(R.array.bank_list_Data)
    val stockList = parseFinanceList(R.array.stock_list_Data)

    var tabIndex by remember { mutableIntStateOf(0) }
    val financeList = if (tabIndex == 0) bankList else stockList

    Column(modifier = Modifier.padding(30.dp)) {
        TextButton(onClick = onDismiss) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                alignment = Alignment.TopEnd,
                painter = painterResource(R.drawable.close_24),
                contentDescription = "닫기"
            )
        }

        Row(modifier = Modifier.height(40.dp)) {
            FinanceTab(
                text = "은행",
                selected = tabIndex == 0,
                onClick = { tabIndex = 0 },
                modifier = Modifier.weight(1f)
            )

            FinanceTab(
                text = "증권",
                selected = tabIndex == 1,
                onClick = { tabIndex = 1 },
                modifier = Modifier.weight(1f)
            )
        }

        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(financeList) { item ->
                FinanceItem(
                    data = item,
                    onClick = { onFinanceClick(item.code) }
                )
            }
        }
    }
}

@Composable
private fun FinanceTab(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            color = if (selected) Color.Black else Color(0xFFCBC7C4)
        )
        HorizontalDivider(
            thickness = if (selected) 2.dp else 1.dp,
            color = if (selected) Color.Black else Color(0xFFCBC7C4)
        )
    }
}

@Composable
fun FinanceItem(
    data: FinanceListData,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(50.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFEAE4E4))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(text = data.name)
    }
}

@Composable
private fun parseFinanceList(arrayRes: Int): List<FinanceListData> {
    val dataArray = stringArrayResource(arrayRes)
    return dataArray.map { item ->
        val parts = item.split("|")
        FinanceListData(parts[0], parts[1])
    }
}

@Preview(showBackground = true)
@Composable
fun FinanceSelectPopupPreview() {
    FinanceSelectPopup(onFinanceClick = {}, onDismiss = {})
}
