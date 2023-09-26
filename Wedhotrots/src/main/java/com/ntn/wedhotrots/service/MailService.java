package com.ntn.wedhotrots.service;

import java.util.Map;

public interface MailService {
    void sendEmail(Map<String,String> params);
}
