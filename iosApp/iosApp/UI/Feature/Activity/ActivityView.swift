import SwiftUI

struct ActivityView: View {
    var body: some View {
        NavigationStack {
            VStack { 
                
            }
            .navigationTitle(R.str.topLevelRouteActivity.localized())
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    NavigationLink(destination: AddActivityView()) {
                        Image(systemName: R.img.actionAdd)
                    }
                }
            }
        }
    }
}

#Preview {
    ActivityView()
}
