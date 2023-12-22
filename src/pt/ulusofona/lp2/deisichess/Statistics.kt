package pt.ulusofona.lp2.deisichess;

fun getStatsCalculator(estatisticas: StatType): (GameManager) -> ArrayList<String>? {

    when (estatisticas) {
        StatType.TOP_5_CAPTURAS -> return ::getTop5Capturas
        StatType.TOP_5_PONTOS -> return ::getTopPontos
        StatType.PECAS_MAIS_5_CAPTURAS -> return ::getPecasMais5Capturas
        StatType.PECAS_MAIS_BARALHADAS -> return ::getTopPontos
        StatType.TIPOS_CAPTURADOS -> return ::getTiposPecaCapturados
    }
}

fun getTop3Baralhadas(gameManager: GameManager): ArrayList<String> {
    val pieces = gameManager.getBoard().getTotalPieces()
        .sortedByDescending { it.getFailedAttempts() }
        .take(3)
        .map { piece ->
            "${piece.getTeam()}:${piece.getNickname()}:${piece.getFailedAttempts()}:${piece.getSuccessfulAttempts()}"
        }
        .toCollection(ArrayList())
    return pieces
}

fun getTopPontos(gameManager: GameManager): ArrayList<String> {
    val pieces = gameManager.getBoard().getTotalPieces()
        .sortedByDescending { it.getPointsWorth() }
        .take(5)
        .map { piece ->
            "${piece.getNickname()} (${if (piece.getTeam() == 10) "PRETA" else "BRANCA"}) tem ${piece.getPointsWorth()} pontos"
        }
        .toCollection(ArrayList())

    return pieces
}


fun getTop5Capturas(gameManager: GameManager): ArrayList<String> {
    val pieces = gameManager.getBoard().getTotalPieces()
        .sortedByDescending { it.getNumCapturas() }
        .take(5)
        .map { piece ->
            "${piece.getNickname()} (${if (piece.getTeam() == 10) "PRETA" else "BRANCA"}) " +
                    "fez ${piece.getNumCapturas()} capturas"
        }
        .toCollection(ArrayList())

    return pieces
}

fun getPecasMais5Capturas(gameManager: GameManager): ArrayList<String> {
    val pieces = gameManager.getBoard().getTotalPieces()
        .filter { it.getNumCapturas() > 5 }
        .sortedByDescending { it.getNumCapturas() }
        .take(5)
        .map { piece ->
            "${if (piece.getTeam() == 10) "PRETA" else "BRANCA"}:${piece.getNickname()}:${piece.getNumCapturas()}"
        }
        .toCollection(ArrayList())

    return pieces
}


fun getTiposPecaCapturados(gameManager: GameManager): ArrayList<String> {

    val piecesCaptured = gameManager.getBoard().getTotalPieces().filter { !it.isInPlay() }
    val namesCaptured = piecesCaptured.map { piece ->
        piece.getTypeStr()
    }.distinct()

    return ArrayList(namesCaptured)
}








