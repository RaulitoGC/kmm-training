import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject var viewModel: FriendListViewModel
    
    init(networkModule: NetworkModule){
        let getFriends = GetFriends(friendsService: networkModule.friendService)
        self.viewModel = FriendListViewModel(getFriends: getFriends)
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
