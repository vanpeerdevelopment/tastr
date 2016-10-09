package eu.tastr.infrastructure.messaging;

import eu.tastr.infrastructure.ddd.Id;
import eu.tastr.infrastructure.ddd.ValueObject;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public abstract class Event<ID extends Id> extends ValueObject {

    @Valid
    @NotNull
    private final ID aggregateRootId;

    protected Event(ID aggregateRootId){
        this.aggregateRootId = aggregateRootId;
    }

    public ID getAggregateRootId() {
        return aggregateRootId;
    }
}
