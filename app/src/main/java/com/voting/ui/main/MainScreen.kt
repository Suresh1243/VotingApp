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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.voting.R
import com.voting.routing.Screen
import com.voting.ui.drawer.DrawerBody
import com.voting.ui.drawer.DrawerHeader
import com.voting.ui.drawer.TopBar
import com.voting.ui.localDatabase.VotingLocalDataBase
import com.voting.ui.model.CandidateModel
import com.voting.ui.theme.VotingAppTheme
import com.voting.ui.theme.purple
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
    val scope =  rememberCoroutineScope()
    var isLogout by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val list = arrayListOf<CandidateModel>().apply {
        add(CandidateModel(name = "Conservative and Unionist Party", mobile = "9876543210"))
        add(CandidateModel(name = "Labour Party", mobile = "9876543210"))
        add(CandidateModel(name = "Liberal Democrats", mobile = "9876543210"))
        add(CandidateModel(name = "Green Party of England and Wales", mobile = "9876543210"))
        add(CandidateModel(name = "Brexit Party", mobile = "9876543210"))
        add(CandidateModel(name = "Independent", mobile = "9876543210"))
        add(CandidateModel(name = "Scottish National Party", mobile = "9876543210"))
        add(CandidateModel(name = "UKIP", mobile = "9876543210"))
        add(CandidateModel(name = "Plaid Cymru", mobile = "9876543210"))
        add(CandidateModel(name = "Yorkshire Party", mobile = "9876543210"))
        add(CandidateModel(name = "Christian Peoples Alliance", mobile = "9876543210"))
        add(CandidateModel(name = "Official Monster Raving Loony Party", mobile = "9876543210"))
        add(CandidateModel(name = "Scottish Green Party", mobile = "9876543210"))
        add(CandidateModel(name = "Social Democratic Party", mobile = "9876543210"))
        add(CandidateModel(name = "Liberal Party", mobile = "9876543210"))
        add(CandidateModel(name = "Alliance Party of Northern Ireland", mobile = "9876543210"))
    }


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
                bottom = paddingValues.calculateBottomPadding())
            Column(
                modifier = Modifier
                    .background(color = purple)
                    .verticalScroll(scrollState)
            ) {
                Spacer(Modifier.height(10.dp))

                Column {
                    list.forEachIndexed { index, candidateModel ->
                        Card(
                            modifier = Modifier
                                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                                .fillMaxWidth()
                                .height(280.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                        ) {
                            Spacer(Modifier.height(10.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_vote),
                                    contentDescription = "Image",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .width(40.dp)
                                        .height(40.dp)
                                )
                                Column {
                                    Text(
                                        candidateModel.name ?: "",
                                        fontSize = 14.sp,
                                        color = Color.Black,
                                        modifier = Modifier
                                            .padding(vertical = 5.dp, horizontal = 10.dp)
                                    )
                                    Text(
                                        candidateModel.mobile ?: "",
                                        fontSize = 14.sp,
                                        color = Color.Black,
                                        modifier = Modifier
                                            .padding(vertical = 5.dp, horizontal = 10.dp)
                                    )
                                }
                            }
                            Spacer(Modifier.height(10.dp))
                            Divider(
                                thickness = 1.5.dp,
                                color = Color.Gray,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(Modifier.height(10.dp))
                            val isChecked = remember { mutableStateOf(false) }

                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    /* list.forEachIndexed { index, candidateModel ->
                                        candidateModel.selectedValue = false
                                    }
                                    candidateModel.selectedValue = true*/
                                    isChecked.value = !isChecked.value
                                }, verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = isChecked.value,
                                    onCheckedChange = {
                                        /*list.forEachIndexed { index, candidateModel ->
                                            candidateModel.selectedValue = false
                                        }
                                        candidateModel.selectedValue = true*/
                                        isChecked.value = !isChecked.value
                                    }
                                )
                                Text("Ballot", modifier = Modifier.fillMaxWidth())
                            }
                            Spacer(Modifier.height(10.dp))
                            Divider(
                                thickness = 1.5.dp,
                                color = Color.Gray,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(Modifier.height(10.dp))
                            Box(Modifier.padding(15.dp)) {
                                RoundedBackgroundButton(
                                    text = "Continue",
                                    onClick = {

                                    }
                                )
                            }
                        }
                    }
                }
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


    }

}