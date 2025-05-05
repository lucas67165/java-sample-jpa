package com.example.lucas.data.filter;

import lombok.Data;

/**
 * Base class for pagination parameters.
 * Provides default values for page number and page size.
 * Extend this class in filters that require pagination.
 *
 * @author Lucas
 */
@Data
public class PaginationFilter {
    private int pageNumber = 1;
    private int pageSize = 10;
}
