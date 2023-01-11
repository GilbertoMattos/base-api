package br.com.tecnoinfo.baseapi.specification

import br.com.tecnoinfo.baseapi.model.Project
import br.com.tecnoinfo.baseapi.model.SizeEnum
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDate

class SpecificationProject {

    companion object {

        fun name(name: String? = null, operation: QueryOperation = QueryOperation.EQUALS): Specification<Project> {
            return Specification<Project> { root, _, builder ->
                if (name != null) {
                    when (operation) {
                        QueryOperation.EQUALS -> builder.equal(root.get<String>("name"), name)
                        QueryOperation.LIKE -> builder.like(root.get("name"), "%$name%")
                        else -> builder.like(root.get("name"), "%$name%")
                    }
                } else null
            }
        }

        fun id(id: Long? = null, operation: QueryOperation = QueryOperation.EQUALS): Specification<Project> {
            return Specification<Project> { root, _, builder ->
                if (id != null) {
                    when (operation) {
                        QueryOperation.EQUALS -> builder.equal(root.get<Long>("id"), id)
                        QueryOperation.GREATER_THEN -> builder.greaterThan(root.get("id"), id)
                        QueryOperation.GREATER_THEN_EQUALS -> builder.greaterThanOrEqualTo(root.get("id"), id)
                        QueryOperation.LESS_THEN -> builder.lessThan(root.get("id"), id)
                        QueryOperation.LESS_THEN_EQUALS -> builder.lessThanOrEqualTo(root.get("id"), id)
                        else -> builder.equal(root.get<Long>("id"), id)
                    }
                } else null
            }
        }

        fun creationDate(creationDate: LocalDate? = null, operation: QueryOperation = QueryOperation.EQUALS): Specification<Project> {
            return Specification<Project> { root, _, builder ->
                if (creationDate != null) {
                    when (operation) {
                        QueryOperation.EQUALS -> builder.equal(root.get<LocalDate>("creationDate"), creationDate)
                        QueryOperation.LESS_THEN -> builder.lessThan(root.get<LocalDate>("creationDate"), creationDate)
                        QueryOperation.LESS_THEN_EQUALS -> builder.lessThanOrEqualTo(root.get<LocalDate>("creationDate"), creationDate)
                        QueryOperation.GREATER_THEN -> builder.greaterThan(root.get<LocalDate>("creationDate"), creationDate)
                        QueryOperation.GREATER_THEN_EQUALS -> builder.greaterThanOrEqualTo(root.get<LocalDate>("creationDate"), creationDate)
                        else -> builder.equal(root.get<LocalDate>("creationDate"), creationDate)
                    }
                } else null
            }
        }

        fun size(size: SizeEnum? = null, operation: QueryOperation = QueryOperation.EQUALS): Specification<Project> {
            return Specification<Project> { root, _, builder ->
                if (size != null) {
                    when (operation) {
                        QueryOperation.EQUALS -> builder.equal(root.get<String>("size"), size)
                        QueryOperation.LIKE -> builder.like(root.get("size"), "%$size%")
                        else -> builder.like(root.get("size"), "%$size%")
                    }
                } else null
            }
        }
    }
}

enum class QueryOperation {
    EQUALS,
    LIKE,
    GREATER_THEN,
    GREATER_THEN_EQUALS,
    LESS_THEN,
    LESS_THEN_EQUALS;
}