package com.vti.exam.specification;

import com.vti.exam.entity.Account;
import com.vti.exam.entity.Department;
import com.vti.exam.form.AccountFilterForm;
import com.vti.exam.form.DepartmentFilterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class DepartmentSpecification {
    public static Specification<Department> buildSpec(DepartmentFilterForm form){
        return (root, query, builder) -> {
            if (form == null) {
                return null;
            }
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(form.getSearch())) {
                String pattern = "%" + form.getSearch().trim() + "%";
                Path<String> name = root.get("name");
                Predicate hasNameLike = builder.like(name, pattern);
                predicates.add(builder.or(hasNameLike));
            }

            if(form.getMinTotal() != null) {
                Path<Integer> totalMember = root.get("totalMember");
                Predicate predicate = builder.greaterThanOrEqualTo(totalMember, form.getMinTotal());
                predicates.add(predicate);
            }

            if (form.getMaxTotal() != null) {
                Path<Integer> totalMember = root.get("totalMember");
                Predicate predicate = builder.lessThanOrEqualTo(totalMember, form.getMaxTotal());
                predicates.add(predicate);
            }

            if (form.getType() != null) {
                Path<String> type = root.get("type");
                Predicate predicate = builder.equal(type, form.getType());
                predicates.add(predicate);
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
