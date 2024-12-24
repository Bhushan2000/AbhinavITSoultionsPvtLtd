package com.example.abhinavitsoultionspvtltd.ui.activities

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.abhinavitsoultionspvtltd.R
import com.example.abhinavitsoultionspvtltd.data.room.entity.Member
import com.example.abhinavitsoultionspvtltd.data.viewModel.MemberViewModel
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var etMemberMobileNumber: TextInputEditText
    private lateinit var etMemberName: TextInputEditText
    private lateinit var etMemberSubscriptionFee: TextInputEditText
    private lateinit var etMemberDepositAmount: TextInputEditText
    private lateinit var etMemberLoanAmoount: TextInputEditText
    private lateinit var etMemberDateOfBirth: TextInputEditText
    private lateinit var etMemberDateOfJoining: TextInputEditText
    private lateinit var etMemberDateOfMarriage: TextInputEditText
    private lateinit var etMemberCaste: TextInputEditText
    private lateinit var etMemberReligion: TextInputEditText
    private lateinit var etMemberCategory: TextInputEditText
    private lateinit var etMemberAadharNumber: TextInputEditText

    private lateinit var rgGender: RadioGroup
    private lateinit var rgMaritialStatus: RadioGroup
    private lateinit var rgRole: RadioGroup

    private lateinit var btnSubmit: Button

    private lateinit var mobileNumber: String
    private lateinit var name: String
    private lateinit var subscriptionFee: String
    private lateinit var depositAmount: String
    private lateinit var loanAmount: String
    private lateinit var dob: String
    private lateinit var doj: String
    private lateinit var dom: String
    private lateinit var cast: String
    private lateinit var religion: String
    private lateinit var category: String
    private lateinit var aadharNumber: String

    private var gender: String = ""
    private var maritialStatus: String = ""
    private var role: String = ""

    private val viewModel: MemberViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initView()
        onClick()
    }


    private fun initView() {
        etMemberMobileNumber = findViewById(R.id.etMemberMobileNumber)
        etMemberName = findViewById(R.id.etMemberName)
        etMemberSubscriptionFee = findViewById(R.id.etMemberSubscriptionFee)
        etMemberDepositAmount = findViewById(R.id.etMemberDepositAmount)
        etMemberLoanAmoount = findViewById(R.id.etMemberLoanAmount)
        etMemberDateOfBirth = findViewById(R.id.etDOB)
        etMemberDateOfJoining = findViewById(R.id.etDOJ)
        etMemberDateOfMarriage = findViewById(R.id.etDOM)
        etMemberCaste = findViewById(R.id.etMemberCast)
        etMemberReligion = findViewById(R.id.etMemberReligion)
        etMemberCategory = findViewById(R.id.etMemberCategory)
        etMemberAadharNumber = findViewById(R.id.etMemberAadharNo)
        rgGender = findViewById(R.id.rgGender)
        rgMaritialStatus = findViewById(R.id.rgMaritialStatus)
        rgRole = findViewById(R.id.rgRole)
        btnSubmit = findViewById(R.id.btnSubmit)
    }

    private fun onClick() {

        btnSubmit.setOnClickListener {
            mobileNumber = etMemberMobileNumber.text.toString().trim()
            name = etMemberName.text.toString().trim()
            subscriptionFee = etMemberSubscriptionFee.text.toString().trim()
            depositAmount = etMemberDepositAmount.text.toString().trim()
            loanAmount = etMemberLoanAmoount.text.toString().trim()
            dob = etMemberDateOfBirth.text.toString().trim()
            doj = etMemberDateOfJoining.text.toString().trim()
            dom = etMemberDateOfMarriage.text.toString().trim()
            cast = etMemberCaste.text.toString().trim()
            religion = etMemberReligion.text.toString().trim()
            category = etMemberCategory.text.toString().trim()
            aadharNumber = etMemberAadharNumber.text.toString().trim()

            val selectedIdMRole = rgRole.checkedRadioButtonId
            if (selectedIdMRole != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedIdMRole)
                role = selectedRadioButton.text.toString()
            } else {
                 role = "" // Optionally set a default value
            }
            val selectedIdGender = rgGender.checkedRadioButtonId
            if (selectedIdGender != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedIdGender)
                gender = selectedRadioButton.text.toString()
            } else {
                 gender = ""// Optionally set a default value
            }

            val selectedIdMaritial = rgMaritialStatus.checkedRadioButtonId
            if (selectedIdMaritial != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedIdMaritial)
                maritialStatus = selectedRadioButton.text.toString()
            } else {
                Toast.makeText(this, "No option selected", Toast.LENGTH_SHORT).show()
                maritialStatus = "" // Optionally set a default value
            }

            if (validateFields(
                    mobileNumber,
                    name,
                    role,
                    subscriptionFee,
                    depositAmount,
                    loanAmount,
                    gender,
                    dob,
                    doj,
                    maritialStatus,
                    dom,
                    cast,
                    religion,
                    category,
                    aadharNumber
                )
            ) {
                // send data to room database
                val result = viewModel.addMember(
                    Member(
                        mobileNumber = mobileNumber,
                        name = name,
                        role = role,
                        subscriptionFee = subscriptionFee,
                        depositAmount = depositAmount,
                        loanAmount = loanAmount,
                        gender = gender,
                        dob = dob,
                        doj = doj,
                        maritialStatus = maritialStatus,
                        dom = dom,
                        cast = cast,
                        religion = religion,
                        category = category,
                        aadharNumber = aadharNumber
                    )
                )
//                if (result) {
                // clear the form
                etMemberMobileNumber.setText("")
                etMemberName.setText("")
                etMemberSubscriptionFee.setText("")
                etMemberDepositAmount.setText("")
                etMemberLoanAmoount.setText("")
                etMemberDateOfBirth.setText("")
                etMemberDateOfJoining.setText("")
                etMemberDateOfMarriage.setText("")
                etMemberCaste.setText("")
                etMemberReligion.setText("")
                etMemberCategory.setText("")
                etMemberAadharNumber.setText("")

                rgGender.clearCheck()
                rgRole.clearCheck()
                rgMaritialStatus.clearCheck()
                showToast("Member added Successfully")
//                } else {
//                    showToast("Error while adding the member")
//                }
            } else {
//                showToast("Please fill all the required fields")
            }
        }
    }

    private fun validateFields(
        mobileNumber: String?,
        name: String?,
        role: String?,
        subscriptionFee: String?,
        depositAmount: String?,
        loanAmount: String?,
        gender: String?,
        dob: String?,
        doj: String?,
        maritialStatus: String?,
        dom: String?,
        cast: String?,
        religion: String?,
        category: String?,
        aadharNumber: String?,
    ): Boolean {
        when {
            mobileNumber.isNullOrBlank() || mobileNumber.length != 10 || !isPositiveNumber(mobileNumber) -> {
                showToast("Enter a valid 10-digit mobile number")
                return false
            }
            name.isNullOrBlank() -> {
                showToast("Name cannot be empty")
                return false
            }


            role.isNullOrBlank() -> {
                showToast("Role cannot be empty")
                return false
            }

            subscriptionFee.isNullOrBlank() || !isPositiveNumber(subscriptionFee) -> {
                showToast("Subscription fee must be a positive number")
                return false
            }

            depositAmount.isNullOrBlank() || !isPositiveNumber(depositAmount) -> {
                showToast("Deposit amount must be a positive number")
                return false
            }

            loanAmount.isNullOrBlank() || !isPositiveNumber(loanAmount) -> {
                showToast("Loan amount must be a positive number")
                return false
            }

            gender.isNullOrBlank() -> {
                showToast("Gender cannot be empty")
                return false
            }

            dob.isNullOrBlank() || !isValidDate(dob) -> {
                showToast("Enter a valid date of birth (yyyy-MM-dd)")
                return false
            }

            doj.isNullOrBlank() || !isValidDate(doj) -> {
                showToast("Enter a valid date of joining (yyyy-MM-dd)")
                return false
            }

            maritialStatus.isNullOrBlank() -> {
                showToast("Marital status cannot be empty")
                return false
            }

            dom.isNullOrBlank() || !isValidDate(dom) -> {
                showToast("Enter a valid date of marriage (yyyy-MM-dd)")
                return false
            }

            cast.isNullOrBlank() -> {
                showToast("Caste cannot be empty")
                return false
            }

            religion.isNullOrBlank() -> {
                showToast("Religion cannot be empty")
                return false
            }

            category.isNullOrBlank() -> {
                showToast("Category cannot be empty")
                return false
            }

            aadharNumber.isNullOrBlank() || !aadharNumber.matches(Regex("^[0-9]{12}$")) -> {
                showToast("Enter a valid 12-digit Aadhar number")
                return false
            }

            else -> return true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun isPositiveNumber(value: String): Boolean {
        return value.toDoubleOrNull()?.let { it > 0 } ?: false
    }

    private fun isValidDate(date: String): Boolean {
        return try {
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).apply {
                isLenient = false
            }.parse(date) != null
        } catch (e: Exception) {
            false
        }
    }

}