package com.example.abhinavitsoultionspvtltd.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "member_table")
data class Member(
    @PrimaryKey(autoGenerate = true) val mId: Int=0,
    @ColumnInfo(name = "mobileNumber") val mobileNumber: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "role") val role: String?,
    @ColumnInfo(name = "subscriptionFee") val subscriptionFee: String?,
    @ColumnInfo(name = "depositAmount") val depositAmount: String?,
    @ColumnInfo(name = "loanAmount") val loanAmount: String?,
    @ColumnInfo(name = "gender") val gender: String?,
    @ColumnInfo(name = "dob") val dob: String?,
    @ColumnInfo(name = "doj") val doj: String?,
    @ColumnInfo(name = "maritialStatus") val maritialStatus: String?,
    @ColumnInfo(name = "dom") val dom: String?,
    @ColumnInfo(name = "cast") val cast: String?,
    @ColumnInfo(name = "religion") val religion: String?,
    @ColumnInfo(name = "category") val category: String?,
    @ColumnInfo(name = "aadharNumber") val aadharNumber: String?,
)
