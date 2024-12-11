import SwiftUI
import Shared

struct TodoDetailsView: View {
    
    let todo: TodoModel
    
    var body: some View {
        Text(todo.title)
    }
}
