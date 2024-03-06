package id.bobipermanasandii.schaute

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.bobipermanasandii.schaute.databinding.ItemRowMovieBinding

class ListMovieAdapter(private val listMovie: ArrayList<Movie>) : RecyclerView.Adapter<ListMovieAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, overview, poster) = listMovie[position]
        Glide.with(holder.itemView.context)
            .load(poster)
            .into(holder.binding.imgPoster)
        holder.binding.tvTitle.text = title
        holder.binding.tvOverview.text = overview
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listMovie[holder.adapterPosition]) }
    }


    override fun getItemCount(): Int = listMovie.size

    class ListViewHolder(var binding: ItemRowMovieBinding) : RecyclerView.ViewHolder(binding.root)


    interface OnItemClickCallback {
        fun onItemClicked(data: Movie)
    }
}