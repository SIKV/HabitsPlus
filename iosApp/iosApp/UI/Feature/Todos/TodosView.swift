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
                dispatch(TodoListAction.FetchAll())
            }
        )
    }
    
    func body(props: Props) -> some View {
        VStack {
            if (props.state.isLoading) {
                ProgressView()
            } else {
                List(props.state.todos, id: \.self) { todo in
                    Text(todo.title)
                }
            }
        }
        .onAppear {
            props.onFetchAll()
        }
    }
}

#Preview {
    TodosView()
}
