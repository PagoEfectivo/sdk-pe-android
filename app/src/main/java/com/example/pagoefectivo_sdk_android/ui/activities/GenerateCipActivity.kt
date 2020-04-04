package com.example.pagoefectivo_sdk_android.ui.activities

import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.pagoefectivo_sdk_android.R
import com.example.pagoefectivo_sdk_android.ui.components.DatePickerDialogFragment
import com.example.pagoefectivo_sdk_android.ui.components.TimePickerDialogFragment
import com.example.pagoefectivo_sdk_android.util.Utils
import kotlinx.android.synthetic.main.activity_generate_cip.*
import kotlinx.android.synthetic.main.content_generate_cip.*
import pe.elcomercio.pagoefectivosdk.PagoEfectivoSdk
import pe.elcomercio.pagoefectivosdk.cip.CipListener
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipEntity
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipError
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipRequest
import pe.elcomercio.pagoefectivosdk.util.Currency
import pe.elcomercio.pagoefectivosdk.util.DocumentType
import java.util.*

class GenerateCipActivity : AppCompatActivity(), CipListener, OnDateSetListener,
    OnTimeSetListener {
    private lateinit var instance: PagoEfectivoSdk
    private val calendar = Calendar.getInstance()
    private var year = calendar[Calendar.YEAR]
    private var month = calendar[Calendar.MONTH]
    private var dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
    private var hourOfDay = calendar[Calendar.HOUR]
    private var minute = calendar[Calendar.MINUTE]
    private val currencyNameList: MutableList<String> =
        ArrayList()
    private val documentTypeNameList: MutableList<String> =
        ArrayList()
    private val currencyValueList: MutableList<Currency> =
        ArrayList()
    private val documentTypeValueList: MutableList<DocumentType> =
        ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_cip)
        init()
        setupViews()
    }

    private fun init() {
        initCurrencyValues()
        initCurrencyNames()
        initDocumentTypeValues()
        initDocumentTypeNames()
        //Get Instance from PagoEfectivo SDK
        instance = PagoEfectivoSdk.getInstance()
        //Setting Up currency adapter
        val currencyAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            currencyNameList
        )
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spiCurrency.setAdapter(currencyAdapter)
        //Setting Up documentType adapter
        val documentTypeAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            documentTypeNameList
        )
        documentTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spiUserDocumentType.setAdapter(documentTypeAdapter)
    }

    private fun setupViews() {
        generateCipButton.setOnClickListener {
            showMessage(getResources().getString(R.string.generating_cip))
            instance.generateCip(getCipRequestObject(), this)
        }
    }

    private fun initCurrencyValues() {
        currencyValueList.add(Currency.PEN)
        currencyValueList.add(Currency.USD)
    }

    private fun initCurrencyNames() {
        currencyNameList.add(getString(R.string.currency_pen))
        currencyNameList.add(getString(R.string.currency_usd))
    }

    private fun initDocumentTypeValues() {
        documentTypeValueList.add(DocumentType.DNI)
        documentTypeValueList.add(DocumentType.LIBRETA_MILITAR)
        documentTypeValueList.add(DocumentType.OTROS)
        documentTypeValueList.add(DocumentType.PARTIDA_NACIMIENTO)
        documentTypeValueList.add(DocumentType.PASSPORT)
    }

    private fun initDocumentTypeNames() {
        documentTypeNameList.add(getString(R.string.document_dni))
        documentTypeNameList.add(getString(R.string.document_lmi))
        documentTypeNameList.add(getString(R.string.document_nan))
        documentTypeNameList.add(getString(R.string.document_par))
        documentTypeNameList.add(getString(R.string.document_pas))
    }

    private fun setCurrentDateTime() {
        val pm_am = if (hourOfDay <= 12) "AM" else "PM"
        lblDateTimeExpiry!!.text = getString(
            R.string.dateformat_with_values,
            Utils.addZeroToNumber(year.toString()),
            Utils.addZeroToNumber((month + 1).toString()),
            dayOfMonth.toString(),
            Utils.addZeroToNumber(hourOfDay.toString()),
            Utils.addZeroToNumber(minute.toString()),
            pm_am
        )
    }//Object necessary for the request

    private fun getCipRequestObject(): CipRequest {
        val cipRequest = CipRequest()
        cipRequest.currency = currencyValueList[spiCurrency!!.selectedItemPosition]
        if (!txtAmount!!.text.toString().isEmpty()) {
            cipRequest.amount = txtAmount!!.text.toString().toDouble()
        }
        cipRequest.transactionCode = txtTransactionCode!!.text.toString()
        if (!lblDateTimeExpiry!!.text.toString().isEmpty()) {
            val calendar = Calendar.getInstance()
            calendar[Calendar.YEAR] = year
            calendar[Calendar.MONTH] = month
            calendar[Calendar.DATE] = dayOfMonth
            calendar[Calendar.HOUR_OF_DAY] = hourOfDay
            calendar[Calendar.MINUTE] = minute
            calendar[Calendar.SECOND] = 0
            cipRequest.dateExpiry = calendar.time
        }
        cipRequest.additionalData = txtAdditionalData!!.text.toString()
        cipRequest.paymentConcept = txtPaymentConcept!!.text.toString()
        cipRequest.userEmail = txtUserEmail!!.text.toString()
        cipRequest.userName = txtUserName!!.text.toString()
        cipRequest.userLastName = txtUserLastName!!.text.toString()
        cipRequest.userUbigeo = txtUserUbigeo!!.text.toString()
        cipRequest.userCountry = txtUserCountry!!.text.toString()
        if (!txtUserDocumentNumber!!.text.toString().isEmpty()) {
            cipRequest.userDocumentType =
                documentTypeValueList[spiUserDocumentType!!.selectedItemPosition]
            cipRequest.userDocumentNumber = txtUserDocumentNumber!!.text.toString()
        }
        cipRequest.userPhone = txtUserPhone!!.text.toString()
        cipRequest.userCodeCountry = txtUserCodeCountry!!.text.toString()
        if (!txtAdminEmail!!.text.toString().isEmpty()) {
            cipRequest.adminEmail = txtAdminEmail!!.text.toString()
        }
        return cipRequest
    }

    override fun onCipSuccessful(cipEntity: CipEntity) {
        val intent = Intent(this, PaymentMethodActivity::class.java)
        intent.putExtra(getString(R.string.serialize_cip), cipEntity)
        startActivity(intent)
    }

    override fun onCipError(list: List<CipError>) {
        val errorMessage = StringBuilder()
        for (error in list) {
            errorMessage.append("- (").append(error.code).append(")").append(" | ")
                .append(error.field).append(" --> ").append(error.message).append("\n")
        }
        showMessage(errorMessage.toString())
    }

    override fun onCipFailure(s: String) {
        showMessage(s)
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG)
    }

    fun showDatePickerDialog(view: View?) {
        val datePickerDialogFragment: DialogFragment =
            DatePickerDialogFragment.newInstance()
        datePickerDialogFragment.show(supportFragmentManager, "date_picker_dialog_fragment")
    }

    private fun showTimePickerDialog() {
        val timePickerDialogFragment: DialogFragment =
            TimePickerDialogFragment.newInstance()
        timePickerDialogFragment.show(supportFragmentManager, "time_picker_dialog_fragment")
    }

    override fun onDateSet(
        view: DatePicker,
        year: Int,
        month: Int,
        dayOfMonth: Int
    ) {
        this.year = year
        this.month = month
        this.dayOfMonth = dayOfMonth
        showTimePickerDialog()
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        this.hourOfDay = hourOfDay
        this.minute = minute
        setCurrentDateTime()
    }
}