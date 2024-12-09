import SwiftUI
import Shared

extension TodoOrderBy {
    
    func localized() -> LocalizedStringKey {
        switch self {
        case TodoOrderBy.titleAsc:
            return R.str.orderByTitleAsc.localized()
        case TodoOrderBy.titleDesc:
            return R.str.orderByTitleDesc.localized()
        case TodoOrderBy.dueDateAsc:
            return R.str.orderByDueDateAsc.localized()
        case TodoOrderBy.dueDateDesc:
            return R.str.orderByDueDateDesc.localized()
        case TodoOrderBy.addedAtAsc:
            return R.str.orderByAddedAtAsc.localized()
        case TodoOrderBy.addedAtDesc:
            return R.str.orderByAddedAtDesc.localized()
        default:
            return LocalizedStringKey("")
        }
    }
}
