package com.example.hackaton.features.main.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.hackaton.R
import com.example.hackaton.features.main.home.component.HomeComponent
import com.example.hackaton.features.main.ui.components.MainBottomBar

@Composable
fun HomeScreen(
    component: HomeComponent
) {
    val state by component.state.subscribeAsState()

    Content()
}

@Composable
private fun Content() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { SearchTopBar() },
        bottomBar = { MainBottomBar() },
        floatingActionButton = { HomeFloatingButton { /*TODO*/ } }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            items(10) {
                DocumentItem(
                    title = "Проверено",
                    date = "12.01.2023  20:00",
                    address = "Москва, ул Пушкина дом колотушкина"
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchTopBar() {
    TextField(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(15.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable { }
            .fillMaxWidth(),
        value = "",
        onValueChange = {},
        enabled = false,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(20.dp),
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.searchicon),
                contentDescription = null,
                tint = Color.Unspecified
            )
        },
        placeholder = {
            Text(
                text = "Поиск",
                fontSize = 15.sp,
                color = Color.Gray
            )
        }
    )
}

@Composable
private fun DocumentItem(
    title: String,
    date: String,
    address: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            color = Color(29, 27, 32),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = date,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(112, 123, 129)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "Адрес: $address",
                modifier = Modifier.weight(1f),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color(73, 69, 79),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
        HorizontalDivider(
            thickness = 1.5f.dp
        )
    }
}

@Composable
private fun HomeFloatingButton(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier
            .padding(bottom = 20.dp, end = 20.dp)
            .size(70.dp),
        shape = RoundedCornerShape(20.dp),
        containerColor = Color(33, 202, 114),
        contentColor = Color.White
    ) {
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Default.Add,
            contentDescription = null,
        )
    }
}

@Composable
@Preview
fun PreviewHomeScreen() {
    Content()
}
