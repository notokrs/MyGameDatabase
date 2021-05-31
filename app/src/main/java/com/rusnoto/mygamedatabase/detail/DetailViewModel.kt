package com.rusnoto.mygamedatabase.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rusnoto.core.domain.model.GameDetailModel
import com.rusnoto.core.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val gameUseCase: GameUseCase): ViewModel(){
	fun getGameDetail(gameId: Int) = gameUseCase.getDetailGame(gameId).asLiveData()
	fun setGameFavorite(game: GameDetailModel?, status: Boolean) =
		game?.let { gameUseCase.setGameFavorite(it, status) }
}