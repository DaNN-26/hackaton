package com.example.hackaton.features.main.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hackaton.R

@Composable
fun MainBottomBar() {
    var selectedIndex by remember { mutableIntStateOf(0) }
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
                    tint = if (selectedIndex == 0) Color.Gray else Color.Black
                )
            }
        )
    }
}