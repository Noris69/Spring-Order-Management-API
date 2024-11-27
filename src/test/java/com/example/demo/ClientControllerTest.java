
import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;
import com.example.demo.controller.ClientController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @Test
    public void testGetAllClients() throws Exception {
        Client client1 = new Client();
        client1.setId(1L);
        client1.setNom("Dupont");

        Client client2 = new Client();
        client2.setId(2L);
        client2.setNom("Durand");

        when(clientService.findAll()).thenReturn(Arrays.asList(client1, client2));

        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();

        mockMvc.perform(get("/api/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("Dupont"))
                .andExpect(jsonPath("$[1].nom").value("Durand"));

        verify(clientService, times(1)).findAll();
    }

    @Test
    public void testGetClientById() throws Exception {
        Long clientId = 1L;
        Client client = new Client();
        client.setId(clientId);
        client.setNom("Dupont");

        when(clientService.findById(clientId)).thenReturn(Optional.of(client));

        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();

        mockMvc.perform(get("/api/clients/{id}", clientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Dupont"));

        verify(clientService, times(1)).findById(clientId);
    }

    @Test
    public void testCreateClient() throws Exception {
        Client client = new Client();
        client.setNom("Dupont");

        when(clientService.save(any(Client.class))).thenReturn(client);

        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();

        mockMvc.perform(post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(client)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Dupont"));

        verify(clientService, times(1)).save(any(Client.class));
    }

    @Test
    public void testUpdateClient() throws Exception {
        Long clientId = 1L;
        Client clientDetails = new Client();
        clientDetails.setNom("NouveauNom");

        Client updatedClient = new Client();
        updatedClient.setId(clientId);
        updatedClient.setNom("NouveauNom");

        when(clientService.update(eq(clientId), any(Client.class))).thenReturn(updatedClient);

        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();

        mockMvc.perform(put("/api/clients/{id}", clientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("NouveauNom"));

        verify(clientService, times(1)).update(eq(clientId), any(Client.class));
    }

    @Test
    public void testDeleteClient() throws Exception {
        Long clientId = 1L;

        doNothing().when(clientService).deleteById(clientId);

        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();

        mockMvc.perform(delete("/api/clients/{id}", clientId))
                .andExpect(status().isOk());

        verify(clientService, times(1)).deleteById(clientId);
    }

    // Méthode utilitaire pour convertir un objet en JSON
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
