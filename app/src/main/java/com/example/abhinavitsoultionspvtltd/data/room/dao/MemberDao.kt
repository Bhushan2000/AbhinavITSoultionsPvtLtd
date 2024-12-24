package com.example.abhinavitsoultionspvtltd.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.abhinavitsoultionspvtltd.data.room.entity.Member
import kotlinx.coroutines.flow.Flow

@Dao
interface MemberDao {
    @Query("Select * From member_table order by mId desc")
    fun getAllMembers(): Flow<List<Member>>

    @Insert
    suspend fun insertMember(member: Member) : Long
}