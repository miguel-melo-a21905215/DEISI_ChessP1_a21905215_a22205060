package pt.ulusofona.lp2.deisichess

fun getStatsCalculator(estatisticas: StatType): (GameManager) -> ArrayList<String>? {

    when (estatisticas) {
        StatType.TOP_5_CAPTURAS -> return ::getTop5Capturas
        StatType.TOP_5_PONTOS -> return ::getTopPontos
        StatType.PECAS_MAIS_5_CAPTURAS -> return ::getPecasMais5Capturas
        StatType.PECAS_MAIS_BARALHADAS -> return ::getTop3Baralhadas
        StatType.TIPOS_CAPTURADOS -> return ::getTiposPecaCapturados
    }
}

fun getTop3Baralhadas(gameManager: GameManager): ArrayList<String> {
    var pieces = gameManager.board.totalPieces
        .asSequence()
        .filter { it.getSuccessfulAttempts() > 0 } // Exclude pieces with successfulAttempts <= 0
        .sortedByDescending {
            it.getFailedAttempts().toDouble() / it.getSuccessfulAttempts().toDouble()
        }
        .take(3)
        .map { piece ->
            "${piece.getTeam()}:${piece.getNickname()}:${piece.getFailedAttempts()}:${piece.getSuccessfulAttempts()}"
        }
        .toCollection(ArrayList())

    return pieces
}


fun getTopPontos(gameManager: GameManager): ArrayList<String> {
    return gameManager.board.totalPieces
        .asSequence()
        .sortedWith(compareByDescending<Piece> { it.getAccumulatedPoints() }
            .thenBy { it.getNickname() })
        .take(5)
        .map { piece ->
            "${piece.getNickname()} (${if (piece.getTeam() == 10) "PRETA" else "BRANCA"}) tem ${piece.getAccumulatedPoints()} pontos"
        }
        .toCollection(ArrayList())
}


fun getTop5Capturas(gameManager: GameManager): ArrayList<String> {
    val pieces = gameManager.board.totalPieces
        .asSequence()
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
    val pieces = gameManager.board.totalPieces
        .asSequence()
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
    val capturedPieces = gameManager.board.capturedCounters
    val resultList = ArrayList<String>()

    val turn = gameManager.turn

    val jokerTypes = arrayOf("Rainha", "Pónei Mágico", "Padre da Vila", "TorreHor", "TorreVert", "Homer Simpson")
    val actualType = jokerTypes[turn % 6]

    for (capturedPiece in capturedPieces) {
        if (capturedPiece.value?.equals("Joker") == true) {
            resultList.add("Joker/$actualType")
        } else {
            resultList.add(capturedPiece.value.toString())
        }
    }

    resultList.sort()
    return resultList
}













