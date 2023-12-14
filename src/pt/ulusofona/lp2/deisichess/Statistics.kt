package pt.ulusofona.lp2.deisichess;

fun getStatsCalculator(estatisticas: StatType): (GameManager) -> ArrayList<String>? {

    when (estatisticas){
        StatType.TOP_5_CAPTURAS -> return :: getTopCapturas
        StatType.TOP_5_PONTOS -> return :: getTopPontos
        StatType.PECAS_MAIS_5_CAPTURAS -> return :: getTopPontos
        StatType.PECAS_MAIS_BARALHADAS -> return :: getTopPontos
        StatType.TIPOS_CAPTURADOS -> return :: getTopPontos
    }
}

fun getTopPontos(gameManager: GameManager): ArrayList<String> {

    val pieces = gameManager.board.totalPieces
            .filter { it.pointsWorth >= 1 }
            .sortedByDescending { it.nickname }
            .sortedByDescending { it.numCapturas }
            .take(5)
            .map { piece -> "${piece.nickname} (${piece.team}) tem ${piece.getNumCapturas()} capturas" }
            .toCollection(ArrayList())

    return pieces;


}

fun getTopCapturas(gameManager: GameManager): ArrayList<String> {
    val pieces = gameManager.board.totalPieces
            .filter { it.numCapturas > 0 }
            .sortedByDescending { it.nickname }
            .sortedByDescending { it.numCapturas }
            .take(5)
            .map { piece ->
                "${piece.nickname} (${if (piece.team == 1) "Branca" else "Preta"}) " +
                        "fez ${piece.numCapturas} capturas"
            }
            .toCollection(ArrayList())

    return pieces
}

