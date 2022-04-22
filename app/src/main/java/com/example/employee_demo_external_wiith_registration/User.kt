package com.example.employee_demo_external_wiith_registration

data class User (var ur_email:String,var ur_city:String,var ur_password:String)
{
    var ur_id:Int=0
    constructor(ur_id:Int,ur_email:String,ur_city: String,ur_password: String):this(ur_email, ur_city, ur_password)
    {
        this.ur_id=ur_id
    }
}