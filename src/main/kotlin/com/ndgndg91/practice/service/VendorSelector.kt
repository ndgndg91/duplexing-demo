package com.ndgndg91.practice.service

import com.ndgndg91.practice.service.model.Vendor
import org.springframework.stereotype.Service

@Service
class VendorSelector(
    private val nhnClient: NhnClient,
    private val infoBankClient: InfoBankClient,
    private val vendorManager: VendorManager
) {
    fun primary(): SmsClient {
        return when(vendorManager.primary()) {
            Vendor.NHN -> nhnClient
            Vendor.INFO_BANK -> infoBankClient
        }
    }

    fun secondary(): SmsClient {
        return when(vendorManager.secondary()) {
            Vendor.NHN -> nhnClient
            Vendor.INFO_BANK -> infoBankClient
        }
    }
}
