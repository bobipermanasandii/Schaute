package id.bobipermanasandii.schaute

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.bobipermanasandii.schaute.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Movie>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovies.setHasFixedSize(true)

        list.addAll(getListMovie())
        showRecyclerList()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                binding.rvMovies.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                binding.rvMovies.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.action_profile -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListMovie(): ArrayList<Movie> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataOverview = resources.getStringArray(R.array.data_overview)
        val dataPoster = resources.getStringArray(R.array.data_poster_path)

        val listMovie = ArrayList<Movie>()
        for (i in dataTitle.indices) {
            val movie = Movie(dataTitle[i], dataOverview[i], dataPoster[i])
            listMovie.add(movie)
        }
        return listMovie
    }

    private fun showRecyclerList() {
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        val listMovieAdapter = ListMovieAdapter(list)
        binding.rvMovies.adapter = listMovieAdapter

        listMovieAdapter.setOnItemClickCallback(object : ListMovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Movie) {
                showSelectedMovie(data)
            }
        })
    }

    private fun showSelectedMovie(movie: Movie) {
//        Toast.makeText(this, "Kamu memilih " + movie.title, Toast.LENGTH_SHORT).show()

        val intentDetail = Intent(this, DetailActivity::class.java)
        intentDetail.putExtra("key_movie", movie)
        startActivity(intentDetail)
    }
}