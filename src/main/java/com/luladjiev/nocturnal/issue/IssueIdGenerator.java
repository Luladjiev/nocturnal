package com.luladjiev.nocturnal.issue;

import com.luladjiev.nocturnal.issue.Issue;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class IssueIdGenerator implements IdentifierGenerator {
  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object obj)
      throws HibernateException {
    var issue = (Issue) obj;
    var identifierColumn =
        session.getEntityPersister(obj.getClass().getName(), obj).getIdentifierPropertyName();
    var query =
        String.format(
            "FROM %s WHERE project_id = '%s' ORDER BY %s DESC",
            obj.getClass().getSimpleName(), issue.getProject().getId(), identifierColumn);
    var ids = session.createQuery(query, Issue.class).setMaxResults(1).stream();

    var id =
        ids.map(o -> o.getId().replace(o.getProject().getId() + "-", ""))
            .mapToLong(Long::parseLong)
            .max()
            .orElse(0L);

    return String.format("%s-%d", issue.getProject().getId(), id + 1);
  }
}
