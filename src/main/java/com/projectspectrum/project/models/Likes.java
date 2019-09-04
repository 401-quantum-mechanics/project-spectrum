package com.projectspectrum.project.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

}
