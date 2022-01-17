//
//  FriendsListViewModel.swift
//  iosApp
//
//  Created by Raul Jonathan Guzman Condor on 14/01/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class FriendListViewModel: ObservableObject{
    
    let getFriends : GetFriends
    let addFriend: AddFriend
    
    @Published var isLoading : Bool = false
    @Published var showError: Bool = false
    @Published var friends: [Friend] = []
    
    
    init(getFriends: GetFriends, addFriend: AddFriend){
        self.getFriends = getFriends
        self.addFriend = addFriend
        loadFriends()
    }
    
    private func loadFriends(){
        updateLoading(showLoading: true)
        getFriends.execute().collectCommon(coroutineScope: nil, callback: { dataState in
            
            if let friends = dataState?.data {
                self.friends = friends as! [Friend]
            } else {
                self.showError(showError: true)
            }
            
            self.updateLoading(showLoading: false)
        })
    }
    
    func insertRandomFriend(){
        updateLoading(showLoading: true)
        addFriend
            .execute()
            .collectCommon(coroutineScope: nil, callback: { dataState  in
                self.loadFriends()
            })
    }
    
    private func updateLoading(showLoading: Bool){
        self.isLoading = showLoading
    }
    
    private func showError(showError: Bool){
        self.showError = showError
    }

}
