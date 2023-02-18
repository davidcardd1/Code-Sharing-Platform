package platform.repositories;

import org.springframework.data.repository.CrudRepository;
import platform.model.Code;

import java.util.List;

public interface CodeRepository extends CrudRepository<Code, String> {

    Code findByIdIs(String id);

    List<Code> findAll();

    List<Code> findAllByViewsRestrictedAndTimeRestricted(Boolean vr, Boolean tr);
}
