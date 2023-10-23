package com.example.jetpackcomposecodelab.mysoothe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecodelab.ui.theme.JetpackComposeCodeLabTheme
import com.example.jetpackcomposecodelab.R

class MySoothe: ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            MySootheApp(windowSizeClass)
        }
    }

    @Composable
    fun SearchBar(
        modifier: Modifier = Modifier
    ) {
        TextField(
            value = "",
            onValueChange = {},
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.surface,
                focusedTrailingIconColor = MaterialTheme.colorScheme.surface
            ),
            placeholder = {
                Text(text = stringResource(R.string.placeholder_search))
            }
        )
    }

    @Composable
    fun AlignYourBodyElement(
        @DrawableRes drawable: Int,
        @StringRes text: Int,
        modifier: Modifier = Modifier
    )  {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
            )
            Text(
                text = stringResource(text),
                modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

    @Composable
    fun FavoriteCollectionCard(
        @DrawableRes drawable: Int,
        @StringRes text: Int,
        modifier: Modifier = Modifier
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = modifier
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.width(255.dp)
            ) {
                Image(
                    painter = painterResource(drawable),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(80.dp)
                )
                Text(
                    text = stringResource(text),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }

    @Composable
    fun AlignYourBodyRow(
        modifier: Modifier = Modifier
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = modifier
        ) {
            items(alignYourBodyData) { item ->
                AlignYourBodyElement(item.drawable, item.text)
            }
        }
    }

    @Composable
    fun FavoriteCollectionsGrid() {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.height(168.dp)
        ) {
            items(favoriteCollectionsData) { item ->
                FavoriteCollectionCard(drawable = item.drawable, text = item.text, Modifier.height(80.dp))
            }
        }
    }

    @Composable
    fun HomeSection(
        @StringRes title: Int,
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
    ) {
        Column(modifier) {
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp)
            )
            content()
        }
    }

    @Composable
    fun HomeScreen(
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier.verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(16.dp))
            SearchBar()
            HomeSection(title = R.string.align_your_body) {
                AlignYourBodyRow()
            }
            HomeSection(title = R.string.favorite_collections) {
                FavoriteCollectionsGrid()
            }
            Spacer(Modifier.height(16.dp))
        }
    }

    @Composable
    fun SootheBottomNavigation(modifier: Modifier = Modifier) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            modifier = modifier
        ) {
            NavigationBarItem(
                selected = true,
                onClick = {},
                icon = {
                    Icon(
                        imageVector = Icons.Default.Spa,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_home))
                }
            )
            NavigationBarItem(
                selected = false,
                onClick = {},
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_profile))
                }
            )
        }
    }

    @Composable
    fun SootheNavigationRail() {
        NavigationRail(
            containerColor = MaterialTheme.colorScheme.background,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NavigationRailItem(
                    selected = true,
                    onClick = {},
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Spa,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(stringResource(R.string.bottom_navigation_home))
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                NavigationRailItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(stringResource(R.string.bottom_navigation_profile))
                    }
                )
            }
        }
    }

    @Composable
    fun MySootheAppPortrait() {
        JetpackComposeCodeLabTheme {
            Scaffold(
                bottomBar = { SootheBottomNavigation() }
            ) { padding ->
                HomeScreen(modifier = Modifier.padding(padding))
            }
        }
    }

    @Composable
    fun MySootheAppLandscape() {
        JetpackComposeCodeLabTheme {
            Row {
               SootheNavigationRail()
                HomeScreen()
            }
        }
    }

    @Composable
    fun MySootheApp(windowSize: WindowSizeClass) {
        when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Compact -> MySootheAppPortrait()
            WindowWidthSizeClass.Expanded -> MySootheAppLandscape()
        }
    }

    private data class DrawableStringPair(
        @DrawableRes val drawable: Int,
        @StringRes val text: Int
    )

    private val alignYourBodyData = listOf(
        R.drawable.ab1_inversions to R.string.ab1_inversions,
        R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
        R.drawable.ab3_stretching to R.string.ab3_stretching,
        R.drawable.ab4_tabata to R.string.ab4_tabata,
        R.drawable.ab5_hiit to R.string.ab5_hiit,
        R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
    ).map { DrawableStringPair(it.first, it.second) }

    private val favoriteCollectionsData = listOf(
        R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
        R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
        R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
        R.drawable.fc4_self_massage to R.string.fc4_self_massage,
        R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
        R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
    ).map { DrawableStringPair(it.first, it.second) }

    @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
    @Composable
    fun SearchBarPreview() {
        JetpackComposeCodeLabTheme {
            SearchBar(modifier = Modifier.padding(all = 10.dp))
        }
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
    @Composable
    fun AlignYourBodyElementPreview() {
        JetpackComposeCodeLabTheme {
            AlignYourBodyElement(
                drawable = R.drawable.ab1_inversions,
                text = R.string.ab1_inversions,
                modifier = Modifier.padding(8.dp)
            )
        }
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
    @Composable
    fun FavoriteCollectionCardPreview() {
        JetpackComposeCodeLabTheme {
            FavoriteCollectionCard(
                drawable = R.drawable.fc2_nature_meditations,
                text = R.string.fc2_nature_meditations,
                modifier = Modifier.padding(8.dp)
            )
        }
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
    @Composable
    fun AlignYourBodyRowPreview() {
        JetpackComposeCodeLabTheme {
            AlignYourBodyRow()
        }
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
    @Composable
    fun FavoriteCollectionsGridPreview() {
        JetpackComposeCodeLabTheme {
            FavoriteCollectionsGrid()
        }
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
    @Composable
    fun HomeSectionPreview() {
        JetpackComposeCodeLabTheme {
            HomeSection(R.string.align_your_body) {
                AlignYourBodyRow()
            }
        }
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, widthDp = 320)
    @Composable
    fun HomeScreenPreview() {
        JetpackComposeCodeLabTheme {
            HomeScreen()
        }
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, widthDp = 320)
    @Composable
    fun SootheBottomNavigationPreview() {
        JetpackComposeCodeLabTheme {
            SootheBottomNavigation()
        }
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, widthDp = 320)
    @Composable
    fun MySootheAppPortraitPreview() {
        MySootheAppPortrait()
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
    @Composable
    fun SootheNavigationRailPreview() {
        JetpackComposeCodeLabTheme {
            SootheNavigationRail()
        }
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 480, widthDp = 640)
    @Composable
    fun MySootheAppLandscapePreview() {
        MySootheAppLandscape()
    }
}