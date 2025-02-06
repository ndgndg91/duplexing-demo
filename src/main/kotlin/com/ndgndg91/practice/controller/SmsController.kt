package com.ndgndg91.practice.controller

import com.ndgndg91.practice.controller.dto.request.SendFreeTextSmsRequest
import com.ndgndg91.practice.service.SmsService
import com.ndgndg91.practice.validation.sequence.ValidationSequence
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SmsController(
    private val smsService: SmsService
) {
    @PostMapping("/api/v1/notifications/sms/free-text")
    fun sendFreeTextSms(
        @RequestBody @Validated(ValidationSequence::class) body: SendFreeTextSmsRequest
    ): ResponseEntity<ApiResponse<Unit>> {
        smsService.send(body.toCommand())
        return ResponseEntity.accepted().body(Unit.toApiResponse())
    }
}
