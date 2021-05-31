package com.rusnoto.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "newRelease_tb")
data class NewReleaseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NotNull
    var id: Int?,

    @NotNull
    @ColumnInfo(name = "game_id")
    var gameid: Int,

    @NotNull
    @ColumnInfo(name = "game_name")
    var gameName: String,

    @ColumnInfo(name = "game_poster")
    var gamePoster: String
)