import SwiftUI
import Shared

struct TodosView: ConnectedView {
    
    struct Props {
        let state: TodoListState
        let onFetchAll: () -> Void
    }
    
    func map(state: AppState, dispatch: @escaping DispatchFunction) -> Props {
        return Props(
            state: state.todoListState,
            onFetchAll: {
                dispatch(TodoListAction.FetchAll(orderBy: TodoOrderBy.titleDesc))
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
                        Text(todo.title)
                    }
                }
            }
            .navigationTitle(R.str.topLevelRouteTodos.localized())
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    NavigationLink(destination: AddTodoView()) {
                        Image(systemName: R.img.add.rawValue)
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
