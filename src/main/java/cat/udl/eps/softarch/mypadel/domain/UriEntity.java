package cat.udl.eps.softarch.mypadel.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.atteo.evo.inflector.English;
import org.springframework.data.domain.Persistable;
import org.springframework.util.StringUtils;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;

/**
 * Created by http://rhizomik.net/~roberto/
 */
@MappedSuperclass
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="uri")
public abstract class UriEntity<ID extends Serializable> implements Persistable<ID> {

    private String uri;

    @Version
    private Long version;

    public String getUri() {
        return "/" + English.plural(StringUtils.uncapitalize(this.getClass().getSimpleName())) + "/" + getId();
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return version == null;
    }
}
