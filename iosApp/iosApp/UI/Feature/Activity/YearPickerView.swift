import SwiftUI

struct YearPickerView: View {
    @State var selectedYear: Int32
    let years: [Int32]
    let onYearSelect: (Int32) -> Void
    
    var body: some View {
        Picker(selection: $selectedYear, label: EmptyView()) {
            ForEach(years, id: \.self) { year in
                // TODO: Fix text formatting.
                Text("\(year)")
            }
        }
        .font(.title)
        .onChange(of: selectedYear) { newValue in
            onYearSelect(newValue)
        }
    }
}
