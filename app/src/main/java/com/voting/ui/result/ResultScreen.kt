package com.voting.ui.result

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
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
import com.voting.ui.model.CandidateModel
import com.voting.ui.model.ResultModel
import com.voting.ui.theme.VotingAppTheme
import com.voting.ui.theme.purple
import com.voting.utils.RoundedBackgroundButton

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun ResultScreen(navController: NavController) {
    val context = LocalContext.current
    val checked = remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val list = arrayListOf<ResultModel>().apply {
        add(
            ResultModel(
                name = "Conservative and Unionist Party",
                mobile = "9876543210",
                selectedValue = "",
                count = 10
            )
        )
        add(
            ResultModel(
                name = "Labour Party",
                mobile = "9876543210",
                selectedValue = "",
                count = 10
            )
        )
        add(
            ResultModel(
                name = "Liberal Democrats",
                mobile = "9876543210",
                selectedValue = "",
                count = 10
            )
        )
        add(
            ResultModel(
                name = "Green Party of England and Wales",
                mobile = "9876543210",
                selectedValue = "",
                count = 10
            )
        )
        add(
            ResultModel(
                name = "Brexit Party",
                mobile = "9876543210",
                selectedValue = "",
                count = 10
            )
        )
        add(
            ResultModel(
                name = "Independent",
                mobile = "9876543210",
                selectedValue = "",
                count = 10
            )
        )
        add(
            ResultModel(
                name = "Scottish National Party",
                mobile = "9876543210",
                selectedValue = "",
                count = 10
            )
        )
        add(
            ResultModel(
                name = "UKIP",
                mobile = "9876543210",
                selectedValue = "",
                count = 10
            )
        )
        add(
            ResultModel(
                name = "Plaid Cymru",
                mobile = "9876543210",
                selectedValue = "",
                count = 10
            )
        )
        add(
            ResultModel(
                name = "Yorkshire Party",
                mobile = "9876543210",
                selectedValue = "",
                count = 10
            )
        )
        add(
            ResultModel(
                name = "Christian Peoples Alliance",
                mobile = "9876543210",
                selectedValue = "",
                count = 10
            )
        )
        add(
            ResultModel(
                name = "Official Monster Raving Loony Party",
                mobile = "9876543210",
                selectedValue = "",
                count = 10
            )
        )
        add(
            ResultModel(
                name = "Scottish Green Party",
                mobile = "9876543210",
                selectedValue = "",
                count = 10
            )
        )
        add(
            ResultModel(
                name = "Social Democratic Party",
                mobile = "9876543210",
                selectedValue = "",
                count = 10
            )
        )
        add(
            ResultModel(
                name = "Liberal Party",
                mobile = "9876543210",
                selectedValue = "",
                count = 10
            )
        )
        add(
            ResultModel(
                name = "Alliance Party of Northern Ireland",
                mobile = "9876543210",
                selectedValue = "",
                count = 10
            )
        )
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
                            text = "Summary of votes for parties", color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.navigateUp()
                            }
                        ) {
                            Icon(imageVector = Icons.Rounded.ArrowBack,
                                tint = Color.White,
                                contentDescription = "Back")
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
                                .height(150.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                        ) {
                            Spacer(Modifier.height(10.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
//                                Image(
//                                    painter = painterResource(id = R.drawable.ic_vote),
//                                    contentDescription = "Image",
//                                    contentScale = ContentScale.Fit,
//                                    modifier = Modifier
//                                        .width(40.dp)
//                                        .height(40.dp)
//                                )
                                Column {
                                    Text(
                                        candidateModel.name ?: "",
                                        fontSize = 14.sp,
                                        color = Color.Black,
                                        modifier = Modifier
                                            .padding(vertical = 5.dp, horizontal = 10.dp)
                                    )
//                                    Text(
//                                        candidateModel.mobile ?: "",
//                                        fontSize = 14.sp,
//                                        color = Color.Black,
//                                        modifier = Modifier
//                                            .padding(vertical = 5.dp, horizontal = 10.dp)
//                                    )
                                }
                            }
                            Spacer(Modifier.height(10.dp))
                            Divider(
                                thickness = 1.5.dp,
                                color = Color.Gray,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(Modifier.height(10.dp))
                            Text(
                                "Vote : ${candidateModel.count ?: ""}",
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(vertical = 5.dp, horizontal = 10.dp)
                            )
                            Spacer(Modifier.height(10.dp))
                            Divider(
                                thickness = 1.5.dp,
                                color = Color.Gray,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(Modifier.height(10.dp))

                        }
                    }
                }
            }

        }


    }

}