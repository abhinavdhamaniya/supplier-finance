package com.abhinav.supplierfinance.rest;

import com.abhinav.supplierfinance.entity.Client;
import com.abhinav.supplierfinance.entity.ClientLogin;
import com.abhinav.supplierfinance.exception.ClientAlreadyExistsException;
import com.abhinav.supplierfinance.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    ClientService service = mock(ClientService.class);

    @InjectMocks
    ClientController controller = new ClientController();

    @Test
    void should_verify_status_as_ok_and_call_service_to_register_client() throws ClientAlreadyExistsException {
        // Given
        Client client = new Client();
        client.setUsername("test");
        when(service.registerClient(client)).thenReturn(client);
        // When
        ResponseEntity<Client> result = controller.registerClient(client);
        // Then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getUsername()).isEqualTo(client.getUsername());
    }

    @Test
    void should_verify_status_as_ok_and_call_service_to_check_login_details() {
        // Given
        ClientLogin loginDetails = new ClientLogin();
        when(service.loginClient(loginDetails)).thenReturn(true);
        // When
        ResponseEntity<Boolean> result = controller.login(loginDetails);
        // Then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isTrue();
    }

    @Test
    void should_verify_status_as_ok_and_call_service_to_get_all_clients() {
        // Given
        List<Client> clients = Collections.singletonList(new Client());
        when(service.getAllClients()).thenReturn(clients);
        // When
        ResponseEntity<List<Client>> result = controller.getAllClients();
        // Then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).hasSize(1);
    }

    @Test
    void should_verify_status_as_ok_and_call_service_to_get_client_by_username() {
        // Given
        String username = "test";
        Client client = new Client();
        client.setUsername(username);
        when(service.getClientByUsername(username)).thenReturn(client);
        // When
        ResponseEntity<Client> result = controller.getClientByUsername(username);
        // Then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getUsername()).isEqualTo(username);
    }
}
