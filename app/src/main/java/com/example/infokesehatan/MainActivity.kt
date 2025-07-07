package com.example.infokesehatan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.infokesehatan.ui.theme.InfoKesehatanTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            Navigation().Navigation(windowSizeClass)
        }
    }
}

/**
 * Composable untuk bilah pencarian.
 */
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = { },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

/**
 * Pratinjau aplikasi untuk mode potret (ponsel).
 * Menggunakan Scaffold untuk menempatkan navigasi bawah.
 */
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AppPreview(navController: NavController = rememberNavController()) {
    InfoKesehatanTheme {
        Scaffold(
            bottomBar = {
                BottomNavigation(navController)
            }
        ) { padding ->
            // Menggunakan padding yang diberikan oleh Scaffold
            HomeScreen(Modifier.padding(padding))
        }
    }
}

/**
 * Item tunggal untuk bagian "Align Your Body".
 */
@Composable
fun AlignYourBody(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
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

/**
 * Item tunggal untuk "Favorite Collection".
 */
@Composable
fun FavoriteCollection(
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

// Data class untuk pasangan drawable dan string resource
private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

// Data untuk bagian "Align Your Body"
private val alignYourBodyData = listOf(
    R.drawable.sungai_colorado to R.string.sungai_colorado,
    R.drawable.sungai_danube to R.string.sungai_danube,
    R.drawable.sungai_ganges to R.string.sungai_ganges,
    R.drawable.sungai_mendidih_amazon to R.string.sungai_mendidih_amazon,
    R.drawable.sungai_seine to R.string.sungai_seine,
    R.drawable.sungai_zambezi to R.string.sungai_zambezi
).map { DrawableStringPair(it.first, it.second) }

/**
 * Baris horizontal yang dapat digulir untuk bagian "Align Your Body".
 */
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
            AlignYourBody(item.drawable, item.text)
        }
    }
}

// Data untuk bagian "Favorite Collection"
private val favoriteCollectionData = listOf(
    R.drawable.canyon to R.string.canyon,
    R.drawable.culture to R.string.culture,
    R.drawable.spirituality to R.string.spirituality,
    R.drawable.biodiversity to R.string.biodiversity,
    R.drawable.romance to R.string.romance,
    R.drawable.victoria to R.string.victoria
).map { DrawableStringPair(it.first, it.second) }

/**
 * Grid horizontal yang dapat digulir untuk "Favorite Collections".
 */
@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(favoriteCollectionData) { item ->
            FavoriteCollection(item.drawable, item.text, Modifier.height(80.dp))
        }
    }
}

/**
 * Bagian beranda yang dapat digunakan kembali dengan judul.
 */
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

/**
 * Layar beranda utama yang berisi SearchBar, AlignYourBody, dan FavoriteCollections.
 */
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.rivers) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.about_rivers) {
            FavoriteCollectionsGrid()
        }
        Spacer(Modifier.height(16.dp))
    }
}

/**
 * Navigasi bawah untuk aplikasi.
 */
@Composable
fun BottomNavigation(navController: NavController) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_home))
            },
            selected = true,
            onClick = {navController.navigate("homescreen")}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_profile))
            },
            selected = false,
            onClick = {navController.navigate("profilescreen")}
        )
    }
}

/**
 * Navigasi samping (rail) untuk layar lebar (landscape).
 */
@Composable
private fun SampingNavigationRail(navController: NavController) {
    NavigationRail(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxHeight()
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_home))
                },
                selected = true,
                onClick = {navController.navigate("homescreen")}
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_profile))
                },
                selected = false,
                onClick = {navController.navigate("profilescreen")}
            )
        }
    }
}

/**
 * Pratinjau aplikasi untuk mode landscape (tablet/desktop).
 * Menggunakan NavigationRail samping.
 */
@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun AppLandScape(navController: NavController = rememberNavController()) {
    InfoKesehatanTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Row {
                SampingNavigationRail(navController)
                HomeScreen(Modifier)
            }
        }
    }
}

/**
 * Fungsi utama aplikasi yang mendeteksi ukuran layar dan menampilkan UI yang sesuai.
 */
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun MyHealthApp(windowSize: WindowSizeClass, navController: NavController) {
    InfoKesehatanTheme {
        when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                // Untuk layar ponsel (portrait), gunakan BottomNavigation
                Scaffold(
                    bottomBar = {
                        BottomNavigation(navController)
                    }
                ) { paddingValues ->
                    HomeScreen(Modifier.padding(paddingValues))
                }
            }
            WindowWidthSizeClass.Medium, WindowWidthSizeClass.Expanded -> {
                // Untuk layar yang lebih lebar (tablet/desktop), gunakan NavigationRail samping
                Row(modifier = Modifier.fillMaxWidth()) {
                    SampingNavigationRail(navController)
                    HomeScreen(Modifier.weight(1f)) // HomeScreen akan mengambil sisa ruang horizontal
                }
            }
        }
    }
}