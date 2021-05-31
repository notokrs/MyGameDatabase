package com.rusnoto.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameDetailResponse(
	@field:SerializedName("background_image")
	val backgroundImage: String,

	@field:SerializedName("developers")
	val developers: List<DevelopersItem>,

	@field:SerializedName("genres")
	val genres: List<GenresItem>,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("metacritic")
	val metacritic: Int?,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("released")
	val released: String?,

	@field:SerializedName("platforms")
	val platforms: List<PlatformsItem>
)

data class DevelopersItem(
	@field:SerializedName("name")
	val name: String
)

data class PlatformsItem(
	@field:SerializedName("platform")
	val platform: Platform
)

data class Platform(
		@field:SerializedName("name")
		val name: String
)

data class GenresItem(
	@field:SerializedName("name")
	val name: String
)
