package com.marko.cryptoflux

/**
 * Result encapsulation class. Can be [Success], [Error] or [Loading]
 */
sealed class Result<out R> {
	/**
	 * Success case
	 */
	data class Success<out T>(val data: T) : Result<T>()

	/**
	 * Error case
	 */
	data class Error(val exception: Exception) : Result<Nothing>()

	/**
	 * Loading case
	 */
	object Loading : Result<Nothing>()
}

/**
 * Checks is [Result] [Success] and is [Success.data] != null
 */
val Result<*>.succeeded
	get() = this is Result.Success && data != null