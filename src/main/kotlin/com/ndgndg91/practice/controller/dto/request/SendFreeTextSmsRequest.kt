package com.ndgndg91.practice.controller.dto.request

import com.ndgndg91.practice.service.SendSmsCommand
import com.ndgndg91.practice.validation.annotation.ValidE164
import com.ndgndg91.practice.validation.annotation.ValidMessageSize
import com.ndgndg91.practice.validation.annotation.ValidateFutureTimestamp
import com.ndgndg91.practice.validation.group.AdvancedSequence
import com.ndgndg91.practice.validation.group.BasicSequence
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull


data class SendFreeTextSmsRequest(
    @field:NotNull(message = "300000", groups = [BasicSequence::class])
    @field:NotBlank(message = "300000", groups = [BasicSequence::class])
    @field:ValidE164(groups = [AdvancedSequence::class])
    val phoneNumber: String?,
    @field:ValidMessageSize(message = "303000", maxBytes = 120, groups = [AdvancedSequence::class], nullable = true)
    val title: String? = null,
    @field:NotNull(message = "303000", groups = [BasicSequence::class])
    @field:ValidMessageSize(maxBytes = 2700, groups = [AdvancedSequence::class])
    val message: String?,
    val accountId: Long?,
    val name: String?,
    @field:ValidateFutureTimestamp(groups = [BasicSequence::class])
    val sendAt: Long? = null,
) {
    fun toCommand(): SendSmsCommand {
        return SendSmsCommand.freeText(
            accountId = accountId,
            name = name,
            phoneNumber = phoneNumber,
            title = title,
            message = message,
            sendAt = sendAt
        )
    }
}