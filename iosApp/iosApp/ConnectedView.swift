import Foundation
import SwiftUI
import Shared

// Source:
// https://github.com/Kotlin/kmp-production-sample/blob/master/iosApp/iosApp/RSSApp.swift

public protocol ConnectedView: View {
    associatedtype Props
    associatedtype V: View
    
    func map(state: AppState, dispatch: @escaping DispatchFunction) -> Props
    func body(props: Props) -> V
}

public extension ConnectedView {
    func render(state: AppState, dispatch: @escaping DispatchFunction) -> V {
        let props = map(state: state, dispatch: dispatch)
        return body(props: props)
    }
    
    var body: StoreConnector<V> {
        return StoreConnector(content: render)
    }
}

public struct StoreConnector<V: View>: View {
    @EnvironmentObject var store: ObservableStore
    let content: (AppState, @escaping DispatchFunction) -> V
    
    public var body: V {
        return content(store.state, store.dispatch)
    }
}
