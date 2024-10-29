import Foundation
import SwiftUI

class R {
    enum str : String {
        // Top level routes
        case topLevelRouteTodos = "TopLevelRouteTodos"
        
        // Common
        case titleErrorAlert = "TitleErrorAlert"
        
        // Common actions
        case actionDone = "ActionDone"
        case actionOK = "ActionOK"
        
        // AddTodo View
        case addTodoNavigationTitle = "AddTodoNavigationTitle"
        case addTodoTitle = "AddTodoTitle"
        
        func localized() -> LocalizedStringKey {
            return LocalizedStringKey(self.rawValue)
        }
    }
    
    enum img: String {
        case add = "plus.circle"
    }
}
