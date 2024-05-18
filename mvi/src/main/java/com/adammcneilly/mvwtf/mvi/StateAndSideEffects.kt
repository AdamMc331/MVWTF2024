package com.adammcneilly.mvwtf.mvi

data class StateAndSideEffects<S : State, SE : SideEffect>(
    val state: S,
    val sideEffects: List<SE>,
)

fun <S : State, SE : SideEffect> S.noSideEffects(): StateAndSideEffects<S, SE> {
    return StateAndSideEffects(
        state = this,
        sideEffects = emptyList(),
    )
}

operator fun <S : State, SE : SideEffect> S.plus(sideEffect: SE): StateAndSideEffects<S, SE> {
    return StateAndSideEffects(
        state = this,
        sideEffects = listOf(sideEffect),
    )
}
