import SwiftUI
import Shared

struct TodoDetailsView: View {
    
    let todo: Todo
    
    var body: some View {
        Text(todo.title)
    }
}
