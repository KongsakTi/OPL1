





class Person(s: String) {
    private val hold = s.split(" ")
    def firstName = hold(0)
    def lastName = hold(1)
}


class Time(hr: Int, min: Int) extends Ordered[Time]{
    var hours = hr
    var minutes = min
    if(min > 59) {
        minutes = min - 60
        hours = hr + min/60
    }
    if(hours > 23 || hours < 0) throw new Exception
    else{
        None
    }
    def compare(that: Time) = hours*60+minutes - that.hours*60+minutes

}

// class BankAccount(initialBalance: Double) = {
//     private var balance = initialBalance
//     def currentBalance = balance
//     def deposit(amount: Double) = {balance += amount; balance}
//     def withdraw(amount: Double) = {balance -= amount; balance}    
// }

// class CheckingAccount(initialBalance: Double) ={
//     private var balance = initialBalance
//     def deposit(amount: Double) = {balance += amount-1; balance}
//     def withdraw(amount: Double) = {balance -= amount-1; balance}    
// }

