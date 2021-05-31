package com.rusnoto.mygamedatabase.detail

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rusnoto.core.data.Resource
import com.rusnoto.core.domain.model.GameDetailModel
import com.rusnoto.core.utils.Helper.setImage
import com.rusnoto.core.utils.Helper.showProgressBar
import com.rusnoto.mygamedatabase.R
import com.rusnoto.mygamedatabase.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val gameId = intent.getIntExtra(GAME_ID, 0)
        detailViewModel.getGameDetail(gameId).observe(this, { game ->
            if(game.data?.name?.isNotEmpty() == true){
                when(game){
                    is Resource.Loading -> {
                        showProgressBar(binding.progressBar, true)
                    }
                    is Resource.Success -> {
                        showProgressBar(binding.progressBar, false)
                        setDetailLayout(game)
                        checkFavorite(game)

                        binding.fabFavGame.setOnClickListener{
                            setFavorite(game)
                        }
                    }
                    is Resource.Error -> {
                        showProgressBar(binding.progressBar, false)
                        showToast(resources.getString(R.string.error_get_games))
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.favorite_games){
            val uri = Uri.parse("mygamedatabase://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun setDetailLayout(game: Resource.Success<GameDetailModel>) {
        binding.ivGamesPosterDetail.setImage(game.data?.poster)
        binding.tvGameNameDetail.text = game.data?.name
        binding.tvPlatformDetail.text = game.data?.platform
        binding.tvMetascoreDetail.text = game.data?.score
        binding.tvGenreDetail.text = game.data?.genre
        binding.tvReleaseDateDetail.text = game.data?.releaseDate
        binding.tvDeveloperDetail.text = game.data?.developer

        binding.tvAboutDetail.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(game.data?.about, Html.FROM_HTML_MODE_COMPACT)
        } else {
            @Suppress("DEPRECATION")
            Html.fromHtml(game.data?.about)
        }
    }

    private fun setFavorite(game: Resource.Success<GameDetailModel>) =
        if(game.data?.isFavorite == true){
            detailViewModel.setGameFavorite(game.data, false)
            binding.fabFavGame.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            showToast(resources.getString(R.string.remove_from_fav, game.data?.name))
        }else{
            game.data?.let { detailViewModel.setGameFavorite(it,true) }
            binding.fabFavGame.setImageResource(R.drawable.ic_baseline_favorite_24)
            showToast(resources.getString(R.string.add_to_fav, game.data?.name))
        }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun checkFavorite(game: Resource.Success<GameDetailModel>) {
        if(game.data?.isFavorite == true){
            binding.fabFavGame.setImageResource(R.drawable.ic_baseline_favorite_24)
        }else{
            binding.fabFavGame.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    companion object{
        const val GAME_ID = "game_id"
    }
}