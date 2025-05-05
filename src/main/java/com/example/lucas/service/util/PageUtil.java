package com.example.lucas.service.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Utility class for pagination.
 * Provides default values and methods to construct Pageable objects.
 *
 * @author Lucas
 */
public interface PageUtil {
	int DEFAULT_PAGE_LIMIT = 10;
	int DEFAULT_PAGE_NUMBER = 1;

	static Pageable getPageable(int pageNumber, int pageSize) {
		if (pageNumber < DEFAULT_PAGE_NUMBER) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		}
		if (pageSize < 1) {
			pageSize = DEFAULT_PAGE_LIMIT;
		}
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
		return pageable;
	}
}