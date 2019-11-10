package com.nutriscan.shared.repositories;
import android.arch.lifecycle.MutableLiveData;

import com.nutriscan.shared.domain.Person;

/**
 * Repository containing the Person data
 */
public class PersonRepository {
    private static PersonRepository instance;

    private MutableLiveData<Person> personMutableLiveData;

    private PersonRepository() {
        personMutableLiveData = new MutableLiveData<>();
    }

    public static PersonRepository getInstance() {
        if (instance == null) instance = new PersonRepository();
        return instance;
    }

    /**
     * @return An observable of a Person containing their ID
     */
    public MutableLiveData<Person> getPerson() {
        return personMutableLiveData;
    }
}
