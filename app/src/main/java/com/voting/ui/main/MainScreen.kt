package com.voting.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.voting.R
import com.voting.appClass.AppClass.Companion.list
import com.voting.routing.Screen
import com.voting.ui.drawer.DrawerBody
import com.voting.ui.drawer.DrawerHeader
import com.voting.ui.drawer.TopBar
import com.voting.ui.localDatabase.VotingLocalDataBase
import com.voting.ui.model.CandidateModel
import com.voting.ui.theme.VotingAppTheme
import com.voting.ui.theme.purple
import com.voting.ui.theme.white
import com.voting.utils.RoundedBackgroundButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val preferenceManager = remember {
        VotingLocalDataBase(context)
    }
    val checked = remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var isLogout by remember { mutableStateOf(false) }
    var isSubmit by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    var selectedOption by remember { mutableStateOf("") }

    VotingAppTheme {
        androidx.compose.material.Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBar(
                    navController = navController,
                    onNavigationIconClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                )
            },
            modifier = Modifier.background(color = purple),
            drawerContent = {
                DrawerHeader()
                DrawerBody(closeNavDrawer = {
                    navController.navigate(Screen.ResultScreen.route)
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                }, onLogout = {
                    isLogout = true
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                })
            },
            backgroundColor = purple,
            contentColor = purple,
            drawerBackgroundColor = purple
        ) { paddingValues ->
            Modifier.padding(
                bottom = paddingValues.calculateBottomPadding()
            )
            Column(
                modifier = Modifier
                    .background(color = white)
                    .verticalScroll(scrollState)
            ) {
                Spacer(Modifier.height(10.dp))

                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    list.forEach { name ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = (name == selectedOption),
                                onClick = { selectedOption = name }
                            )
                            Text(
                                text = name,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(start = 8.dp).clickable {
                                    selectedOption = name
                                }
                            )
                        }
                    }

                }
                Box(Modifier.padding(15.dp)) {
                    RoundedBackgroundButton(
                        text = "Submit",
                        onClick = {
                            isSubmit = true
                        }
                    )
                }

            }
            if (isLogout) {
                AlertDialog(
                    onDismissRequest = {
                        isLogout = false
                    },
                    title = { Text(stringResource(id = R.string.app_name)) },
                    text = { Text("Are you sure you want to logout?") },
                    confirmButton = {
                        Button(
                            onClick = {
                                preferenceManager.saveData("isLogin", false)
                                navController.navigate(
                                    Screen.LoginScreen.route
                                ) {
                                    popUpTo(Screen.MainScreen.route) {
                                        inclusive = true
                                    }
                                }
                                isLogout = false
                            }
                        ) {
                            Text("Logout")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                isLogout = false
                            }
                        ) {
                            Text("Cancel")
                        }
                    }
                )
            }
            if (isSubmit) {
                AlertDialog(
                    onDismissRequest = {
                        isLogout = false
                    },
                    title = { Text(stringResource(id = R.string.app_name)) },
                    text = { Text("Thanks for Voting to $selectedOption") },
                    confirmButton = {
                        Button(
                            onClick = {
                                selectedOption = ""
                                isSubmit = false
                            }
                        , modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Ok")
                        }
                    }
                )
            }

        }

    }
}