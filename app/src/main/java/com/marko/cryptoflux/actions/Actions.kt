package com.marko.cryptoflux.actions

import com.marko.cryptoflux.entities.Coin
import com.marko.cryptoflux.entities.CoinId

sealed class Action

/**
 * [Action] signaling [Coin]s should be fetched
 */
object GetCoinsAction : Action()

/**
 * [Action] signaling [Coin] details should be fetched
 *
 * @param id [CoinId] of the [Coin] that should be fetched
 */
data class GetCoinAction(val id: CoinId) : Action()

/**
 * [Action] signaling [Coin]s should be saved
 *
 * @param coins [Coin]s that should be saved
 */
data class SaveCoinsAction(val coins: List<Coin>) : Action()