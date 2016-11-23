package com.example;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Point implements Serializable {
	@Id
	private String customerId;
	private Long value;

	public Point() {
	}

	public Point(String customerId, Long value) {
		this.customerId = customerId;
		this.value = value;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Point{" + "customerId='" + customerId + '\'' + ", value=" + value + '}';
	}
}
