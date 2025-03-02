import SwiftUI
import Shared

struct ActivityDetailsView: View {
    
    let activity: ActivityModel
    
    var body: some View {
        Text(activity.description_)
    }
}
