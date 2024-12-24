package com.example.abhinavitsoultionspvtltd.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.abhinavitsoultionspvtltd.R
import com.example.abhinavitsoultionspvtltd.data.room.entity.Member

class MemberAdapter :
    RecyclerView.Adapter<MemberAdapter.ViewHolder>() {
    private var memberList: List<Member> = emptyList()

    fun submitList(newUsers: List<Member>) {
        memberList = newUsers
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.tvNameValue)
        var mobileNumber: TextView = view.findViewById(R.id.tvMobileNumberValue)
        var role: TextView = view.findViewById(R.id.tvRoleValue)
        var subSubscriptionAmount: TextView = view.findViewById(R.id.tvSubscriptionValue)
        var loanAmount: TextView = view.findViewById(R.id.tvLoanAmountValue)
        var depositAmount: TextView = view.findViewById(R.id.tvDepositAmountValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.member_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = memberList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = memberList[position]
        holder.name.text = item.name
        holder.mobileNumber.text = item.mobileNumber.toString()
        holder.role.text = item.role
        holder.subSubscriptionAmount.text = "₹${item.subscriptionFee.toString()}/-"
        holder.loanAmount.text = "₹${item.loanAmount.toString()}/-"
        holder.depositAmount.text = "₹${item.depositAmount.toString()}/-"
    }
}
