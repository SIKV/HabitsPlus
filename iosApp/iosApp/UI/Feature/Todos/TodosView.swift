import SwiftUI
import Shared

struct TodosView: ConnectedView {
    
    struct Props {
        let state: TodoListState
        let onFetchAll: () -> Void
        let onToggleStatus: (Todo) -> Void
    }
    
    func map(state: AppState, dispatch: @escaping DispatchFunction) -> Props {
        return Props(
            state: state.todoListState,
            onFetchAll: {
                dispatch(TodoListAction.FetchAll(orderBy: TodoOrderBy.titleDesc))
            },
            onToggleStatus: { todo in
                dispatch(TodoListAction.ToggleStatus(todo: todo))
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
                        Image(systemName: R.img.add)
                    }
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
