package com.abhinav.supplierfinance.rest;

import com.abhinav.supplierfinance.entity.Supplier;
import com.abhinav.supplierfinance.entity.SupplierLogin;
import com.abhinav.supplierfinance.exception.SupplierAlreadyExistsException;
import com.abhinav.supplierfinance.service.SupplierService;
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
public class SupplierControllerTest {

    @Mock
    SupplierService service = mock(SupplierService.class);

    @InjectMocks
    SupplierController controller = new SupplierController();

    @Test
    void should_verify_status_as_ok_and_call_service_to_register_supplier() throws SupplierAlreadyExistsException {
        // Given
        Supplier supplier = new Supplier();
        supplier.setSupplierCode("test");
        when(service.registerSupplier(supplier)).thenReturn(supplier);
        // When
        ResponseEntity<Supplier> result = controller.registerSupplier(supplier);
        // Then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getSupplierCode()).isEqualTo(supplier.getSupplierCode());
    }

    @Test
    void should_verify_status_as_ok_and_call_service_to_check_login_details() {
        // Given
        SupplierLogin loginDetails = new SupplierLogin();
        when(service.loginSupplier(loginDetails)).thenReturn(true);
        // When
        ResponseEntity<Boolean> result = controller.login(loginDetails);
        // Then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isTrue();
    }

    @Test
    void should_verify_status_as_ok_and_call_service_to_get_all_suppliers() {
        // Given
        List<Supplier> suppliers = Collections.singletonList(new Supplier());
        when(service.getAllSuppliers()).thenReturn(suppliers);
        // When
        ResponseEntity<List<Supplier>> result = controller.getAllSuppliers();
        // Then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).hasSize(1);
    }

    @Test
    void should_verify_status_as_ok_and_call_service_to_get_supplier_by_code() {
        // Given
        String code = "test";
        Supplier supplier = new Supplier();
        supplier.setSupplierCode(code);
        when(service.getSupplierByCode(code)).thenReturn(supplier);
        // When
        ResponseEntity<Supplier> result = controller.getSupplierByCode(code);
        // Then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getSupplierCode()).isEqualTo(code);
    }
}
