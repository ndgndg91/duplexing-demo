package com.ndgndg91.practice.service

import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.ndgndg91.practice.service.model.PhoneNumber
import com.ndgndg91.practice.service.model.Recipient
import com.ndgndg91.practice.service.model.SmsType

data class SendSmsCommand(
    val type: SmsType,
    val recipient: Recipient,
    val title: String?,
    val message: String,
    val sendAt: Long? = null
) {
    companion object {
        fun freeText(
            accountId: Long?,
            name: String?,
            phoneNumber: String?,
            title: String?,
            message: String?,
            sendAt: Long? = null
        ): SendSmsCommand {
            return SendSmsCommand(
                type = SmsType.FREE_TEXT,
                recipient = Recipient(
                    accountId = accountId,
                    name = name,
                    email = null,
                    phoneNumber = run {
                        val temp = PhoneNumberUtil.getInstance().parse(phoneNumber!!, null)
                        PhoneNumber(temp.countryCode, temp.nationalNumber)
                    }
                ),
                title = title,
                message = message!!,
                sendAt = sendAt
            )
        }

    }
}