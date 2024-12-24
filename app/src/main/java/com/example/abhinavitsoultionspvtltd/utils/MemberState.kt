package com.example.abhinavitsoultionspvtltd.utils

import com.example.abhinavitsoultionspvtltd.data.room.entity.Member


sealed class MemberState{
    object Loading : MemberState()
    data class Success(val members: List<Member>) : MemberState()
    data class Error(val message: String) : MemberState()
}
