package com.finance.plutus.transaction.repository;

import com.finance.plutus.transaction.model.Transaction;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** Plutus Created by Catalin on 12/22/2020 */
class TransactionRepositoryFilterImpl implements TransactionRepositoryFilter {

  private static final String SPACE_DELIMITER = " ";
  @PersistenceContext private EntityManager entityManager;

  @Override
  public List<Transaction> findAllFiltered(Map<String, Object> params, PageRequest page) {
    return find(params)
        .setFirstResult(page.getPageNumber() * page.getPageSize())
        .setMaxResults(page.getPageSize())
        .getResultList();
  }

  @Override
  public List<Transaction> findAllFiltered(Map<String, Object> params) {
    return find(params).getResultList();
  }

  private TypedQuery<Transaction> find(Map<String, Object> params) {
    StringBuilder queryBuilder = new StringBuilder();
    queryBuilder.append("SELECT t FROM Transaction t");
    prepareAndAppendQuery(params, queryBuilder);
    queryBuilder.append(" ORDER BY date DESC");
    TypedQuery<Transaction> jpaQuery =
        entityManager.createQuery(queryBuilder.toString(), Transaction.class);
    for (String key : params.keySet()) {
      jpaQuery.setParameter(key, params.get(key));
    }
    return jpaQuery;
  }

  private void prepareAndAppendQuery(Map<String, Object> params, StringBuilder queryBuilder) {
    List<String> whereClause = new ArrayList<>();
    if (params.containsKey("partnerId")) {
      whereClause.add("t.partner.id = :partnerId");
    }
    if (params.containsKey("type")) {
      whereClause.add("t.type = :type");
    }
    if (params.containsKey("startDate")) {
      whereClause.add("t.date >= :startDate");
    }
    if (params.containsKey("endDate")) {
      whereClause.add("t.date <= :endDate");
    }
    if (whereClause.size() > 0) {
      queryBuilder.append(SPACE_DELIMITER);
      queryBuilder.append("WHERE");
      queryBuilder.append(SPACE_DELIMITER);
      String delimiter = SPACE_DELIMITER + "AND" + SPACE_DELIMITER;
      queryBuilder.append(String.join(delimiter, whereClause));
    }
  }
}
