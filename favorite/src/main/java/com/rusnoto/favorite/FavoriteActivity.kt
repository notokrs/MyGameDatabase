package com.rusnoto.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.rusnoto.core.domain.model.GameDetailModel
import com.rusnoto.core.ui.FavGameAdapter
import com.rusnoto.core.utils.Helper.showProgressBar
import com.rusnoto.favorite.databinding.ActivityFavoriteBinding
import com.rusnoto.mygamedatabase.detail.DetailActivity
import com.rusnoto.mygamedatabase.di.FavoriteModuleDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var binding: ActivityFavoriteBinding
    private val favViewModel: FavoriteViewModel by viewModels {factory}

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            ).build().inject(this)

        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val favAdapter = FavGameAdapter()

        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        favViewModel.getGameFavorite.observe(this, { favGame ->
            if(favGame.isNotEmpty()){
                binding.errorHeader.visibility = View.GONE
                binding.errorSubHeader.visibility = View.GONE
                binding.rvFavGames.visibility = View.VISIBLE
                showProgressBar(binding.progressBar, false)
                favAdapter.setData(favGame)
            }else{
                showProgressBar(binding.progressBar, false)
                binding.rvFavGames.visibility = View.GONE
                binding.errorHeader.visibility = View.VISIBLE
                binding.errorSubHeader.visibility = View.VISIBLE
            }
        })

        with(binding.rvFavGames) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = favAdapter
        }

        favAdapter.setOnItemClickCallback(object : FavGameAdapter.OnItemClickCallback{
            override fun onItemClicked(data: GameDetailModel) {
                showDetailGame(data)
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun showDetailGame(data: GameDetailModel) {
        startActivity(
                Intent(
                        this, DetailActivity::class.java
                ).putExtra(DetailActivity.GAME_ID, data.gameId)
        )
    }
}