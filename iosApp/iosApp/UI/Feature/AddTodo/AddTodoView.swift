import SwiftUI
import Shared

struct AddTodoView: ConnectedView {
    
    @EnvironmentObject var store: ObservableStore
    @Environment(\.dismiss) private var dismiss
    
    @State private var title: String = ""
    
    @State private var showErrorAlert = false
    @State private var errorMessage = ""
    
    struct Props {
        let state: AddTodoState
        let onAdd: (_ title: String) -> Void
    }
    
    func map(state: AppState, dispatch: @escaping DispatchFunction) -> Props {
        return Props(
            state: state.addTodoState,
            onAdd: { title in
                dispatch(AddTodoAction.UpdateTitle(title: title))
                dispatch(AddTodoAction.Add())
            }
        )
    }
    
    func body(props: Props) -> some View {
        VStack {
            TextField(R.str.addTodoTitle.localized(), text: $title)
                .textFieldStyle(.roundedBorder)
            
            Spacer()
        }
        .alert(R.str.titleErrorAlert.localized(), isPresented: $showErrorAlert) {
            Button(R.str.actionOK.localized(), role: .cancel) { }
        } message: {
            Text(errorMessage)
        }
        .padding()
        .navigationTitle(R.str.addTodoNavigationTitle.localized())
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Button(R.str.actionDone.localized()) {
                    props.onAdd(title)
                }
            }
        }
        .onReceive(store.$sideEffect, perform: { effect in
            if let effect = effect as? AddTodoHandleResultEffect {
                handleResult(result: effect.result)
            }
        })
    }
    
    private func handleResult(result: AddTodoResult) {
        if result is AddTodoResult.Success {
            dismiss()
        } else if let failure = result as? AddTodoResult.Failure {
            errorMessage = failure.error.name // TODO: Replace with a localized string.
            showErrorAlert = true
        }
    }
}

#Preview {
    AddTodoView()
}
