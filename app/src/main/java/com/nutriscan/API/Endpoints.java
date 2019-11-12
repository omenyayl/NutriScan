package com.nutriscan.API;

import androidx.annotation.NonNull;

import com.nutriscan.misc.constants.APIConstants;
import com.nutriscan.shared.domain.Person;

public class Endpoints {

    /**
     * @param person The person of which to get the scan history
     * @return A URL for retrieving the profile scan history for a given person
     */
    public static String getProfileScanHistoryEndpoint(@NonNull Person person) {
        return String.format("%s/scans/%s", APIConstants.API_BASE_URL, person.getId());
    }
}
