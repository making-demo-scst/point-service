package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = "com.example:customer-service", workOffline = true)
public class PointServiceConsumerTest {
	@MockBean
	PointRepository pointRepository;
	@Autowired
	StubTrigger stubTrigger;

	@Test
	public void createsPointBalance() throws Exception {
		stubTrigger.trigger("create-customer");
        ArgumentCaptor<Point> captor = ArgumentCaptor.forClass(Point.class);
        verify(this.pointRepository).save(captor.capture());
        assertThat(captor.getValue()).isNotNull();
        assertThat(captor.getValue().getCustomerId()).isEqualTo("foo");
	}

}