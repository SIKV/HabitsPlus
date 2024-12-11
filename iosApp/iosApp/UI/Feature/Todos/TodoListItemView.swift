import SwiftUI
import Shared

struct TodoListItemView: View {
    
    let todo: TodoModel
    let onToggleStatus: () -> Void
    
    var body: some View {
        NavigationLink(destination: TodoDetailsView(todo: todo)) {
            HStack {
                let imageName = todo.status is TodoStatus.Todo ? R.img.todoStatusTodo : R.img.todoStatusDone
            
                Image(systemName: imageName)
                    .onTapGesture {
                        onToggleStatus()
                    }
                    .padding(.trailing)
                
                VStack(alignment: .leading) {
                    Text("Due 20/11/24") // TODO: Display real data.
                        .foregroundStyle(.gray)
                        .font(.caption)
                        .padding([.bottom], R.spacing.tiny)
                    
                    Text(todo.title)
                        .strikethrough(todo.status is TodoStatus.Done)
                        .padding([.bottom], R.spacing.tiny)
                        
                    if let desc = todo.description_ {
                        if !desc.isEmpty {
                            Text(desc)
                                .foregroundStyle(.gray)
                                .font(.caption)
                                .strikethrough(todo.status is TodoStatus.Done)
                                .padding([.bottom], R.spacing.extraSmall)
                        }
                    }
                }
            }
        }
    }
}
