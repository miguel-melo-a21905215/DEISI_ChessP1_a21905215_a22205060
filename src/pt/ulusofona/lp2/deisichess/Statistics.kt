package pt.ulusofona.lp2.deisichess;

 fun getStatsCalculator(estatisticas: StatType): (GameManager) -> ArrayList<String>? {

    when (estatisticas){
        StatType.TOP_5_CAPTURAS -> return :: getTop5Capturas
        StatType.TOP_5_PONTOS -> return :: getTopPontos
        StatType.PECAS_MAIS_5_CAPTURAS -> return :: getTopPontos
        StatType.PECAS_MAIS_BARALHADAS -> return :: getTopPontos
        StatType.TIPOS_CAPTURADOS -> return :: getTopPontos
    }
 }

 fun getTopPontos(gameManager: GameManager): ArrayList<String> {
    val pieces = gameManager.board.totalPieces
        .filter { it.pointsWorth >= 1 }
        .sortedByDescending { it.pointsWorth }
        .take(5)
        .map { piece ->
            "${piece.nickname} (${if (piece.team == 10) "Preta" else "Branca"}) tem ${piece.pointsWorth} pontos"
        }
        .toCollection(ArrayList())

    return pieces
 }



 fun getTop5Capturas(gameManager: GameManager): ArrayList<String> {
    val pieces = gameManager.board.totalPieces
            .sortedByDescending { it.numCapturas }
            .take(5)
            .map { piece ->
                "${piece.nickname} (${if (piece.team == 10) "Preta" else "Branca"}) " +
                        "fez ${piece.numCapturas} capturas"
            }
            .toCollection(ArrayList())

    return pieces
 }

