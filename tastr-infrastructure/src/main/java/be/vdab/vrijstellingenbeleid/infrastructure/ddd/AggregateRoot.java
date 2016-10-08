package be.vdab.vrijstellingenbeleid.infrastructure.ddd;

import be.vdab.vrijstellingenbeleid.infrastructure.messaging.Command;
import be.vdab.vrijstellingenbeleid.infrastructure.messaging.CommandHandler;
import be.vdab.vrijstellingenbeleid.infrastructure.messaging.Event;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@MappedSuperclass
public abstract class AggregateRoot<ID extends Id> extends BaseEntity<ID> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AggregateRoot.class);

    @Version
    @Column(nullable = false)
    private int versie;
    @JsonIgnore
    @Transient
    private final List<CommandHandler> commandHandlers = newArrayList();
    @JsonIgnore
    @Transient
    private final List<Event<ID>> uncommittedEvents = newArrayList();

    protected AggregateRoot() {
        this.registerCommandHandlers();
    }

    public int getVersie() {
        return versie;
    }

    private void registerCommandHandlers() {
        this.getCommandHandlers().stream()
            .forEach(this.commandHandlers::add);
    }

    protected List<CommandHandler> getCommandHandlers() {
        return newArrayList();
    }

    public void execute(Command<?> command) {
        LOGGER.debug(String.format("Received command %s", command));
        checkVersie(command);
        commandHandlers.stream()
            .filter(commandHandler -> commandHandler.canHandle(command))
            .forEach(commandHandler -> commandHandler.handle(command));
    }

    private void checkVersie(Command<?> command) {
        if(this.versie != command.getVersie()) {
            throw new OptimisticLockException(String.format("Command met versie %s probeerde een aanpassing te doen aan aggregateroot %s met versie %s. Het command was %s.", command.getVersie(), this.getClass().getSimpleName(), getVersie(), command));
        }
    }

    public List<Event<ID>> getUncommittedEvents(){
        return uncommittedEvents;
    }

    void clearUncommittedEvents() {
        uncommittedEvents.clear();
    }

    protected void stuurEvent(Event<ID> event){
        this.uncommittedEvents.add(event);
    }
}
