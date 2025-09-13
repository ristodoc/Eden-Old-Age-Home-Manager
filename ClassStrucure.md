class diagram: 

class Doctor {
- String doctorId
- String name
- String specialization
- List~DoctorVisit~ visits
+ addVisit(DoctorVisit v)
+ viewResidentHistory(String residentId)
}


class DoctorVisit {
- LocalDate date
- String notes
- Doctor doctor
- List~String~ prescriptions
+ getSummary() String
}


class InventoryItem {
- String itemId
- String name
- int quantityLeft
- int monthlyRequirement
+ isRestockNeeded() boolean
+ restock(int amount)
}


class LoginLogger {
- List~LoginEntry~ logs
+ recordLogin(String userId)
+ recordLogout(String userId)
+ getUserLogHistory(String userId) List~LoginEntry~
}


class LoginEntry {
- String userId
- LocalDateTime loginTime
- LocalDateTime logoutTime
+ duration() Duration
}


Admin --|> User
WardEmployee --|> User


Admin "1" --> "0..*" Ward : manages
Ward "1" o-- "0..*" Resident : contains
Ward "1" o-- "0..*" WardEmployee : employs
WardEmployee "0..*" -- "1" Ward : assigned to


Resident "1" *-- "0..*" Medication : uses
Resident "1" *-- "0..*" DoctorVisit : has
Doctor "1" o-- "0..*" DoctorVisit : performs
LoginLogger "1" *-- "0..*" LoginEntry : records


WardEmployee ..> Resident : updates
WardEmployee ..> DoctorVisit : logs
User ..> LoginLogger : uses
Doctor ..> Resident : views