package com.rusnoto.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class NewReleaseResponse(
	@field:SerializedName("results")
	val results: List<ResultsItem>
)

data class ResultsItem(
	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("background_image")
	val backgroundImage: String
)
