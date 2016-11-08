package com.yangyang.javaPerform;

import com.yangyang.model.Person;

public class PersonFactory {

    //private static Person person = new Person();

    public static Person getPerson() throws Exception{
        Person person = new Person();
        return person.clonePerson();
    }

}
