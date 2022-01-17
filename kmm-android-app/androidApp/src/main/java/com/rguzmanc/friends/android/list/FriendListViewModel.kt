package com.rguzmanc.friends.android.list

import androidx.lifecycle.*
import com.rguzmanc.friends.android.util.OneTimeEvent
import com.rguzmanc.friends.android.util.toOneTimeEvent
import com.rguzmanc.friends.datasource.network.FriendService
import com.rguzmanc.friends.datasource.network.KtorClientFactory
import com.rguzmanc.friends.domain.GetFriends
import com.rguzmanc.friends.domain.model.Friend
import kotlinx.coroutines.launch

class FriendListViewModel(private val getFriends: GetFriends) : ViewModel() {

    private val _viewState = MutableLiveData<OneTimeEvent<ViewState>>()
    val viewState: LiveData<OneTimeEvent<ViewState>> = _viewState

    private val _friends = MutableLiveData<OneTimeEvent<List<Friend>>>()
    val friends: LiveData<OneTimeEvent<List<Friend>>> = _friends

    init {
        getFriends()
    }

    private fun getFriends() = viewModelScope.launch {
        updateViewState(ViewState.Loading(true))
        getFriends
            .execute()
            .collectCommon { dataState ->

                val friends = dataState.data
                val message = dataState.message

                if (friends != null && friends.isNotEmpty()) {
                    _friends.value = friends.toOneTimeEvent()
                } else if (message != null && message.isNotEmpty()) {
                    updateViewState(ViewState.Error(message))
                }

                updateViewState(ViewState.Loading(false))
            }
    }

    private fun updateViewState(viewState: ViewState) {
        _viewState.value = viewState.toOneTimeEvent()
    }

    sealed class ViewState {
        data class Loading(val showLoading: Boolean) : ViewState()
        data class Error(val message: String) : ViewState()
    }
}

class FriendListViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val friendService = FriendService(
            httpClient = KtorClientFactory().build(),
            baseUrl = "https://private-908651-friends15.apiary-mock.com/"
        )

        val getFriends = GetFriends(friendService)
        return FriendListViewModel(getFriends) as T
    }

}