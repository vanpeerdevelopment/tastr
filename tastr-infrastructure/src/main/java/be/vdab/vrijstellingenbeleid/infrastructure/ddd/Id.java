package be.vdab.vrijstellingenbeleid.infrastructure.ddd;

import javax.persistence.Access;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.persistence.AccessType.FIELD;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@MappedSuperclass
@Access(FIELD)
public abstract class Id extends ValueObject implements Serializable{

    @NotNull
    private final String value;

    protected Id() {
        this.value = null;
    }

    protected Id(String value) {
        this.value = value;
        checkArgument(isNotBlank(value), "Value moet ingevuld zijn.");
    }

    public String getValue() {
        return value;
    }
}
