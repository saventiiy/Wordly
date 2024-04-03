package com.saventiy.wordly.screens.main

internal class Detailed {

    sealed class ViewState {
        object Loading : ViewState()
        data class Display(val data: String = "q") : ViewState()
    }

    sealed class Event {
        data class OnSearchClicked(val search: String) : Event()
    }

    sealed class Effect {
        data class Error(val error: String) : Effect()
    }
}