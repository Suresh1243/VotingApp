package com.voting.ui.login

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.voting.R
import com.voting.routing.Screen
import com.voting.ui.theme.VotingAppTheme
import com.voting.ui.theme.purple
import com.voting.ui.theme.white
import com.voting.utils.OutlineFormField
import com.voting.utils.RoundedButton

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val db = Firebase.firestore
    VotingAppTheme {
        Scaffold {
            Column(
                modifier = Modifier.background(purple)
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_vote),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth().height(180.dp)
                )
                Column(
                    modifier = Modifier.fillMaxSize().background(purple),
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(Modifier.padding(bottom = 60.dp)) {
                        Card(
                            modifier = Modifier.padding(
                                top = 10.dp,
                                start = 10.dp,
                                end = 10.dp
                            ),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(5.dp),
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(20.dp)
                            ) {
                                Text(
                                    text = "Login", color = Color.Gray,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp),
                                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                OutlineFormField(
                                    value = email,
                                    onValueChange = { text ->
                                        email = text
                                    },
                                    placeholder = "Enter email",
                                    keyboardType = KeyboardType.Email,
                                )

                                Spacer(modifier = Modifier.height(5.dp))
                                OutlineFormField(
                                    value = password,
                                    onValueChange = { text ->
                                        password = text
                                    },
                                    placeholder = "Enter Password",
                                    keyboardType = KeyboardType.Password,
                                    visualTransformation = PasswordVisualTransformation(),
                                )

                                Spacer(modifier = Modifier.height(5.dp))
                                RoundedButton(
                                    text = "Login",
                                    onClick = {
                                        if (email.isNotEmpty()) {
                                            if (password.isNotEmpty()) {
                                                db.collection("users")
                                                    .get()
                                                    .addOnSuccessListener { result ->
                                                        if (result.isEmpty) {
                                                            Toast.makeText(
                                                                context,
                                                                "Invalid user.",
                                                                Toast.LENGTH_LONG
                                                            ).show()
                                                            return@addOnSuccessListener
                                                        } else {
                                                            for (document in result) {
                                                                Log.e(
                                                                    "TAG",
                                                                    "setOnClick: $document"
                                                                )
                                                                if (document.data["email"] == email &&
                                                                    document.data["password"] == password
                                                                ) {
                                                                    Toast.makeText(
                                                                        context,
                                                                        "Login successfully.",
                                                                        Toast.LENGTH_LONG
                                                                    ).show()
                                                                    navController.navigate(
                                                                        Screen.MainScreen.route
                                                                    ) {
                                                                        popUpTo(Screen.LoginScreen.route) {
                                                                            inclusive = true
                                                                        }
                                                                    }
                                                                } else {
                                                                    Toast.makeText(
                                                                        context,
                                                                        "Invalid user.",
                                                                        Toast.LENGTH_LONG
                                                                    ).show()
                                                                    return@addOnSuccessListener
                                                                }
                                                            }
                                                        }

                                                    }
                                                    .addOnFailureListener { exception ->
                                                        Toast.makeText(
                                                            context,
                                                            exception.message.toString(),
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                    }
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    "Please enter password.",
                                                    Toast.LENGTH_LONG
                                                ).show()

                                            }
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Please enter email.",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    }
                                )
                                RoundedButton(
                                    text = "Register",
                                    onClick = {
                                        navController.navigate(Screen.RegisterScreen.route)
                                    }
                                )

                            }
                            Spacer(modifier = Modifier.height(20.dp))

                        }

                    }


                }



            }



        }
    }
}