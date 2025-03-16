import Foundation
import Shared

// Source:
// https://github.com/Kotlin/kmp-production-sample/blob/master/iosApp/iosApp/RSSApp.swift

class ObservableStore: ObservableObject {
    
    @Published public var state: AppStateImpl = AppStateImpl.companion.emptyState
    @Published public var sideEffect: Effect?
    
    let store: Store<AppState>
    
    var stateWatcher: Closeable?
    var sideEffectWatcher: Closeable?
    
    init(store: Store<AppState>) {
        self.store = store
        
        stateWatcher = self.store.watchState().watch { [weak self] state in
            self?.state = state as! AppStateImpl
        }
        
        sideEffectWatcher = self.store.watchSideEffect().watch { [weak self] effect in
            self?.sideEffect = effect
            // Avoid receiving the same side effect multiple times.
            self?.sideEffect = nil
        }
    }
    
    public func dispatch(_ action: Action) {
        store.dispatch(action: action)
    }
    
    deinit {
        stateWatcher?.close()
        sideEffectWatcher?.close()
    }
}

public typealias DispatchFunction = (Action) -> ()
