package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PointServiceTest {
	@MockBean
	PointRepository pointRepository;
	@Autowired
	Sink sink;

	@Test
	public void createsPointBalance() throws Exception {
		CustomerCreateEvent event = new CustomerCreateEvent();
		event.setId("foo");
		sink.input().send(MessageBuilder.withPayload(event).build());
        ArgumentCaptor<Point> captor = ArgumentCaptor.forClass(Point.class);
        verify(this.pointRepository).save(captor.capture());
		System.out.println(captor.getValue());
        assertThat(captor.getValue()).isNotNull();
        assertThat(captor.getValue().getCustomerId()).isEqualTo("foo");
	}

}