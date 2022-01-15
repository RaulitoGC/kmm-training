package com.rguzmanc.friends.android.list

import androidx.lifecycle.*
import com.rguzmanc.friends.android.util.OneTimeEvent
import com.rguzmanc.friends.android.util.toOneTimeEvent
import com.rguzmanc.friends.datasource.network.FriendService
import com.rguzmanc.friends.datasource.network.KtorClientFactory
import com.rguzmanc.friends.domain.GetFriends
import kotlinx.coroutines.launch

class FriendListViewModel(private val getFriends: GetFriends) : ViewModel() {

    private val _dataState = MutableLiveData<OneTimeEvent<GetFriends.DataState>>()
    val dataState: LiveData<OneTimeEvent<GetFriends.DataState>> = _dataState

    init {
        getFriends()
    }

    private fun getFriends() = viewModelScope.launch {
        getFriends
            .execute()
            .collectCommon { dataState ->
                _dataState.value = dataState.toOneTimeEvent()
            }
    }
}

class FriendListViewModelFactory: ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val friendService = FriendService(
            httpClient = KtorClientFactory().build(),
            baseUrl = "https://private-908651-friends15.apiary-mock.com/")

        val getFriends = GetFriends(friendService)
        return FriendListViewModel(getFriends) as T
    }

}