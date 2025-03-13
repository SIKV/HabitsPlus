import SwiftUI
import Shared

struct AddActivityView: ConnectedView {
    
    @EnvironmentObject var store: ObservableStore
    
    @Environment(\.dismiss) private var dismiss
    
    @State private var description: String = ""
    @State private var date = Date()
    
    @State private var showErrorAlert = false
    @State private var errorMessage = ""
    
    struct Props {
        let state: AddActivityState
        
        let onInit: () -> Void
        let onSave: (_ description: String, _ date: Date) -> Void
    }
    
    func map(state: AppState, dispatch: @escaping DispatchFunction) -> Props {
        return Props(
            state: state.addActivityState,
            onInit: {
                dispatch(AddActivityAction.Init.shared)
            },
            onSave: { description, date in
                // Covert to milliseconds.
                let dateMs = Int64(date.timeIntervalSince1970 * 1000)
                
                dispatch(AddActivityAction.Update(
                    date: dateMs,
                    description: description
                ))
                
                dispatch(AddActivityAction.Save.shared)
            }
        )
    }
    
    func body(props: Props) -> some View {
        VStack(
            alignment: HorizontalAlignment.leading
        ) {
            DatePicker(
                "",
                selection: $date,
                displayedComponents: [.date]
            )
            .labelsHidden()
            .padding()
            
            Form {
                Section {
                    TextField(
                        R.str.addActivityDescription.localized(),
                        text: $description,
                        axis:.vertical
                    )
                    .textInputAutocapitalization(.sentences)
                    .lineLimit(Int(props.state.descriptionMinLines)...Int(props.state.descriptionMinLines)) // TODO: Set max length.
                }
            }
        }
        .alert(R.str.titleErrorAlert.localized(), isPresented: $showErrorAlert) {
            Button(R.str.actionOK.localized(), role: .cancel) { }
        } message: {
            Text(errorMessage)
        }
        .navigationTitle(R.str.addActivityNavigationTitle.localized())
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Button(R.str.actionDone.localized()) {
                    props.onSave(description, date)
                }
            }
        }
        .onAppear {
            props.onInit()
        }
        .onReceive(store.$sideEffect, perform: { effect in
            if let effect = effect as? AddActivityResultEffect {
                handleResult(result: effect.result)
            }
        })
    }
    
    private func handleResult(result: AddActivityResult) {
        if result is AddActivityResult.Success {
            dismiss()
        } else if let failure = result as? AddActivityResult.Failure {
            errorMessage = failure.error.name // TODO: Replace with a localized string.
            showErrorAlert = true
        }
    }
}

#Preview {
    AddActivityView()
}
