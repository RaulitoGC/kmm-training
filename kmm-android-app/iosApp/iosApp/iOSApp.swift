import SwiftUI
import shared

@main
struct iOSApp: App {
    
    let networkModule = NetworkModule()
    let cacheModule = CacheModule()
    
	var body: some Scene {
        WindowGroup {
			ContentView(networkModule: networkModule, cacheModule: cacheModule)
		}
	}
}
