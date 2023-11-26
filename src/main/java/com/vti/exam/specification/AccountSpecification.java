package com.vti.exam.specification;

import com.vti.exam.entity.Account;
import com.vti.exam.form.AccountFilterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class AccountSpecification {
    public static Specification<Account> buildSpec(AccountFilterForm form){
        return (root, query, builder) -> {
            if (form == null) {
                return null;
            }
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(form.getSearch())) {
                String pattern = "%" + form.getSearch().trim() + "%";
                Path<String> username = root.get("username");
                Predicate hasUsernameLike = builder.like(username, pattern);
                Path<String> firstName = root.get("firstName");
                Predicate hasFirstNameLike = builder.like(firstName, pattern);
                Path<String> lastName = root.get("lastName");
                Predicate hasLastNameLike = builder.like(lastName, pattern);
                predicates.add(builder.or(hasUsernameLike, hasFirstNameLike, hasLastNameLike));
            }

            if (form.getRole() != null) {
                Path<String> role = root.get("role");
                Predicate predicate = builder.equal(role, form.getRole());
                predicates.add(predicate);
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
