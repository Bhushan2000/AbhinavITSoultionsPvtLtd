package com.example.abhinavitsoultionspvtltd.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.abhinavitsoultionspvtltd.data.room.dao.MemberDao
import com.example.abhinavitsoultionspvtltd.data.room.entity.Member

@Database(entities = [Member::class], version = 1, exportSchema = false)
abstract class MemberDatabase : RoomDatabase() {
    abstract fun memberDao(): MemberDao
}