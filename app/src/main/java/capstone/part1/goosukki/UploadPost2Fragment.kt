package capstone.part1.goosukki

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import capstone.part1.goosukki.databinding.FragmentUploadPost2Binding

// 갤러리 띄우기
class UploadPost2Fragment : Fragment(R.layout.fragment_upload_post2) {

    private lateinit var binding : FragmentUploadPost2Binding

    // 사진 선택 도구 (Android 11 즉 API 30 이상부터 실행, 그 이하는 ContextProvider)
    // Registers a photo picker activity launcher in multi-select mode.
    // In this example, the app allows the user to select up to 5 media files.
    val pickMultipleMedia =
        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { uris ->
            // Callback is invoked after the user selects media items or closes the
            // photo picker.
            if (uris.isNotEmpty()) {
                Log.d("PhotoPicker", "Number of items selected: ${uris.size}")
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUploadPost2Binding.bind(view)

        // Launch the photo picker and allow the user to choose images and videos.
        pickMultipleMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
    }
}

private fun checkPermission() {

}