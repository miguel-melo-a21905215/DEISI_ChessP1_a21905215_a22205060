package pt.ulusofona.lp2.deisichess

class StatisticsKt {
    companion object {
        @JvmStatic
        fun getStatsCalculator(type: StatType): (GameManager) -> ArrayList<String> {
            return GameManager::holderMethod

        }
    }

    fun getTopPontos (gameManager: GameManager): ArrayList<String> {

        val pieces = gameManager.board.totalPieces
            .filter { it.pointsWorth >= 1 }
            .sortedByDescending { it.nickname }
            .sortedByDescending { it.numCapturas }
            .take(5)
            .map { piece -> "${piece.nickname} (${piece.team}) tem ${piece.getNumCapturas()} capturas"  }
            .toCollection(ArrayList())

        return pieces;


    }




}