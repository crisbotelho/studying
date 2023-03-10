package com.company.cris.view.request;

import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;

public record DepartmentRequest(@NotEmpty String name, @NotEmpty BigInteger number) {
}
