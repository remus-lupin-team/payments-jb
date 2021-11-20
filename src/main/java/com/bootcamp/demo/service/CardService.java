package com.bootcamp.demo.service;

import com.bootcamp.demo.dao.FirestoreDao;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private final FirestoreDao firestoreDao;

    public CardService(FirestoreDao firestoreDao) {
        this.firestoreDao = firestoreDao;
    }
    public void removeCard(String id){
        this.firestoreDao.remove(id, "cards");
    }
}
