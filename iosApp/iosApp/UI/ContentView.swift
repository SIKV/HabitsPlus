import SwiftUI
import Shared

struct ContentView: View {

    var body: some View {
        TabView {
            ForEach(topLevelRoutes, id: \.self) { route in
                route.view
                    .tabItem {
                        Image(systemName: route.imageName)
                        Text(LocalizedStringKey(route.nameKey))
                    }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
