package com.example.studentdatabase

class Student {
    var name: String? = null
    var fatherName: String? = null
    var address: String? = null
    var gender: String? = null
    var contactNo: String? = null
    var nicNo: String? = null

    constructor()

    constructor(
    name: String?,
    fatherName: String?,
    address: String?,
    gender: String?,
    contactNo: String?,
    nicNo: String?
    ) {
        this.name = name
        this.fatherName = fatherName
        this.address = address
        this.gender = gender
        this.contactNo= contactNo
        this.nicNo = nicNo

    }
}
