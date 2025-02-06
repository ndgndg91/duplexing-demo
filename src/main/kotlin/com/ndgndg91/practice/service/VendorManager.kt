package com.ndgndg91.practice.service

import com.ndgndg91.practice.service.model.Vendor
import com.ndgndg91.practice.service.model.VendorPair
import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicReference

@Component
class VendorManager {
    private val providers = AtomicReference(VendorPair(Vendor.NHN, Vendor.INFO_BANK))

    fun primary(): Vendor = providers.get().primary

    fun secondary(): Vendor = providers.get().secondary

    fun swap() {
        providers.updateAndGet { current ->
            VendorPair(primary = current.secondary, secondary = current.primary)
        }
    }
}