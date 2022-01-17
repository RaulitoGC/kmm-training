import SwiftUI
import shared

@main
struct iOSApp: App {
    
    let networkModule = NetworkModule()
    
	var body: some Scene {
        WindowGroup {
			ContentView(networkModule: networkModule)
		}
	}
}
