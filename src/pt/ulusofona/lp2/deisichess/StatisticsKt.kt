package pt.ulusofona.lp2.deisichess

class StatisticsKt {
    companion object {
        @JvmStatic
        fun getStatsCalculator(type: StatType): (GameManager) -> ArrayList<String> {
            return GameManager::holderMethod

        }
    }

}