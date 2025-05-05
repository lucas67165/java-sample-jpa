package com.example.lucas.data.filter;

import com.example.lucas.data.enums.SortDirection;
import com.example.lucas.entity.User_;
import com.example.lucas.validation.ValidSortField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;

/**
 * @author Lucas
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserFilter extends PaginationFilter {
    private String username;
    private String email;
    private Integer gender;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String endDate;

    @ValidSortField(allowed = {User_.ID, User_.EMAIL, User_.CREATED_AT})
    private String sortBy = User_.ID;
    @Pattern(regexp = SortDirection.REGEX)
    private String direction = SortDirection.DESC.name();
}
