import pt.ulusofona.lp2.deisichess.GameManager
import pt.ulusofona.lp2.deisichess.StatType

fun getStatusCalculator(estatisticas: StatType): (GameManager) -> ArrayList<String>? {

    when (estatisticas){
        StatType.TOP_5_CAPTURAS -> return :: getTopPontos
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