package com.rusnoto.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "gameDetail_tb")
data class GameDetailEntity(
		@PrimaryKey(autoGenerate = true)
		@ColumnInfo(name = "id")
		@NotNull
		var id: Int?,

		@NotNull
		@ColumnInfo(name = "gameId")
		var gameid: Int?,

		@NotNull
		@ColumnInfo(name = "name")
		var name: String?,

		@ColumnInfo(name = "poster")
		var poster: String?,

		@ColumnInfo(name = "platform")
		var platform: String?,

		@ColumnInfo(name = "score")
		var score: String?,

		@ColumnInfo(name = "genre")
		var genre: String?,

		@ColumnInfo(name = "developer")
		var developer: String?,

		@ColumnInfo(name = "about")
		var about: String?,

		@ColumnInfo(name = "releaseDate")
		var releaseDate: String?,

		@ColumnInfo(name = "isFavorite")
		var isFavorite: Boolean?
)