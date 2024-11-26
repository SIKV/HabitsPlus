import Foundation
import SwiftUI

class R {
    
    class spacing {
        static let tiny = 2.0
        static let extraSmall = 4.0
        static let small = 8.0
        static let medium = 16.0
        static let large = 32.0
    }
    
    class img {
        // Common
        static let add = "plus.circle"
        
        // Todo
        static let todoStatusTodo = "circle"
        static let todoStatusDone = "checkmark.circle.fill"
    }
    
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
        case addTodoDescription = "AddTodoDescription"
        case addTodoDueDate = "AddTodoDueDate"
        
        func localized() -> LocalizedStringKey {
            return LocalizedStringKey(self.rawValue)
        }
    }
}
