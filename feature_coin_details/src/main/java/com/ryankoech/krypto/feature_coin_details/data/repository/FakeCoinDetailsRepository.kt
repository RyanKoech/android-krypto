package com.ryankoech.krypto.feature_coin_details.data.repository

import com.ryankoech.krypto.feature_coin_details.data.dto.market_chart_dto.MarketChartDto
import com.ryankoech.krypto.feature_coin_details.domain.repository.CoinDetailsRepository
import retrofit2.Response
import javax.inject.Inject

val FAKE_MARKET_CHART_DTO = MarketChartDto(
    prices = listOf(
        listOf( 0.0, 21719.94377889883),
        listOf( 0.0, 21726.012694376623),
        listOf( 0.0, 21754.081777271844),
        listOf( 0.0, 21787.54913559603),
        listOf( 0.0, 21750.230221882968),
        listOf( 0.0, 21719.94377889883),
        listOf( 0.0, 21751.645292836954),
        listOf( 0.0, 21825.36877917646),
        listOf( 0.0, 21821.486652083513),
        listOf( 0.0, 21804.720442422582),
    ),
    marketCaps = listOf(),
    totalVolumes = listOf(),
)

class FakeCoinDetailsRepository @Inject constructor() : CoinDetailsRepository {

    override suspend fun getDayMarketChart(coinId: String): Response<MarketChartDto> {
        return Response.success(
            FAKE_MARKET_CHART_DTO.copy(
                prices = FAKE_MARKET_CHART_DTO.prices.subList(0, (FAKE_MARKET_CHART_DTO.prices.size-1)/3)
            )
        )
    }

    override suspend fun getThreeMonthsMarketChart(coinId: String): Response<MarketChartDto> {
        return Response.success(
            FAKE_MARKET_CHART_DTO.copy(
                prices = FAKE_MARKET_CHART_DTO.prices.subList(0,FAKE_MARKET_CHART_DTO.prices.size/2)
            )
        )
    }

    override suspend fun getYearMarketChart(coinId: String): Response<MarketChartDto> {
        return Response.success(
            FAKE_MARKET_CHART_DTO
        )
    }
}