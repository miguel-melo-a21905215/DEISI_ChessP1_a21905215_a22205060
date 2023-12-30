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
        .filter { it.getAccumulatedPoints() > 0 }
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
        .filter { it.getAccumulatedPoints() > 0 }
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

    val capturedCounters = gameManager.board.capturedCounters
    val resultList = ArrayList<String>()
    var jokerCaptured = false

    for (key in capturedCounters.keys) { // Itera sobre cada key do tipo de peça nas peças capturadas
        if (key.startsWith("Joker") && !jokerCaptured) { // ve se a key começa com o nome "Joker" e ve tambem se o joker nao foi adicionado na resultlist

            resultList.add(key) //adiciona nome "Joker/(...)" na lista resultlist
            jokerCaptured = true
        } else if (!key.startsWith("Joker")) { // se a key nao começa com o nome "Joker"
            resultList.add(key)                      // adiciona o nome da outra peça na resultlist
        }
    }

    resultList.sort()
    return resultList
}









