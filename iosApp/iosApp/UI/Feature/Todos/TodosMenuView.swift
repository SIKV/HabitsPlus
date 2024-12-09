import SwiftUI
import Shared

struct TodosMenuView: View {
    
    let orderByOptions: [TodoOrderBy]
    let orderBy: TodoOrderBy
    
    let showCompleted: Bool
    
    let onUpdateOrderBy: (TodoOrderBy) -> Void
    let onUpdateShowCompleted: (Bool) -> Void
    
    var body: some View {
        Menu {
            Button {
                onUpdateShowCompleted(!showCompleted)
            } label: {
                if showCompleted {
                    Label(R.str.todosActionHideCompleted.localized(), systemImage: R.img.todosHideCompleted)
                } else {
                    Label(R.str.todosActionShowCompleted.localized(), systemImage: R.img.todosShowCompleted)
                }
            }
            Menu {
                ForEach(orderByOptions, id: \.self) { option in
                    Button(action: {
                        onUpdateOrderBy(option)
                    }) {
                        if option == orderBy {
                            Image(systemName: R.img.selected)
                        }
                        Text(option.localized())
                    } 
                }
            } label: {
                Text(R.str.actionSortBy.localized())
                Text(orderBy.localized())
                Image(systemName: R.img.actionSort)
            }
        } label: {
            Label(R.str.actionMenu.localized(), systemImage: R.img.actionMore)
        }
    }
}
