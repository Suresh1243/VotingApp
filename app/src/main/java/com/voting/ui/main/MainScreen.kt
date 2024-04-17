package com.voting.ui.main

import android.annotation.SuppressLint
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.voting.R
import com.voting.routing.Screen
import com.voting.ui.model.CandidateModel
import com.voting.ui.theme.VotingAppTheme
import com.voting.ui.theme.purple
import com.voting.utils.RoundedButton

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val checked = remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val list = arrayListOf<CandidateModel>().apply {
        add(CandidateModel(name = "Test Candidate", mobile = "9876543210"))
        add(CandidateModel(name = "Test Candidate", mobile = "9876543210"))
        add(CandidateModel(name = "Test Candidate", mobile = "9876543210"))
        add(CandidateModel(name = "Test Candidate", mobile = "9876543210"))
        add(CandidateModel(name = "Test Candidate", mobile = "9876543210"))
        add(CandidateModel(name = "Test Candidate", mobile = "9876543210"))
        add(CandidateModel(name = "Test Candidate", mobile = "9876543210"))
        add(CandidateModel(name = "Test Candidate", mobile = "9876543210"))
        add(CandidateModel(name = "Test Candidate", mobile = "9876543210"))
        add(CandidateModel(name = "Test Candidate", mobile = "9876543210"))
        add(CandidateModel(name = "Test Candidate", mobile = "9876543210"))
    }


    VotingAppTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .background(color = purple)
                    .padding(top = 40.dp)
                    .verticalScroll(scrollState)
            ) {
                SmallTopAppBar(
                    title = {
                        Text(
                            text = "Home", color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = {
                            }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_result),
                                contentDescription = "Image",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .width(35.dp)
                                    .height(35.dp)
                                    .clickable {
                                        navController.navigate(Screen.ResultScreen.route)
                                    }
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = purple,
                        titleContentColor = Color.White
                    )
                )
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
                                RoundedButton(
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


    }

}