package com.example.lucas.data.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Lucas
 */
@Data
public class PageDTO {
	private List<?> items;
	private long total;

	public PageDTO(Page<?> page) {
		this.items = page.getContent();
		this.total = page.getTotalElements();
	}
}
