import SwiftUI
import Shared

struct ActivityItemView: View {
    
    let activity: ActivityModel
    
    var body: some View {
        let date = activity.date.toDate()
        
        NavigationLink(destination: ActivityDetailsView(activity: activity)) {
            VStack {
                Text(date.formatted(.dateTime.weekday()))
                    .scaledToFill()
                    .minimumScaleFactor(0.5)
                    .font(.subheadline)
                
                Text(date.formatted(.dateTime.day()))
                    .font(.title)
            }
        }
        .padding([.all], R.spacing.big)
        .background(
            RoundedRectangle(cornerRadius: 10) // TODO: Use a constant.
                .stroke(.blue, lineWidth: 1)
                .aspectRatio(1, contentMode: .fit)
        )
    }
}
