package com.ryankoech.krypto.feature_home.domain.usecase

import com.ryankoech.krypto.common.core.util.Resource
import com.ryankoech.krypto.feature_home.core.di.HILT_NAME_REPO_FOR_ALL
import com.ryankoech.krypto.feature_home.domain.repository.OwnedCoinsRepository
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class WipeDatabaseUseCase @Inject constructor(
    @Named(HILT_NAME_REPO_FOR_ALL) private val repository: OwnedCoinsRepository
) {

    operator fun invoke() = flow {
            try {
                emit(Resource.Success(repository.wipeDatabase()))
            }catch (e : Exception) {
                Timber.e(e)
                emit(Resource.Error(e.message?: "Unexpected Error Occurred"))
        }
    }
}