import SwiftUI
import Shared

struct TodosView: ConnectedView {
    
    struct Props {
        let state: TodoListState
        let onFetchAll: () -> Void
        let onToggleStatus: (TodoModel) -> Void
        let onUpdateOrderBy: (TodoOrderBy) -> Void
        let onUpdateShowCompleted: (Bool) -> Void
    }
    
    func map(state: AppStateImpl, dispatch: @escaping DispatchFunction) -> Props {
        return Props(
            state: state.todoListState,
            onFetchAll: {
                dispatch(TodoListAction.FetchAll.shared)
            },
            onToggleStatus: { todo in
                dispatch(TodoListAction.ToggleStatus(todo: todo))
            },
            onUpdateOrderBy: { orderBy in
                dispatch(TodoListAction.UpdateOrderBy(orderBy: orderBy))
            },
            onUpdateShowCompleted: { showCompleted in
                dispatch(TodoListAction.UpdateShowCompleted(showCompleted: showCompleted))
            }
        )
    }
    
    func body(props: Props) -> some View {
        NavigationStack {
            VStack {
                if (props.state.isLoading) {
                    ProgressView()
                } else {
                    List(props.state.todos, id: \.self) { todo in
                        TodoListItemView(
                            todo: todo,
                            onToggleStatus: {
                                props.onToggleStatus(todo)
                            }
                        )
                    }
                } 
            }
            .navigationTitle(R.str.topLevelRouteTodos.localized())
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    NavigationLink(destination: AddTodoView()) {
                        Image(systemName: R.img.actionAdd)
                    }
                }
                ToolbarItem(placement: .navigationBarTrailing) {
                    TodosMenuView(
                        orderByOptions: props.state.orderByOptions,
                        orderBy: props.state.orderBy,
                        showCompleted: props.state.showCompleted,
                        onUpdateOrderBy: props.onUpdateOrderBy,
                        onUpdateShowCompleted: props.onUpdateShowCompleted
                    )
                }
            }
            .onAppear {
                props.onFetchAll()
            }
        }
    }
}

#Preview {
    TodosView()
}
