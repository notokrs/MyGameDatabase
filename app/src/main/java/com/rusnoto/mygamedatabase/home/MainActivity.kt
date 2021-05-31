package com.rusnoto.mygamedatabase.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.rusnoto.core.data.Resource
import com.rusnoto.core.domain.model.GameListModel
import com.rusnoto.core.ui.GameListAdapter
import com.rusnoto.core.utils.Helper.showProgressBar
import com.rusnoto.mygamedatabase.R
import com.rusnoto.mygamedatabase.databinding.ActivityMainBinding
import com.rusnoto.mygamedatabase.detail.DetailActivity
import com.rusnoto.mygamedatabase.detail.DetailActivity.Companion.GAME_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private val mainViewModel: MainViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val mainAdapter = GameListAdapter()

		supportActionBar?.title = ""

		mainViewModel.gameList.observe(this, { games ->
			if(games != null){
				when(games){
					is Resource.Loading -> {
						binding.errorHeader.visibility = View.GONE
						binding.errorSubHeader.visibility = View.GONE
						showProgressBar(binding.progressBar, true)
					}
					is Resource.Success -> {
						binding.errorHeader.visibility = View.GONE
						binding.errorSubHeader.visibility = View.GONE
						binding.rvNewRelease.visibility = View.VISIBLE
						showProgressBar(binding.progressBar, false)
						games.data?.let { mainAdapter.setData(it) }
					}
					is Resource.Error -> {
						showProgressBar(binding.progressBar, false)
						binding.rvNewRelease.visibility = View.GONE
						binding.errorHeader.visibility = View.VISIBLE
						binding.errorSubHeader.visibility = View.VISIBLE
					}
				}
			}
		})
		with(binding.rvNewRelease) {
			layoutManager = GridLayoutManager(context, 2)
			setHasFixedSize(true)
			adapter = mainAdapter
		}

		mainAdapter.setOnItemClickCallback(object : GameListAdapter.OnItemClickCallback{
			override fun onItemClicked(data: GameListModel) {
				showDetailGame(data)
			}

		})
	}

	private fun showDetailGame(data: GameListModel) {
		startActivity(
				Intent(
						this, DetailActivity::class.java
				).putExtra(GAME_ID, data.gameId)
		)
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
}