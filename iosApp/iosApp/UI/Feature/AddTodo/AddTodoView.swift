import SwiftUI
import Shared

struct AddTodoView: ConnectedView {
    
    @EnvironmentObject var store: ObservableStore
    @Environment(\.dismiss) private var dismiss
    
    @State private var title: String = ""
    @State private var description: String = ""
    
    @State private var includeDueDate = false
    @State private var dueDate = Date()
    
    @State private var showErrorAlert = false
    @State private var errorMessage = ""
    
    struct Props {
        let state: AddTodoState
        let onSave: (_ title: String, _ description: String, _ includeDueDate: Bool, _ dueDate: Date) -> Void
    }
    
    func map(state: AppStateImpl, dispatch: @escaping DispatchFunction) -> Props {
        return Props(
            state: state.addTodoState,
            onSave: { title, description, includeDueDate, dueDate in
                // Covert to milliseconds.
                let dateMs = Int64(dueDate.timeIntervalSince1970 * 1000).toKotlin()
                
                let calendar = Calendar.current
                let hour = calendar.component(.hour, from: dueDate).toKotlin()
                let minute = calendar.component(.minute, from: dueDate).toKotlin()
    
                dispatch(AddTodoAction.Update(
                    title: title,
                    description: description,
                    dueDate: includeDueDate ? dateMs : nil,
                    dueTimeHour: includeDueDate ? hour : nil,
                    dueTimeMinute: includeDueDate ? minute : nil
                ))
                
                dispatch(AddTodoAction.Save.shared)
            }
        )
    }
    
    func body(props: Props) -> some View {
        Form {
            Section {
                TextField(
                    R.str.addTodoTitle.localized(),
                    text: $title
                )
                .textInputAutocapitalization(.sentences)
                
                TextField(
                    R.str.addTodoDescription.localized(),
                    text: $description,
                    axis:.vertical
                )
                .textInputAutocapitalization(.sentences)
                .lineLimit(Int(props.state.descriptionMinLines)...Int(props.state.descriptionMinLines))
            }
            Section {
                Toggle(isOn: $includeDueDate) {
                    Text(R.str.addTodoDueDate.localized())
                }
                .listRowSeparator(.hidden)
                
                if includeDueDate {
                    DatePicker(
                        "",
                        selection: $dueDate,
                        displayedComponents: [.date, .hourAndMinute]
                    )
                    .padding(.top)
                }
            }
        }
        .alert(R.str.titleErrorAlert.localized(), isPresented: $showErrorAlert) {
            Button(R.str.actionOK.localized(), role: .cancel) { }
        } message: {
            Text(errorMessage)
        }
        .navigationTitle(R.str.addTodoNavigationTitle.localized())
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Button(R.str.actionDone.localized()) {
                    props.onSave(title, description, includeDueDate, dueDate)
                }
            }
        }
        .onReceive(store.$sideEffect, perform: { effect in
            if let effect = effect as? AddTodoResultEffect {
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
