package com.rusnoto.mygamedatabase.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rusnoto.core.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(gameUseCase: GameUseCase): ViewModel(){
    val gameList = gameUseCase.getNewRelease().asLiveData()
}