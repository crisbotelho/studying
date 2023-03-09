package com.company.cris.view.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProjectRequest(String name,
                             BigDecimal value,
                             BigDecimal cost,
                             LocalDate startDate,
                             LocalDate endDate,
                             Long departmentId) {
}
