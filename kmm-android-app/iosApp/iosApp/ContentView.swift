import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject var viewModel: FriendListViewModel
    
    init(networkModule: NetworkModule, cacheModule: CacheModule){
        let getFriends = GetFriends(friendsService: networkModule.friendService, friendCache: cacheModule.friendCache)
        let addFriend = AddFriend(friendCache: cacheModule.friendCache)
        self.viewModel = FriendListViewModel(getFriends: getFriends, addFriend: addFriend)
    }
    
	var body: some View {
        ZStack{
            List{
                ForEach(viewModel.friends, id: \.self.id){ friend in
                    Text("\(friend.name) \(friend.lastName)")
                }
            }
            
            if(viewModel.isLoading){
                ProgressView("Loading Friends")
            }
            
            VStack {
                Spacer()
                HStack {
                    Spacer()
                    Button(action: {
                        viewModel.insertRandomFriend()
                    }, label: {
                        Text("+")
                        .font(.system(.largeTitle))
                        .frame(width: 77, height: 70)
                        .foregroundColor(Color.white)
                        .padding(.bottom, 7)
                    })
                    .background(Color.blue)
                    .cornerRadius(38.5)
                    .padding()
                    .shadow(color: Color.black.opacity(0.3),
                            radius: 3,
                            x: 3,
                            y: 3)
                }
            }
            
        }.alert(isPresented: $viewModel.showError, content: {
            Alert(
                title: Text("Error"), message: Text("Friends not found"), dismissButton: .default(Text("OK")))
        })
        
        
        

        
	}
}

//struct ContentView_Previews: PreviewProvider {
//	static var previews: some View {
//        ContentView(networkModule: <#T##NetworkModule#>())
//	}
//}
