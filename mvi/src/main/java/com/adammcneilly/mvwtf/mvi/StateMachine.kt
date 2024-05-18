package com.adammcneilly.mvwtf.mvi

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StateMachine<S : State, SUE : StateUpdateEvent, SE : SideEffect>(
    initialState: S,
    private val eventProcessor: (S, SUE) -> StateAndSideEffects<S, SE>,
    private val sideEffectProcessor: (SE) -> Unit,
) {
    private val mutableState = MutableStateFlow(initialState)
    val state = mutableState.asStateFlow()

    fun processEvent(event: SUE) {
        val output = eventProcessor(mutableState.value, event)

        mutableState.value = output.state
        output.sideEffects.forEach(sideEffectProcessor::invoke)
    }
}
