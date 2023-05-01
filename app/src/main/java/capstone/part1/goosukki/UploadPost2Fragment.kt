package capstone.part1.goosukki

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import androidx.activity.result.contract.ActivityResultContracts.*
import capstone.part1.goosukki.databinding.FragmentUploadPost2Binding
import com.google.android.material.tabs.TabLayoutMediator

// 먼저 갤러리 띄운 후 이미지 및 추가
class UploadPost2Fragment : Fragment(R.layout.fragment_upload_post2) {

    private lateinit var binding : FragmentUploadPost2Binding
    private lateinit var frameAdapter: FrameAdapter

    // 선택된 이미지들 배열에 넣기
    private var uriList: List<Uri> = listOf()

    // 사진 선택 도구 (Android 11 즉 API 30 이상부터 실행, 그 이하는 ContextProvider)
    // Registers a photo picker activity launcher in multi-select mode.
    // In this example, the app allows the user to select up to 5 media files.
    private val pickMultipleMedia =
        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { uris ->
            // Callback is invoked after the user selects media items or closes the
            // photo picker.
            if (uris.isNotEmpty()) {
                // uri 리스트에 값이 있을 경우
                uriList = uris.toList()
                frameAdapter.submitList(uriList.map { ImageItems.Image(it) } + ImageItems.LoadMore)
                Log.d("PhotoPicker", "Number of items selected: ${uris.size}")
            } else {
                // uri 리스트에 값이 없을 경우
                Log.d("PhotoPicker", "No media selected")
            }
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUploadPost2Binding.bind(view)

        // Launch the photo picker and allow the user to choose images and videos.
        pickMultipleMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))

        // toolbar 뒤로가기 버튼 클릭 시 fragment1로 전환
        binding.backFragment2to1Button.setOnClickListener {
            findNavController().navigate(UploadPost2FragmentDirections.actionBack())
        }

        // frameAdapter 생성
        frameAdapter = FrameAdapter(object : FrameAdapter.ItemClickListener {
            override fun onLoadMoreClick() {
                Log.d("UploadPost2", "onLoadMoreClick")
            }
        })

        // viewpager 설정
        binding.viewPager.adapter = frameAdapter

        // viewpager와 tablayout 연결
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager,
        ) {
            tab, position ->
            binding.viewPager.currentItem = tab.position
        }.attach()
    }

}



