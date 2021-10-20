package org.homework.model;

import java.io.Serializable;

@FunctionalInterface
public interface BaseModel<ID> extends Serializable {

    ID getId();
}
