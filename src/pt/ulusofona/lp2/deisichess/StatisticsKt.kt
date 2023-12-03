package pt.ulusofona.lp2.deisichess

class StatisticsKt {
    fun getStatsCalculator(type: StatType): (GameManager) -> ArrayList<String> {
        return GameManager::holderMethod

    }
}