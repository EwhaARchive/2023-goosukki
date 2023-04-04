package capstone.part1.goosukki

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import capstone.part1.goosukki.databinding.FragmentUploadPost1Binding

// 제목, 공개여부 입력
class UploadPost1Fragment : Fragment(R.layout.fragment_upload_post1) {

    private lateinit var binding: FragmentUploadPost1Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUploadPost1Binding.bind(view)

        // 다음 버튼 클릭 시 갤러리 화면으로 이동
        binding.nextFragmentButton.setOnClickListener {
            val action =
                UploadPost1FragmentDirections.actionUploadPost1FragmentToUploadPost2Fragment()
            findNavController().navigate(action)
        }

    }

}
