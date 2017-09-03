package com.qyf404.email.api;

import java.util.List;

public interface Mail {

    String getSubject();

    List<String> getRecipients();

    List<String> getCarbonCopies();

    List<String> getBlindCarbonCopies();

    String getContent();
}
