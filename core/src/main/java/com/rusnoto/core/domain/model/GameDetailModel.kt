package com.rusnoto.core.domain.model

data class GameDetailModel(
		val id: Int?,
		val gameId: Int?,
		val name: String?,
		val poster: String?,
		val platform: String?,
		val score: String?,
		val genre: String?,
		val developer: String?,
		val releaseDate: String?,
		val about: String?,
		val isFavorite: Boolean?
)
