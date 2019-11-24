package com.nutriscan.shared.repositories;

import androidx.lifecycle.MutableLiveData;

import com.nutriscan.shared.domain.Person;

public interface IPersonRepository {
    MutableLiveData<Person> getPerson();
}
