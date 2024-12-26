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
        // Common Actions
        static let actionAdd = "plus.circle"
        static let actionMore = "ellipsis.circle"
        static let actionSort = "arrow.up.arrow.down"
        
        static let selected = "checkmark"
        
        // Todo
        static let todoStatusTodo = "circle"
        static let todoStatusDone = "checkmark.circle.fill"
        static let todosShowCompleted = "eye"
        static let todosHideCompleted = "eye.slash"
    }
    
    enum str : String {
        // Top level routes
        case topLevelRouteTodos = "TopLevelRouteTodos"
        case topLevelRouteActivity = "TopLevelRouteActivity"
        
        // Common
        case titleErrorAlert = "TitleErrorAlert"
        
        case orderByTitleAsc = "OrderByTitleAsc"
        case orderByTitleDesc = "OrderByTitleDesc"
        case orderByDueDateAsc = "OrderByDueDateAsc"
        case orderByDueDateDesc = "OrderByDueDateDesc"
        case orderByAddedAtAsc = "OrderByAddedAtAsc"
        case orderByAddedAtDesc = "OrderByAddedAtDesc"
        
        // Common actions
        case actionDone = "ActionDone"
        case actionOK = "ActionOK"
        case actionMenu = "ActionMenu"
        case actionSortBy = "ActionSortBy"
        
        // TodosView
        case todosActionShowCompleted = "TodosActionShowCompleted"
        case todosActionHideCompleted = "TodosActionHideCompleted"
        
        // AddTodoView
        case addTodoNavigationTitle = "AddTodoNavigationTitle"
        case addTodoTitle = "AddTodoTitle"
        case addTodoDescription = "AddTodoDescription"
        case addTodoDueDate = "AddTodoDueDate"
        
        // AddActivityView
        case addActivityNavigationTitle = "AddActivityNavigationTitle"
        case addActivityDescription = "AddActivityDescription"
        
        func localized() -> LocalizedStringKey {
            return LocalizedStringKey(self.rawValue)
        }
    }
}
