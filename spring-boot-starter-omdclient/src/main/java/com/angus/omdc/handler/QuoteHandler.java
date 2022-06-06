package com.angus.omdc.handler;

import com.angus.omdc.message.*;

public interface QuoteHandler {
    void processOmdMessage(OmdMessage message);
}
