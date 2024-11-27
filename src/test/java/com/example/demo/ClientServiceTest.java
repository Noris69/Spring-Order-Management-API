package com.example.demo;

import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;

import com.example.demo.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    public void testFindAll() {
        Client client1 = new Client();
        client1.setId(1L);
        client1.setNom("Dupont");

        Client client2 = new Client();
        client2.setId(2L);
        client2.setNom("Durand");

        when(clientRepository.findAll()).thenReturn(Arrays.asList(client1, client2));

        List<Client> clients = clientService.findAll();

        assertEquals(2, clients.size());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Long clientId = 1L;
        Client client = new Client();
        client.setId(clientId);
        client.setNom("Dupont");

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        Optional<Client> result = clientService.findById(clientId);

        assertTrue(result.isPresent());
        assertEquals("Dupont", result.get().getNom());
        verify(clientRepository, times(1)).findById(clientId);
    }

    @Test
    public void testSave() {
        Client client = new Client();
        client.setNom("Dupont");

        when(clientRepository.save(client)).thenReturn(client);

        Client result = clientService.save(client);

        assertNotNull(result);
        assertEquals("Dupont", result.getNom());
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    public void testUpdate() {
        Long clientId = 1L;
        Client existingClient = new Client();
        existingClient.setId(clientId);
        existingClient.setNom("AncienNom");

        Client updatedDetails = new Client();
        updatedDetails.setNom("NouveauNom");

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(existingClient)).thenReturn(existingClient);

        Client result = clientService.update(clientId, updatedDetails);

        assertEquals("NouveauNom", result.getNom());
        verify(clientRepository, times(1)).findById(clientId);
        verify(clientRepository, times(1)).save(existingClient);
    }

    @Test
    public void testDeleteById() {
        Long clientId = 1L;

        doNothing().when(clientRepository).deleteById(clientId);

        clientService.deleteById(clientId);

        verify(clientRepository, times(1)).deleteById(clientId);
    }
}
