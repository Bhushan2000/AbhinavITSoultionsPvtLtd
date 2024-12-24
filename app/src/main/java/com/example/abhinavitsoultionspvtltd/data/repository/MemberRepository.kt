package com.example.abhinavitsoultionspvtltd.data.repository

import com.example.abhinavitsoultionspvtltd.data.room.dao.MemberDao
import com.example.abhinavitsoultionspvtltd.data.room.entity.Member
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemberRepository @Inject constructor(private val memberDao: MemberDao) {
//    Room's @Insert annotation typically returns:
//
//    The row ID if the insertion was successful.
//    -1 if the insertion failed (e.g., due to a conflict).

    suspend fun insertMember(member: Member) : Boolean{
     val result =  memberDao.insertMember(member)
        return result != -1L // Returns true if insertion was successful
    }
    val allMembers : Flow<List<Member>> = memberDao.getAllMembers()
}