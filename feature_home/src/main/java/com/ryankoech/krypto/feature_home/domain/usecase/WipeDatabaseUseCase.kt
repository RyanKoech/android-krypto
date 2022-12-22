package com.ryankoech.krypto.feature_home.domain.usecase

import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WipeDatabaseUseCase @Inject constructor(
    private val repository: OwnedCoinsRepository
) {

    operator fun invoke() = flow {
            try {
                emit(Resource.Success(repository.wipeDatabase()))
            }catch (e : Exception) {
                emit(Resource.Error(e.message?: "Unexpected Error Occurred"))
        }
    }
}