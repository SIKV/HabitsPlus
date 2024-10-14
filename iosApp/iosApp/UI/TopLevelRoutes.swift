import Foundation
import SwiftUI

struct TopLevelRoute {
    public let nameKey: String
    public let imageName: String
    public let view: AnyView
}

extension TopLevelRoute: Hashable {
    static func == (lhs: TopLevelRoute, rhs: TopLevelRoute) -> Bool {
        return lhs.nameKey == rhs.nameKey
    }
    
    func hash(into hasher: inout Hasher) {
        hasher.combine(nameKey)
    }
}

let topLevelRoutes = [
    TopLevelRoute(nameKey: "TopLevelRouteHabits", imageName: "hand.thumbsup", view: AnyView(HabitsView())),
    TopLevelRoute(nameKey: "TopLevelRouteTodos", imageName: "checkmark", view: AnyView(TodosView())),
    TopLevelRoute(nameKey: "TopLevelRouteActivity", imageName: "star", view: AnyView(ActivityView())),
    TopLevelRoute(nameKey: "TopLevelRouteMore", imageName: "ellipsis", view: AnyView(MoreView()))
]
