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
}
