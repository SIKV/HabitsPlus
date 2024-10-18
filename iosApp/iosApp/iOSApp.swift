import SwiftUI
import Shared

@main
struct iOSApp: App {
    let store: ObservableStore
    
    init() {
        DIKt.doInitKoin()
        
        let appStore: Store<AppState> = StoreProvider().appStore()
        store = ObservableStore(store: appStore)
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
                .environmentObject(store)
        }
    }
}
