package com.yangyang.hibernate.service;

import java.util.List;

public interface ICashier {
    void checkOut(String username, List<String> isbns);
}
