package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PointService {
	private final Logger log = LoggerFactory.getLogger(PointService.class);
	private final PointRepository pointRepository;

	public PointService(PointRepository pointRepository) {
		this.pointRepository = pointRepository;
	}

	@StreamListener(Sink.INPUT)
	@Transactional
	public void createsPointBalance(CustomerCreateEvent event) {
		log.info("create point balance for {}", event);
		pointRepository.save(new Point(event.getId(), 100L /* welcome point */));
	}
}
