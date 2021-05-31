package com.rusnoto.core.utils

import com.rusnoto.core.data.source.local.entity.GameDetailEntity
import com.rusnoto.core.data.source.local.entity.NewReleaseEntity
import com.rusnoto.core.data.source.remote.response.GameDetailResponse
import com.rusnoto.core.data.source.remote.response.NewReleaseResponse
import com.rusnoto.core.domain.model.GameDetailModel
import com.rusnoto.core.domain.model.GameListModel
import com.rusnoto.core.utils.Helper.developerExtractor
import com.rusnoto.core.utils.Helper.genreExtractor
import com.rusnoto.core.utils.Helper.platformExtractor

object DataMapper {
	fun mapListResponsesToListEntities(input: NewReleaseResponse): List<NewReleaseEntity>{
	    val gameList = ArrayList<NewReleaseEntity>()
        input.results.map {
            val game = NewReleaseEntity(
                id = null,
                gameid = it.id,
                gameName = it.name,
                gamePoster = it.backgroundImage,
            )
            gameList.add(game)
        }
        return gameList
	}

    fun mapListEntitiesToListDomain(input: List<NewReleaseEntity>): List<GameListModel> =
        input.map {
            GameListModel(
                gameId = it.gameid,
                gameName = it.gameName,
                gamePoster = it.gamePoster
            )
        }

	fun mapDetailResponseToDetailEntity(input: GameDetailResponse): GameDetailEntity =
			GameDetailEntity(
					id = null,
					gameid = input.id,
					name = input.name,
					poster = input.backgroundImage,
					platform = platformExtractor(input.platforms),
					score = (input.metacritic ?: "Not Rated yet").toString(),
					genre = genreExtractor(input.genres),
					developer = developerExtractor(input.developers),
					about = input.description,
					releaseDate = (input.released ?: "TBA"),
					isFavorite = false
			)

	fun mapDetailEntitiesToDetailDomain(input: GameDetailEntity?): GameDetailModel =
			GameDetailModel(
					id = input?.id,
					gameId = input?.gameid,
					name = input?.name,
					poster = input?.poster,
					platform = input?.platform,
					score = input?.score,
					genre = input?.genre,
					developer = input?.developer,
					releaseDate = input?.releaseDate,
					about = input?.about,
					isFavorite = input?.isFavorite
			)

	fun mapDetailDomainToDetailEntity(input: GameDetailModel): GameDetailEntity =
		GameDetailEntity(
				id = input.id,
				gameid = input.gameId,
				name = input.name,
				poster = input.poster,
				platform = input.platform,
				score = input.score,
				genre = input.genre,
				developer = input.developer,
				releaseDate = input.releaseDate,
				about = input.about,
				isFavorite = input.isFavorite
		)

	fun mapDetailEntityToListModel(input: List<GameDetailEntity>?): List<GameDetailModel> {
		val favList = ArrayList<GameDetailModel>()
		input?.map {
			val gameList = GameDetailModel(
					id = it.id,
					gameId = it.gameid,
					name = it.name,
					poster = it.poster,
					platform = it.platform,
					score = it.score,
					genre = it.genre,
					developer = it.developer,
					releaseDate = it.releaseDate,
					about = it.about,
					isFavorite = it.isFavorite
			)
			favList.add(gameList)
		}
		return favList
	}

}
