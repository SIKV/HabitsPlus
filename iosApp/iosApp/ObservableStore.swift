import Foundation
import Shared

// Source:
// https://github.com/Kotlin/kmp-production-sample/blob/master/iosApp/iosApp/RSSApp.swift

class ObservableStore: ObservableObject {
    
    @Published public var state: AppState = AppState.companion.emptyState
    
    let store: Store<AppState>
    
    var stateWatcher: Closeable?
    
    init(store: Store<AppState>) {
        self.store = store
        
        stateWatcher = self.store.watchState().watch { [weak self] state in
            self?.state = state
        }
    }
    
    public func dispatch(_ action: Action) {
        store.dispatch(action: action)
    }
    
    deinit {
        stateWatcher?.close()
    }
}

public typealias DispatchFunction = (Action) -> ()
