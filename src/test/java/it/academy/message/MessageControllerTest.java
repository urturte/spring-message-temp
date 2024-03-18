package it.academy.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class MessageControllerTest {

	@Mock
	private MessageBean messageBean;

	@Mock
	private MessageRepository repository;

	@InjectMocks
	private MessageController controller;

	@Test
	public void createNewMessage_shouldReturnSuccessMessage() {
		String message = "newMessage";

		Mockito.when(repository.save(any())).thenReturn(messageBean);
		Mockito.when(messageBean.getMessage()).thenReturn(message);

		String result = controller.createMessage(message);

		Assertions.assertThat(result).isEqualTo("Message: `" + message + "` was save successfully");
	}

}
