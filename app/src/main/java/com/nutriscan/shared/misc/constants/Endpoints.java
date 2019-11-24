package com.nutriscan.shared.misc.constants;

import androidx.annotation.NonNull;

import com.nutriscan.shared.misc.constants.APIConstants;
import com.nutriscan.shared.domain.Person;

import java.util.Locale;

public class Endpoints {

    /**
     * @param person The person of which to get the scan history
     * @return A URL for retrieving the profile scan history for a given person
     */
    public static String getProfileScanHistoryEndpoint(@NonNull Person person) {
        return String.format("%s/scans/%s", APIConstants.API_BASE_URL, person.getId());
    }

    public static String getProductEndpoint(long upc) {
        return String.format(Locale.US, "%s/products/%d", APIConstants.API_BASE_URL, upc);
    }
}
