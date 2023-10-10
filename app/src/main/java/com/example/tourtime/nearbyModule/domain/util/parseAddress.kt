package com.example.tourtime.nearbyModule.domain.util


fun parseAddress(htmlAddress: String): String {
    val extendedAddressPattern = Regex("<span class=\"extended-address\">(.*?)</span>")
    val localityPattern = Regex("<span class=\"locality\">(.*?)</span>")
    val regionPattern = Regex("<span class=\"region\">(.*?)</span>")
    val countryNamePattern = Regex("<span class=\"country-name\">(.*?)</span>")

    val extendedAddress = extendedAddressPattern.find(htmlAddress)?.groupValues?.get(1) ?: ""
    val locality = localityPattern.find(htmlAddress)?.groupValues?.get(1) ?: ""
    val region = regionPattern.find(htmlAddress)?.groupValues?.get(1) ?: ""
    val countryName = countryNamePattern.find(htmlAddress)?.groupValues?.get(1) ?: ""

    val simpleAddress = buildString {
        if (extendedAddress.isNotEmpty()) {
            append(extendedAddress)
            append(", ")
        }
        if (locality.isNotEmpty()) {
            append(locality)
            append(", ")
        }
        if (region.isNotEmpty()) {
            append(region)
            append(", ")
        }
        if (countryName.isNotEmpty()) {
            append(countryName)
        }
    }

    return simpleAddress
}