import Shared

extension Int {
    
    func toKotlin() -> KotlinInt {
        return KotlinInt(int: Int32(self))
    }
}

extension Int64 {
    
    func toKotlin() -> KotlinLong {
        return KotlinLong(value: self)
    }
    
    func toDate() -> Date {
        return Date(timeIntervalSince1970: TimeInterval(self / 1000))
    }
}

extension KotlinLong? {

    func toDate() -> Date? {
        if let value = self {
            return Date(timeIntervalSince1970: TimeInterval(Int(truncating: value) / 1000))
        } else {
            return nil
        }
    }
}

extension KotlinInt {

    func toInt32() -> Int32 {
        return Int32(truncating: self)
    }
}
