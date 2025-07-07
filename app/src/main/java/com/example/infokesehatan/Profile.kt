package com.example.infokesehatan

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.infokesehatan.ui.theme.InfoKesehatanTheme

class Profile {
    @Composable
    fun Profile(modifier: Modifier, navController: NavController) {
        val msg =
            navController.currentBackStackEntry?.savedStateHandle?.get<String>("msg")
        Column(
            modifier = modifier.fillMaxSize(), // Menggunakan modifier yang diteruskan
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp) // Ukuran diperbesar sedikit agar lebih terlihat
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp)) // Spasi setelah gambar
            // Perbaikan sintaks Text
            Text(
                text = "Pembuktian terbaik bukan dari kata-kata, tetapi dari tindakan yang konsisten dan hasil yang nyata.",
                modifier = Modifier.padding(horizontal = 30.dp),
                overflow = TextOverflow.Clip,
                color = Color.Green,
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 2.sp, // Sesuaikan letter spacing agar lebih mudah dibaca
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp)) // Spasi sebelum tombol
            Button(
                onClick = {
                    navController.navigate("homescreen") // Navigasi ke homescreen
                }
            ) {
                Text("Go to Home Screen")
            }
            Spacer(modifier = Modifier.height(8.dp))
            msg?.let { Text(it) }
        }
    }

    @Composable
    fun ProfileScreen(navController: NavController) {
        InfoKesehatanTheme {
            Scaffold(
                bottomBar = {
                    BottomNavigation(navController)
                }
            ) { padding ->
                // Meneruskan padding dari Scaffold ke Konten Profil
                Profile(Modifier.padding(padding), navController)
            }
        }
    }
}
