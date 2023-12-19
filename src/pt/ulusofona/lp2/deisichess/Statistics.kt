package pt.ulusofona.lp2.deisichess;

 fun getStatsCalculator(estatisticas: StatType): (GameManager) -> ArrayList<String>? {

    when (estatisticas){
        StatType.TOP_5_CAPTURAS -> return :: getTop5Capturas
        StatType.TOP_5_PONTOS -> return :: getTopPontos
        StatType.PECAS_MAIS_5_CAPTURAS -> return :: getPecasMais5Capturas
        StatType.PECAS_MAIS_BARALHADAS -> return :: getTopPontos
        StatType.TIPOS_CAPTURADOS -> return :: getTiposPecaCapturados
    }
 }

 fun getTopPontos(gameManager: GameManager): ArrayList<String> {
    val pieces = gameManager.board.totalPieces
        .sortedByDescending { it.pointsWorth }
        .take(5)
        .map { piece ->
            "${piece.nickname} (${if (piece.team == 10) "PRETA" else "BRANCA"}) tem ${piece.pointsWorth} pontos"
        }
        .toCollection(ArrayList())

    return pieces
 }



 fun getTop5Capturas(gameManager: GameManager): ArrayList<String> {
    val pieces = gameManager.board.totalPieces
            .sortedByDescending { it.numCapturas }
            .take(5)
            .map { piece ->
                "${piece.nickname} (${if (piece.team == 10) "PRETA" else "BRANCA"}) " +
                        "fez ${piece.numCapturas} capturas"
            }
            .toCollection(ArrayList())

    return pieces
 }

 fun getPecasMais5Capturas(gameManager: GameManager): ArrayList<String> {
    val pieces = gameManager.board.totalPieces
        .filter { it.numCapturas > 5 }
        .sortedByDescending { it.numCapturas }
        .take(5)
        .map { piece ->
            "${if (piece.team == 10) "PRETA" else "BRANCA"}:${piece.nickname}:${piece.numCapturas}"
        }
        .toCollection(ArrayList())

    return pieces
 }





 fun getTiposPecaCapturados(gameManager: GameManager): ArrayList<String> {

        val piecesCaptured = gameManager.board.totalPieces.filter { !it.isInPlay() }
        val namesCaptured = piecesCaptured.map { piece ->
            piece.typeStr
        }.distinct()

        return ArrayList(namesCaptured)
 }






