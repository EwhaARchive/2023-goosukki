package capstone.part1.goosukki

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import capstone.part1.goosukki.databinding.ItemImageBinding
import capstone.part1.goosukki.databinding.ItemLoadMoreBinding

// FrameAdapter : 2번째 fragment (이미지 추가, 캡션 추가)에서 viewpager2 view에 이미지 추가해줌
// ListAdapter<> : item type, viewholder type
// DiffUtil : 아이템이 변경되었는지 확인

class FrameAdapter(private val itemClickListener: ItemClickListener) : ListAdapter<ImageItems, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<ImageItems>() {
        override fun areItemsTheSame(oldItem: ImageItems, newItem: ImageItems): Boolean {
            // 같은 객체 참조하고 있는지 확인
            return oldItem === newItem

        }

        override fun areContentsTheSame(oldItem: ImageItems, newItem: ImageItems): Boolean {
            // 내용이 같은지 확인
            return oldItem == newItem
        }

    }
){

    // footer (viewpager 맨 마지막)에만 loadMore 추가하기 위한 item count
    override fun getItemCount(): Int {
        val originSize = currentList.size
//        return currentList.size
        return if (originSize == 0) 0 else originSize
    }

    // 아이템 타입이 2개이므로, 아이템 타입 체크
    override fun getItemViewType(position: Int): Int {
        // 이미지가 2개 있다 가정 -> 이미지는 0&1번째 요소, loadMore은 2번째 요소, itemCount는 3
        // 따라서 itemCount에서 1을 빼준 값이 position이면 loadMore
        return if (itemCount.dec() == position) ITEM_LOAD_MORE else ITEM_IMAGE
    }

    //
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // viewholder 만드는 함수
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return when(viewType) {
            ITEM_IMAGE -> {
                val binding = ItemImageBinding.inflate(inflater, parent, false)
                ImageViewHolder(binding)
            } else -> {
                val binding = ItemLoadMoreBinding.inflate(inflater, parent, false)
                LoadMoreViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // 해당 화면에 갔을 때 데이터와 화면을 연결해주는 함수
        when(holder) {
            // ImageItems라는 seales class에서 모든 요소 가져옴 - else 처리 안해도 됨
            // (video 추가할 경우 액자 UI (1) 강의 참고)
            is ImageViewHolder -> {
//                holder.bind(currentList[position] as ImageItems.Image)
                // Check if the item at the specified position is not null
                val item = currentList.getOrNull(position) as? ImageItems.Image
                if (item != null) {
                    holder.bind(item)
                }
            }
            is LoadMoreViewHolder -> {
                holder.bind(itemClickListener)
            }
        }
    }

    interface ItemClickListener {
        fun onLoadMoreClick() {}
    }

    companion object {
        const val ITEM_IMAGE = 0
        const val ITEM_LOAD_MORE = 1
    }
}

// 아래 2개를 묶는 class
sealed class ImageItems {
    // 다양한 item을 넣기 위한 data class
    data class Image(
        val uri: Uri,
    ) : ImageItems()

    // 사진 추가하기 view - 이미지 하나씩만 추가할 것이기 때문에 object로 선언
    object LoadMore : ImageItems()
}

// ch 8 - 4 15분 정도
// 이미지 viewHolder
class ImageViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ImageItems.Image) {
        // 받은 이미지의 uri를 가져오기 - UploadPost2Fragment에서 이미지 배열 받은거 받은 다음에 여기에 넣어야할듯
        binding.previewImageView.setImageURI(item.uri)
    }
}

// 사진 추가하기 viewHolder
class LoadMoreViewHolder(binding: ItemLoadMoreBinding) : RecyclerView.ViewHolder(binding.root) {
    // 일단 데이터를 받아오는 건 없으므로 필요하면 넣기
    fun bind(itemClickListener: FrameAdapter.ItemClickListener?) {
        itemView.setOnClickListener {
            itemClickListener?.onLoadMoreClick()
        }
    }
}



