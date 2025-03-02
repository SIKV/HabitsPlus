import SwiftUI
import Shared

struct ActivityView: ConnectedView {
    
    @EnvironmentObject var store: ObservableStore
    
    struct Props {
        let state: ActivityListState
        let yearsFilter: [Int32]
        let onInit: () -> Void
        let onFetchAll: (Int32) -> Void
    }
    
    private let adaptiveColumn = [
        GridItem(.flexible()),
        GridItem(.flexible()),
        GridItem(.flexible()),
        GridItem(.flexible()),
    ]
    
    func map(state: AppState, dispatch: @escaping DispatchFunction) -> Props {
        return Props(
            state: state.activityListState,
            yearsFilter: state.activityListState.yearsFilter.map { year in
                year.toInt32()
            },
            onInit: {
                dispatch(ActivityListAction.Init.shared)
            },
            onFetchAll: { year in
                dispatch(ActivityListAction.FetchAll(year: year))
            }
        )
    }
    
    func body(props: Props) -> some View {
        NavigationStack {
            ScrollView {
                VStack(alignment: .leading) {
                    if let selectedYear = props.state.selectedYear?.toInt32() {
                        YearPickerView(
                            selectedYear: selectedYear,
                            years: props.yearsFilter,
                            onYearSelect: { year in
                                props.onFetchAll(year)
                            }
                        )
                        .padding([.horizontal])
                    }
                    
                    LazyVGrid(columns: adaptiveColumn, alignment: .leading) {
                        ForEach(props.state.activities, id: \.self) { activityGroup in
                            Section {
                                ForEach(activityGroup.activities, id: \.self) { activity in
                                    ActivityItemView(activity: activity)
                                }
                            } header: {
                                let date = activityGroup.monthTimestamp().toDate()
                                Text(date?.formatted(.dateTime.month(.wide)) ?? "")
                                    .font(.title3)
                            }
                        }
                    }
                    .padding()
                }
            }
            .navigationTitle(R.str.topLevelRouteActivity.localized())
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    NavigationLink(destination: AddActivityView()) {
                        Image(systemName: R.img.actionAdd)
                    }
                }
            }
            .onAppear {
                props.onInit()
            }
        }
    }
}

#Preview {
    ActivityView()
}
