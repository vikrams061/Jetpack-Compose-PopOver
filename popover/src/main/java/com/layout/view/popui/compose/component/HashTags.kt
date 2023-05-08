package com.layout.view.popui.compose.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.layout.popover.R
import com.layout.view.popui.compose.theme.SkylabTheme


@Composable
fun HashTag(
    title: String,
    isSelected: Boolean,
    onHashtagPressed: (String) -> Unit,
) {
    Surface(
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 1.dp, end = 1.dp),
        elevation = 0.dp,
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(
            width = 0.5.dp,
            color = when {
                isSelected -> colorResource(androidx.constraintlayout.widget.R.color.abc_background_cache_hint_selector_material_dark)
                else -> colorResource(androidx.constraintlayout.widget.R.color.abc_background_cache_hint_selector_material_dark)
            }
        )
    ) {
        Row(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onHashtagPressed(title)
                }
            )
        ) {
            Text(
                text = title,
                color = Color.Black,
                modifier = Modifier
                    .padding(vertical = 3.dp, horizontal = 8.dp)

            )
        }
    }
}


@Preview
@Composable
internal fun HashTagPreview() {
    SkylabTheme {
        HashTag(
            title = "#Cat", false
        ) {}
    }
}

@Preview
@Composable
internal fun ShimmerHashTagsPreview() {
    SkylabTheme {
        ShimmerHashTags()
    }
}


@Composable
fun ShimmerHashTags() {

    //These colors will be used on the brush. The lightest color should be in the middle

    val gradient = listOf(
        Color.LightGray.copy(alpha = 0.9f), //darker grey (90% opacity)
        Color.LightGray.copy(alpha = 0.3f), //lighter grey (30% opacity)
        Color.LightGray.copy(alpha = 0.9f)
    )

    val transition = rememberInfiniteTransition() // animate infinite times

    val translateAnimation = transition.animateFloat( //animate the transition
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000, // duration for the animation
                easing = FastOutLinearInEasing
            )
        )
    )
    val brush = Brush.linearGradient(
        colors = gradient,
        start = Offset(200f, 200f),
        end = Offset(
            x = translateAnimation.value,
            y = translateAnimation.value
        )
    )

    SkylabTheme {
        ShimmerHashTagsItems(brush = brush)
    }
}

@Composable
fun ShimmerHashTagsItems(brush: Brush) {
    Row {

        Spacer(
            modifier = Modifier
                .width(10.dp)
        )

        Spacer(
            modifier = Modifier
                .height(25.dp)
                .width(100.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(brush)
        )

        Spacer(
            modifier = Modifier
                .width(10.dp)
        )
    }
}


//@Composable
//fun ShimmerDiscoverGrid() {
//
//    //These colors will be used on the brush. The lightest color should be in the middle
//
//    val gradient = listOf(
//        Color.LightGray.copy(alpha = 0.9f), //darker grey (90% opacity)
//        Color.LightGray.copy(alpha = 0.3f), //lighter grey (30% opacity)
//        Color.LightGray.copy(alpha = 0.9f)
//    )
//
//    val transition = rememberInfiniteTransition() // animate infinite times
//
//    val translateAnimation = transition.animateFloat( //animate the transition
//        initialValue = 0f,
//        targetValue = 1000f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(
//                durationMillis = 1000, // duration for the animation
//                easing = FastOutLinearInEasing
//            )
//        )
//    )
//    val brush = Brush.linearGradient(
//        colors = gradient,
//        start = Offset(200f, 200f),
//        end = Offset(
//            x = translateAnimation.value,
//            y = translateAnimation.value
//        )
//    )
//
//    ShimmerGridLayouts(brush)
//
//}
//
//
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun ShimmerGridLayouts(brush: Brush) {
//    LazyVerticalGrid(
//        cells = GridCells.Fixed(3),
//        verticalArrangement = Arrangement.spacedBy(2.dp),
//        horizontalArrangement = Arrangement.spacedBy(2.dp),
//        modifier = Modifier.fillMaxSize(),
//    ) {
//        items(20) {
//            ShimmerGridItems(brush)
//        }
//    }
//}
//
//
//@Composable
//fun ShimmerGridItems(brush: Brush) =
//    Box(modifier = Modifier) {
//        Spacer(
//            modifier = Modifier
//                .height(150.dp)
//                .fillMaxWidth()
//                .background(brush)
//        )
//    }
//
//@Preview
//@Composable
//internal fun ShimmerDiscoverPreview() {
//    AugustaTheme {
//        ShimmerDiscoverGrid()
//    }
//}

@Preview
@Composable
internal fun ShimmerSingleDiscoverGridPreview() {
    SkylabTheme {
        ShimmerSingleDiscoverGrid()
    }
}

@Composable
fun ShimmerSingleDiscoverGrid() {

    //These colors will be used on the brush. The lightest color should be in the middle

    val gradient = listOf(
        Color.LightGray.copy(alpha = 0.9f), //darker grey (90% opacity)
        Color.LightGray.copy(alpha = 0.3f), //lighter grey (30% opacity)
        Color.LightGray.copy(alpha = 0.9f)
    )

    val transition = rememberInfiniteTransition() // animate infinite times

    val translateAnimation = transition.animateFloat( //animate the transition
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000, // duration for the animation
                easing = FastOutLinearInEasing
            )
        )
    )
    val brush = Brush.linearGradient(
        colors = gradient,
        start = Offset(200f, 200f),
        end = Offset(
            x = translateAnimation.value,
            y = translateAnimation.value
        )
    )

    Column {
        ShimmerGridItem(brush)
        ShimmerGridItem(brush)
        ShimmerGridItem(brush)
        ShimmerGridItem(brush)
        ShimmerGridItem(brush)
        ShimmerGridItem(brush)
    }


}

@Composable
fun ShimmerGridItem(brush: Brush) =
    Row(modifier = Modifier) {
        Spacer(
            modifier = Modifier
                .weight(1f)
                .height(150.dp)
                .fillMaxWidth()
                .background(brush)
        )

        Spacer(
            modifier = Modifier
                .width(2.dp)
                .height(150.dp)
        )
        Spacer(
            modifier = Modifier
                .weight(1f)
                .height(150.dp)
                .fillMaxWidth()
                .background(brush)
        )


        Spacer(
            modifier = Modifier
                .width(2.dp)
                .height(150.dp)
        )
        Spacer(
            modifier = Modifier
                .weight(1f)
                .height(150.dp)
                .fillMaxWidth()
                .background(brush)
        )
    }


@Preview
@Composable
internal fun ShimmerSingleDiscoverPostPreview() {
    SkylabTheme {
        ShimmerSingleDiscoverPost()
    }
}

@Composable
fun ShimmerSingleDiscoverPost() {

    //These colors will be used on the brush. The lightest color should be in the middle

    val gradient = listOf(
        Color.LightGray.copy(alpha = 0.9f), //darker grey (90% opacity)
        Color.LightGray.copy(alpha = 0.3f), //lighter grey (30% opacity)
        Color.LightGray.copy(alpha = 0.9f)
    )

    val transition = rememberInfiniteTransition() // animate infinite times

    val translateAnimation = transition.animateFloat( //animate the transition
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000, // duration for the animation
                easing = FastOutLinearInEasing
            )
        )
    )
    val brush = Brush.linearGradient(
        colors = gradient,
        start = Offset(200f, 200f),
        end = Offset(
            x = translateAnimation.value,
            y = translateAnimation.value
        )
    )

    Column {
        ShimmerDiscoverPostItem(brush)
        ShimmerDiscoverPostItem(brush)
        ShimmerDiscoverPostItem(brush)
    }


}

@Composable
fun ShimmerDiscoverPostItem(brush: Brush) =
    Column {

        Spacer(modifier = Modifier.size(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.size(8.dp))


            Spacer(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(brush)
            )


            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            ) {

                Spacer(
                    modifier = Modifier
                        .width(300.dp)
                        .height(20.dp)
                        .background(brush)
                )

                Spacer(modifier = Modifier.size(8.dp))

                Spacer(
                    modifier = Modifier
                        .width(300.dp)
                        .height(15.dp)
                        .background(brush)
                )

            }
        }

        Spacer(modifier = Modifier.size(8.dp))


        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp)
                .background(brush)
        )


        Spacer(modifier = Modifier.size(8.dp))

        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
        ) {

            Spacer(
                modifier = Modifier
                    .width(300.dp)
                    .height(40.dp)
                    .weight(1f)
                    .background(brush)
            )

            Spacer(modifier = Modifier.size(8.dp))

            Spacer(
                modifier = Modifier
                    .width(300.dp)
                    .height(40.dp)
                    .weight(1f)
                    .background(brush)
            )

            Spacer(modifier = Modifier.size(8.dp))

            Spacer(
                modifier = Modifier
                    .width(300.dp)
                    .height(40.dp)
                    .weight(1f)
                    .background(brush)
            )

            Spacer(modifier = Modifier.size(8.dp))

            Spacer(
                modifier = Modifier
                    .width(300.dp)
                    .height(40.dp)
                    .weight(1f)
                    .background(brush)
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

    }


@Composable
fun NoRecordsFound(){

    Box(
        modifier = Modifier
            .height(500.dp)
            .fillMaxSize(),
    ) {
        Text(
            color = Color.Black,
            text = stringResource(id = R.string.no_match_found),
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}

@Preview
@Composable
internal fun NoRecordsFoundPreview() {
    SkylabTheme {
        NoRecordsFound()
    }
}


