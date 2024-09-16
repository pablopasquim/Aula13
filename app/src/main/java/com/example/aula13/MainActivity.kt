package com.example.aula13

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aula13.ui.theme.Aula13Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutMain()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tela1(navController: NavController){

    var nome by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Menu", fontSize = 25.sp)

        Spacer(modifier = Modifier.height(15.dp))

        TextField(value = nome,
            onValueChange = { nome = it},
            label = { Text(text = "Nome do usuário: ") })

        Spacer(modifier = Modifier.height(15.dp))

        TextField(value = email,
            onValueChange = { email = it},
            label = { Text(text = "E-mail: ") })

        Button(onClick = {

            if (nome.isNotBlank() && email.isNotBlank()) {
                navController.navigate("Login/$nome/$email")
            } else{

                Toast.makeText(
                    context,
                    "Preencha tdos os campos",
                    Toast.LENGTH_SHORT
                ).show()0
            }

        }) {
            Text(text = "Login")
        }
    }
}

@Composable
fun Login(navController: NavController, nome: String?, email : String?){


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Bem-vindo, $nome", fontSize = 25.sp)
        Text(text = "$email")

        Spacer(modifier = Modifier.height(15.dp))

        Button(onClick = {
            navController.navigate("Tela1")
        }) {
            Text(text = "Voltar para o inicio")
        }
    }
}

@Composable
fun LayoutMain(){

    val nav = rememberNavController()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {


    }

    NavHost(
        navController = nav,
        startDestination = "Tela1"
    ) {
        composable("Tela1") { Tela1(navController = nav)}
        composable("Login/{nome}/{email}") {
            navBackStackEntry -> Login(
            navController = nav,
            nome = navBackStackEntry.arguments?.getString("nome"),
            email = navBackStackEntry.arguments?.getString("email"))
        }
    }
}

@Preview (showBackground = true)
@Composable
fun Preview(){



}


