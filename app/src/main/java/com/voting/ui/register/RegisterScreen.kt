package com.voting.ui.register

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.voting.R
import com.voting.routing.Screen
import com.voting.ui.localDatabase.VotingLocalDataBase
import com.voting.ui.theme.VotingAppTheme
import com.voting.ui.theme.purple
import com.voting.ui.theme.white
import com.voting.utils.BorderButton
import com.voting.utils.RoundedBackgroundButton
import com.voting.utils.isValidEmail

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current
    val preferenceManager = remember {
        VotingLocalDataBase(context)
    }
    var isRegisterVoter by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val firebaseAuth = FirebaseAuth.getInstance()
    VotingAppTheme {
        Scaffold {
            Column(
                modifier = Modifier.fillMaxSize().background(purple).verticalScroll(rememberScrollState())
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
                                    text = "Register", color = Color.Gray,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp),
                                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                BorderButton(
                                    value = name,
                                    onValueChange = { text ->
                                        name = text
                                    },
                                    placeholder = "Enter name",
                                    keyboardType = KeyboardType.Text,
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                BorderButton(
                                    value = email,
                                    onValueChange = { text ->
                                        email = text
                                    },
                                    placeholder = "Enter email",
                                    keyboardType = KeyboardType.Email,
                                )

                                Spacer(modifier = Modifier.height(5.dp))
                                BorderButton(
                                    value = password,
                                    onValueChange = { text ->
                                        password = text
                                    },
                                    placeholder = "Enter Password",
                                    keyboardType = KeyboardType.Password,
                                    visualTransformation = PasswordVisualTransformation(),
                                )

                                Spacer(modifier = Modifier.height(5.dp))
                                RoundedBackgroundButton(
                                    text = "Register",
                                    onClick = {
                                        if (name.isNotEmpty()) {
                                            if (email.isNotEmpty()) {
                                                if (!isValidEmail(email)) {
                                                    if (password.isNotEmpty()) {
                                                        isRegisterVoter = true
                                                        firebaseAuth.createUserWithEmailAndPassword(
                                                            email.lowercase(),
                                                            password
                                                        )
                                                            .addOnCompleteListener { task ->
                                                                if (task.isSuccessful) {
                                                                    preferenceManager.saveData(
                                                                        "isLogin", true
                                                                    )
                                                                    Toast.makeText(
                                                                        context,
                                                                        "Register successfully.",
                                                                        Toast.LENGTH_SHORT
                                                                    ).show()
                                                                    navController.navigate(
                                                                        Screen.MainScreen.route
                                                                    ) {
                                                                        popUpTo(Screen.RegisterScreen.route) {
                                                                            inclusive = true
                                                                        }
                                                                    }
                                                                    isRegisterVoter = false
                                                                } else {
                                                                    Toast.makeText(
                                                                        context,
                                                                        task.exception?.message.toString(),
                                                                        Toast.LENGTH_SHORT
                                                                    ).show()
                                                                    isRegisterVoter = false
                                                                }
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
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    "Please enter valid email.",
                                                    Toast.LENGTH_LONG
                                                ).show()

                                            }
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Please enter name.",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    }
                                )
                                RoundedBackgroundButton(
                                    text = "Login",
                                    onClick = {
                                        navController.navigate(Screen.LoginScreen.route) {
                                            popUpTo(Screen.RegisterScreen.route) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                )

                            }
                            Spacer(modifier = Modifier.height(20.dp))

                        }

                    }


                }



            }
            if (isRegisterVoter) {
                Dialog(
                    onDismissRequest = { },
                    DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(100.dp)
                            .background(white, shape = RoundedCornerShape(8.dp))
                    ) {
                        CircularProgressIndicator(color = purple)
                    }
                }
            }


        }
    }
}