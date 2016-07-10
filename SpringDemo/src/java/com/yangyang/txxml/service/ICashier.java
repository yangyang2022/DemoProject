package com.yangyang.txxml.service;

import java.util.List;

public interface ICashier {
    void checkOut(String username, List<String> isbns);
}
