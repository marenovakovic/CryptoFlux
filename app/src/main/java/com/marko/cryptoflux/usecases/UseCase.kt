package com.marko.cryptoflux.usecases

import com.marko.cryptoflux.Result
import com.marko.cryptoflux.coroutinedispatchers.CoroutineDispatchers
import kotlinx.coroutines.withContext

/**
 * Executes business logic
 *
 * @param dispatchers [CoroutineDispatchers]
 */
abstract class UseCase<in P, R>(
	private val dispatchers: CoroutineDispatchers
) {
	/**
	 * This is where business logic is executed.
	 * Override this to set code to be executed
	 *
	 * @return result of executed code
	 *
	 * @param parameters the input parameters to run the use case with
	 */
	protected abstract suspend fun execute(parameters: P): R

	/**
	 * Executes the use case asynchronously inside [withContext] and returns [Result]
	 *
	 * @return [Result]
	 *
	 * @param parameters the input parameters to run the use case with
	 */
	suspend operator fun invoke(parameters: P): Result<R> = try {
		withContext(dispatchers.io) {
			execute(parameters).let {
				Result.Success(it)
			}
		}
	} catch (e: Exception) {
		Result.Error(e)
	}
}

/**
 * [UseCase] override for use cases that do not take input parameters
 */
suspend operator fun <R> UseCase<Unit, R>.invoke() = this.invoke(Unit)