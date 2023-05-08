package com.layout.popover

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.layout.view.popui.compose.PostText
import com.layout.popover.ui.theme.PopOverTheme
import com.layout.view.popui.AnchorEdge
import com.layout.view.popui.Tooltip
import com.layout.view.popui.TooltipStyle
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PopOverTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = "Popup Window Dialog",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            )
                        }
                    ) {
                        PopupWindowDialog()
                    }
                }
            }
        }
    }
}



@Composable
fun PopupWindowDialog() {
    val openDialog = remember { mutableStateOf(false) }
    val (expanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box {
            Button(
                onClick = {
                    openDialog.value = !openDialog.value

                }
            ) {
                Text(text = "Click")
            }
            Tooltip(
                tooltipStyle = TooltipStyle(
                    color = Color.LightGray,
                    cornerRadius = 4.dp,
                    tipHeight = 8.dp,
                    tipWidth = 24.dp,
                    contentPadding = PaddingValues(12.dp)),
                anchorEdge = AnchorEdge.Top,
                visible = openDialog.value,
            ) {
                PostText(
                    linkUrl = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.justdial.com%2Fjdmart%2FCoimbatore%2FAugusta-Hitech-Soft-Solutions-Pvt-Ltd-Eachanari%2F0422PX422-X422-181204080840-G3Y6_BZDET%2Fcatalogue&psig=AOvVaw0IjxXeJKadgGn4zVXlFlaG&ust=1681450241030000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCLiDyLSQpv4CFQAAAAAdAAAAABAD",
                    linkTitle = "Augusta",
                    linkDesc = "Augusta Hi Tech",
                    text = " #name vikram @augusta movement After moving https://www.google.com/ away for a decade, Rhea returns to the nherquestforindependence. Things are looking bleak, until she bumps into a familiar face - one she didn  think she would ever see again. Feelings rekindle as secrets begin to unfold, Rhea soon realizes her childhood friend may not be who she believes him to be, and thus her most cherished relationship is put to the test.",
                    onHashtagPressed = { hashtag ->
                        Toast.makeText(context, "$hashtag", Toast.LENGTH_LONG).show()
                    },
                    onMentionPressed = { mention ->
                        Toast.makeText(context, "$mention", Toast.LENGTH_LONG).show()
                    },
                    onPostPressed = {
                        Toast.makeText(context, "Post Pressed ", Toast.LENGTH_LONG).show()
                    },
                    onLinkPressed = { link ->
                        Toast.makeText(context, "$link", Toast.LENGTH_LONG).show()
                    },
                )





//                BasicReadMoreText(
//                    text = stringResource(id = R.string.description_reunion),
//                    expanded = expanded,
//                    onExpandedChange = onExpandedChange,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 18.dp, top = 5.dp, end = 18.dp, bottom = 18.dp),
//                    style = TextStyle.Default.copy(
//                        color = MaterialTheme.colors.onSurface,
//                        fontSize = 15.sp,
//                        fontStyle = FontStyle.Normal,
//                        lineHeight = 22.sp,
//                    ),
//                    readMoreMaxLines = 3,
//                    readMoreText = stringResource(id = R.string.read_more),
//                    readMoreStyle = SpanStyle(
//                        color = MaterialTheme.colors.primary,
//                        fontSize = 12.sp,
//                        fontWeight = FontWeight.Bold,
//                        textDecoration = TextDecoration.Underline,
//                    ),
//                    readLessText = stringResource(id = R.string.read_less),
//                )
            }
        }
    }


//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 40.dp),
//        horizontalAlignment = Alignment.End,
//    ) {
//        Button(
//            onClick = {
//                openDialog2.value = !openDialog2.value
//            }
//        ) {
//            Text(text = "Click")
//        }
//        Tooltip(
//            anchorEdge = AnchorEdge.Bottom,
//            visible = openDialog2.value,
//        ) {
//            Text("Augusta popover!")
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 20.dp),
//        horizontalAlignment = Alignment.Start,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Button(
//            onClick = {
//                openDialog3.value = !openDialog3.value
//
//            }
//        ) {
//            Text(text = "Click")
//        }
//        Tooltip(
//            anchorEdge = AnchorEdge.End,
//            visible = openDialog3.value,
//        ) {
//            Text("Augusta popover!")
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 20.dp),
//        horizontalAlignment = Alignment.End,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Button(
//            onClick = {
//                openDialog4.value = !openDialog4.value
//
//            }
//        ) {
//            Text(text = "Click")
//        }
//        Tooltip(
//            anchorEdge = AnchorEdge.Start,
//            visible = openDialog4.value,
//        ) {
//            Text("Augusta popover!")
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 20.dp),
//        horizontalAlignment = Alignment.Start,
//        verticalArrangement = Arrangement.Bottom
//    ) {
//        Button(
//            onClick = {
//                openDialog5.value = !openDialog5.value
//
//            }
//        ) {
//            Text(text = "Click")
//        }
//        Tooltip(
//            anchorEdge = AnchorEdge.Top,
//            visible = openDialog5.value,
//        ) {
//            Text("Augusta popover!")
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 20.dp),
//        horizontalAlignment = Alignment.End,
//        verticalArrangement = Arrangement.Bottom
//    ) {
//        Button(
//            onClick = {
//                openDialog6.value = !openDialog6.value
//
//            }
//        ) {
//            Text(text = "Click")
//        }
//        Tooltip(
//            anchorEdge = AnchorEdge.Bottom,
//            visible = openDialog6.value,
//        ) {
//            Text("Augusta popover!")
//        }
//    }


}


