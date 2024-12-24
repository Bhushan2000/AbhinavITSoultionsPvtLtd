package com.example.abhinavitsoultionspvtltd.data.viewModel

import android.provider.MediaStore.Audio.Genres.Members
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abhinavitsoultionspvtltd.data.repository.MemberRepository
import com.example.abhinavitsoultionspvtltd.data.room.entity.Member
import com.example.abhinavitsoultionspvtltd.utils.MemberState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemberViewModel @Inject constructor(private val memberRepository: MemberRepository) :
    ViewModel() {
    private val _memberState = MutableStateFlow<MemberState>(MemberState.Loading)
    val memberState: StateFlow<MemberState> = _memberState

    init {
        fetchMembers()
    }

    private fun fetchMembers() {
        viewModelScope.launch {
            memberRepository.allMembers.collect { member ->
                _memberState.value = MemberState.Success(member)
            }
        }
    }

    fun addMember(
        member: Member,
    ) {
        // Create a new coroutine to move the execution off the UI thread
        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                memberRepository.insertMember(member)
            } catch (e: Exception) {
                _memberState.value = MemberState.Error(e.message ?: ("Error Occured"))
            }
        }
    }
}