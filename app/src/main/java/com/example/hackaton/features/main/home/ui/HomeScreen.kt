package com.example.hackaton.features.main.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hackaton.R

@Composable
fun HomeScreen() {
    Content()
}

@Composable
private fun Content() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SearchTopBar(

            )
        },
        bottomBar = {
            CustomBottomBar()
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()

                .padding(contentPadding)
        ) {
            LazyColumn {
                items(10) {
                    DocumentItem(
                        title = "Проверено",
                        date = "12.01.2023  20:00",
                        address = "Москва, ул Пушкина дом колотушкина"
                    )
                }
            }
            IconButton(
                modifier = Modifier
                    .padding(bottom = 20.dp, end = 20.dp)
                    .background(
                        Color(33, 202, 114),
                        RoundedCornerShape(30.dp)
                    )
                    .size(80.dp)
                    .align(Alignment.BottomEnd),
                onClick = { /*TODO*/ },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.White,
                )
            ) {
                Icon(
                    modifier = Modifier.size(50.dp),
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
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
            .padding(15.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable { }
            .shadow(
                elevation = 51.dp
            )
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
                text = "Посик",
                fontSize = 15.sp,
                color = Color.Gray
            )
        }

    )
}

@Composable
fun CustomBottomBar(
) {
    var selectedIndex by remember { mutableStateOf(0) }
    BottomAppBar(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth(),
        containerColor = Color.White
    ) {
        NavigationBarItem(
            modifier = Modifier.weight(1f),
            selected = selectedIndex == 0,
            onClick = { selectedIndex = 0 },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color(247,247,249)
            ),
            icon = {
                Icon(
                    modifier = Modifier
                        .weight(1f)
                        .size(30.dp),
                    painter = painterResource(R.drawable.home),
                    contentDescription = null,
                    tint = if (selectedIndex == 1) Color.Gray else Color.Black,


                    )
            }
        )
        NavigationBarItem(
            modifier = Modifier.weight(1f),
            selected = selectedIndex == 1,
            onClick = { selectedIndex = 1 },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color(247,247,249)
            ),
            icon = {
                Icon(
                    modifier = Modifier
                        .weight(1f)
                        .size(30.dp),
                    painter = painterResource(R.drawable.person),
                    contentDescription = null,
                    tint = if (selectedIndex == 0) Color.Gray else Color.Black,


                    )
            }
        )
    }
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
@Preview
fun PreviewHomeScreen() {
    Content()
}