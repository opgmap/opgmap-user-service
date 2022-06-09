package ru.opgmap.opgmap_user_service.hibernate;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import ru.opgmap.opgmap_user_service.model.Model;

import java.io.Serializable;

public class SettableSequenceGenerator extends SequenceStyleGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) {
        if (obj instanceof Model) {
            Model entity = (Model) obj;
            if (entity.getId() == null) {
                return super.generate(session, obj);
            }
            return entity.getId();
        }

        return super.generate(session, obj);
    }

}
