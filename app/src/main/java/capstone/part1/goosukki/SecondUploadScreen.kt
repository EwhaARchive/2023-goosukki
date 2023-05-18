package capstone.part1.goosukki

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.Animatable
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.viewpager2.widget.ViewPager2
import capstone.part1.goosukki.ui.theme.BgGrey
import capstone.part1.goosukki.ui.theme.Nct
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch



@Composable
fun SecondUploadScreen(navController: NavController) {


    Scaffold(
        topBar = { AppBar("캡션 추가", onBackClicked = {}) },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = BgGrey,
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(vertical = it.calculateBottomPadding())
        ) {


            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(horizontal = 10.dp, vertical = 30.dp) // 여백 크기 조정
            ) {
                MultiplePhotoPicker()
            }

            Button(
                // 맨 위 Column에서 padding start 30.dp 이미 줌
                onClick = { navController.navigate("thirdScreen") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, bottom = 30.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Nct)
            ) {
                Text(text = "다음")
            }
        }
    }
}

@Composable
fun MultiplePhotoPicker() {

    var selectedImageUris by remember {
        mutableStateOf<List<Uri>>(emptyList())
    }

    val multiplePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { selectedImageUris = it }
    )

    val request = remember { PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly) }

    // 초기화된 ActivityResultLauncher를 사용하여 launch()를 호출
    LaunchedEffect(Unit) {
        multiplePhotoPicker.launch(request)
    }

    // ViewPager에 이미지 표시
    if (selectedImageUris.isNotEmpty()) {
        // 선택된 이미지가 있는 경우에만 ViewPager 표시
        HorizontalPagerWithImages(selectedImageUris)
    }
}



@OptIn(ExperimentalCoilApi::class, ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerWithImages(images: List<Uri>) {
    val pagerState = rememberPagerState()

    HorizontalPager(
        state = pagerState,
        pageCount = images.size,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)) { page ->
        val imageUri = images.getOrNull(page)
        if (imageUri != null) {
            val pageOffset = (page - pagerState.currentPage).coerceIn(-1, 1)

            val scale = animateFloatAsState(if (pageOffset == 0) 1f else 0.85f).value
            val alpha = animateFloatAsState(if (pageOffset == 0) 1f else 0.85f).value

            Box(
                Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 30.dp, vertical = 30.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                    }
            ) {
                Image(
                    painter = rememberImagePainter(imageUri),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxWidth().aspectRatio(1f, true)
                )
            }
        }
    }
}


