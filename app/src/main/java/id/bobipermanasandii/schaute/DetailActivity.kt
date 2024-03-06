package id.bobipermanasandii.schaute

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import id.bobipermanasandii.schaute.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val KEY_MOVIE = "key_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataMovie = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(KEY_MOVIE, Movie::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(KEY_MOVIE)
        }

        if (dataMovie != null) {
            binding.tvDetailTitle.text = dataMovie.title
            binding.tvDetailOverview.text = dataMovie.overview
            Glide.with(this)
                .load(dataMovie.posterPath)
                .into(binding.imgDetailPoster)

        }

    }
}