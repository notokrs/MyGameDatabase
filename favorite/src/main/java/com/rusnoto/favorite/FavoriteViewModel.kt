package com.rusnoto.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rusnoto.core.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(gameUseCase: GameUseCase): ViewModel() {
	val getGameFavorite = gameUseCase.getGameFavorite().asLiveData()
}