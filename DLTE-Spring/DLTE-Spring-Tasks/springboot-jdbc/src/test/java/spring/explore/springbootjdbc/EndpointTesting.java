package spring.explore.springbootjdbc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EndpointTesting {

    @MockBean
    private MyBankService myBankService;

    @InjectMocks
    private MyBankController myBankController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAproval() throws Exception {
        String request = "{\n" +
                "        \"transactionId\": 2024002,\n" +
                "        \"transactionDate\": \"2024-02-19\",\n" +
                "        \"transactionAmount\": 4000.0,\n" +
                "        \"transactionTo\": \"Sam\",\n" +
                "        \"remarks\": \"Rent\",\n" +
                "        \"transactionBy\": \"John\"\n" +
                "    }";

        Transaction transaction = new Transaction(2024002L, new Date("02/19/2024"),4000.0,"Sam","Rent","John");
        when(myBankService.apiSave(any())).thenReturn(transaction);

//        mockMvc.perform(post("/transaction/new").contentType(MediaType.APPLICATION_JSON).content(request))
//                .andExpect(status().isOk());

        mockMvc.perform(post("/transaction/new").contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(status().isOk()).
                andExpect(jsonPath("$.transactionId").value(2024002L)).
                andExpect(jsonPath("$.transactionDate").value("2024-02-18T18:30:00.000+00:00")).
                andExpect(jsonPath("$.transactionAmount").value(4000.0)).
                andExpect(jsonPath("$.transactionTo").value("Sam")).
                andExpect(jsonPath("$.remarks").value("Rent")).
                andExpect(jsonPath("$.transactionBy").value("John"));
    }

    @Test
    void testFindBySender() throws Exception {
        Transaction transaction1 = new Transaction(768757847L, new Date("03/22/2024"), 7000.0, "John", "Bills", "Peter");
        List<Transaction> list = Stream.of(transaction1).collect(Collectors.toList());

        when(myBankService.apiFindBySender(eq("Peter"))).thenReturn(list);  // Corrected method name

        mockMvc.perform(get("/transaction/sender/Peter"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].transactionId").value(768757847L))
                .andExpect(jsonPath("$[0].transactionDate").value("2024-03-21T18:30:00.000+00:00"))
                .andExpect(jsonPath("$[0].transactionAmount").value(7000.0))
                .andExpect(jsonPath("$[0].transactionTo").value("John"))
                .andExpect(jsonPath("$[0].remarks").value("Bills"))
                .andExpect(jsonPath("$[0].transactionBy").value("Peter"));
    }


    @Test
    void testFindByReceiver() throws Exception {
        Transaction transaction1 = new Transaction(768757847L, new Date("03/22/2024"), 7000.0, "John", "Bills", "Peter");
        List<Transaction> list = Stream.of(transaction1).collect(Collectors.toList());

        when(myBankService.apiFindByReceiver(eq("John"))).thenReturn(list);  // Corrected method name

        mockMvc.perform(get("/transaction/receiver/John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].transactionId").value(768757847L))
                .andExpect(jsonPath("$[0].transactionDate").value("2024-03-21T18:30:00.000+00:00"))
                .andExpect(jsonPath("$[0].transactionAmount").value(7000.0))
                .andExpect(jsonPath("$[0].transactionTo").value("John"))
                .andExpect(jsonPath("$[0].remarks").value("Bills"))
                .andExpect(jsonPath("$[0].transactionBy").value("Peter"));
    }

    @Test
    void testFindByAmount() throws Exception {
        Transaction transaction1 = new Transaction(768757847L, new Date("03/22/2024"), 7000.0, "John", "Bills", "Peter");
        List<Transaction> list = Stream.of(transaction1).collect(Collectors.toList());

        when(myBankService.apiFindByAmount(eq(7000.0))).thenReturn(list);  // Corrected method name

        mockMvc.perform(get("/transaction/view/7000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].transactionId").value(768757847L))
                .andExpect(jsonPath("$[0].transactionDate").value("2024-03-21T18:30:00.000+00:00"))
                .andExpect(jsonPath("$[0].transactionAmount").value(7000.0))
                .andExpect(jsonPath("$[0].transactionTo").value("John"))
                .andExpect(jsonPath("$[0].remarks").value("Bills"))
                .andExpect(jsonPath("$[0].transactionBy").value("Peter"));

        //Failed
//        mockMvc.perform(get("/transaction/amount/7000"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].transactionId").value(768757847L))
//                .andExpect(jsonPath("$[0].transactionDate").value("2024-03-21T18:30:00.000+00:00"))
//                .andExpect(jsonPath("$[0].transactionAmount").value(7000.0))
//                .andExpect(jsonPath("$[0].transactionTo").value("John"))
//                .andExpect(jsonPath("$[0].remarks").value("Bills"))
//                .andExpect(jsonPath("$[0].transactionBy").value("Peter"));
    }

}
