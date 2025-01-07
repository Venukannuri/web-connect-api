package com.optimus.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.optimus.dao.DealRepository;
import com.optimus.dao.VendorRepository;
import com.optimus.model.Category;
import com.optimus.model.CategoryStatus;
import com.optimus.model.UserLookupInformation;
import com.optimus.model.Vendor;
import com.optimus.model.VendorHistory;
import com.optimus.service.DealService;
import com.optimus.service.DealServiceImpl;
import com.optimus.service.VendorHistoryService;
import com.optimus.service.VendorService;
import com.optimus.service.VendorServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {DealController.class})
@ExtendWith(SpringExtension.class)
class DealControllerTest {
    @Autowired
    private DealController dealController;

    @MockBean
    private DealService dealService;

    @MockBean
    private VendorHistoryService vendorHistoryService;

    @MockBean
    private VendorService vendorService;

    /**
     * Test {@link DealController#equals(Object)}.
     * <ul>
     *   <li>When other is different.</li>
     *   <li>Then return not equal.</li>
     * </ul>
     * <p>
     * Method under test: {@link DealController#equals(Object)}
     */
    @Test
    @DisplayName("Test equals(Object); when other is different; then return not equal")
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
        // Arrange
        DealController dealController = new DealController(null, new VendorServiceImpl(mock(VendorRepository.class)),
                mock(VendorHistoryService.class));

        // Act and Assert
        assertNotEquals(dealController, new DealController(null, new VendorServiceImpl(mock(VendorRepository.class)),
                mock(VendorHistoryService.class)));
    }

    /**
     * Test {@link DealController#equals(Object)}.
     * <ul>
     *   <li>When other is different.</li>
     *   <li>Then return not equal.</li>
     * </ul>
     * <p>
     * Method under test: {@link DealController#equals(Object)}
     */
    @Test
    @DisplayName("Test equals(Object); when other is different; then return not equal")
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
        // Arrange
        DealServiceImpl dealServices = new DealServiceImpl(mock(DealRepository.class));
        DealController dealController = new DealController(dealServices,
                new VendorServiceImpl(mock(VendorRepository.class)), mock(VendorHistoryService.class));
        DealServiceImpl dealServices2 = new DealServiceImpl(mock(DealRepository.class));

        // Act and Assert
        assertNotEquals(dealController, new DealController(dealServices2,
                new VendorServiceImpl(mock(VendorRepository.class)), mock(VendorHistoryService.class)));
    }

    /**
     * Test {@link DealController#equals(Object)}, and
     * {@link DealController#hashCode()}.
     * <ul>
     *   <li>When other is same.</li>
     *   <li>Then return equal.</li>
     * </ul>
     * <p>
     * Methods under test:
     * <ul>
     *   <li>{@link DealController#equals(Object)}
     *   <li>{@link DealController#hashCode()}
     * </ul>
     */
    @Test
    @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
    void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
        // Arrange
        DealServiceImpl dealServices = new DealServiceImpl(mock(DealRepository.class));
        DealController dealController = new DealController(dealServices,
                new VendorServiceImpl(mock(VendorRepository.class)), mock(VendorHistoryService.class));

        // Act and Assert
        assertEquals(dealController, dealController);
        int expectedHashCodeResult = dealController.hashCode();
        assertEquals(expectedHashCodeResult, dealController.hashCode());
    }

    /**
     * Test {@link DealController#getAllDeals(UserLookupInformation)}.
     * <p>
     * Method under test: {@link DealController#getAllDeals(UserLookupInformation)}
     */
    @Test
    @DisplayName("Test getAllDeals(UserLookupInformation)")
    void testGetAllDeals() throws Exception {
        // Arrange
        when(dealService.findByVendorId(Mockito.<String>any())).thenReturn(new ArrayList<>());

        CategoryStatus categoryStatus = new CategoryStatus();
        categoryStatus.setCode("Code");
        categoryStatus.setId("42");
        categoryStatus.setName("Name");

        Category category = new Category();
        category.setCategoryStatus(categoryStatus);
        category.setId("42");
        category.setName("Name");

        Vendor vendor = new Vendor();
        vendor.setAddress("42 Main St");
        vendor.setCategory(category);
        vendor.setDescription("The characteristics of someone or something");
        vendor.setEmail("jane.doe@example.org");
        vendor.setId("42");
        vendor.setLatitude(10.0d);
        vendor.setLogo("Logo");
        vendor.setLongitude(10.0d);
        vendor.setName("Name");
        vendor.setPhoneNumber("6625550144");
        vendor.setStartDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        vendor.setStatus("Status");
        vendor.setWebsite("Website");
        when(vendorService.findById(Mockito.<String>any())).thenReturn(vendor);

        CategoryStatus categoryStatus2 = new CategoryStatus();
        categoryStatus2.setCode("Code");
        categoryStatus2.setId("42");
        categoryStatus2.setName("Name");

        Category category2 = new Category();
        category2.setCategoryStatus(categoryStatus2);
        category2.setId("42");
        category2.setName("Name");

        Vendor vendor2 = new Vendor();
        vendor2.setAddress("42 Main St");
        vendor2.setCategory(category2);
        vendor2.setDescription("The characteristics of someone or something");
        vendor2.setEmail("jane.doe@example.org");
        vendor2.setId("42");
        vendor2.setLatitude(10.0d);
        vendor2.setLogo("Logo");
        vendor2.setLongitude(10.0d);
        vendor2.setName("Name");
        vendor2.setPhoneNumber("6625550144");
        vendor2.setStartDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        vendor2.setStatus("Status");
        vendor2.setWebsite("Website");

        VendorHistory vendorHistory = new VendorHistory();
        vendorHistory.setEmail("jane.doe@example.org");
        vendorHistory.setId("42");
        vendorHistory.setSearchDate(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        vendorHistory.setVendor(vendor2);
        when(vendorHistoryService.saveVendorHistory(Mockito.<VendorHistory>any())).thenReturn(vendorHistory);

        UserLookupInformation userLookupInformation = new UserLookupInformation();
        userLookupInformation.setLatitude(10.0d);
        userLookupInformation.setLongitude(10.0d);
        userLookupInformation.setUserEmail("jane.doe@example.org");
        userLookupInformation.setVendorId("42");
        String content = (new ObjectMapper()).writeValueAsString(userLookupInformation);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/deals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(dealController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Test {@link DealController#equals(Object)}.
     * <ul>
     *   <li>When other is {@code null}.</li>
     *   <li>Then return not equal.</li>
     * </ul>
     * <p>
     * Method under test: {@link DealController#equals(Object)}
     */
    @Test
    @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
    void testEquals_whenOtherIsNull_thenReturnNotEqual() {
        // Arrange
        DealServiceImpl dealServices = new DealServiceImpl(mock(DealRepository.class));

        // Act and Assert
        assertNotEquals(new DealController(dealServices, new VendorServiceImpl(mock(VendorRepository.class)),
                mock(VendorHistoryService.class)), null);
    }

    /**
     * Test {@link DealController#equals(Object)}.
     * <ul>
     *   <li>When other is wrong type.</li>
     *   <li>Then return not equal.</li>
     * </ul>
     * <p>
     * Method under test: {@link DealController#equals(Object)}
     */
    @Test
    @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
    void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
        // Arrange
        DealServiceImpl dealServices = new DealServiceImpl(mock(DealRepository.class));

        // Act and Assert
        assertNotEquals(new DealController(dealServices, new VendorServiceImpl(mock(VendorRepository.class)),
                mock(VendorHistoryService.class)), "Different type to DealController");
    }
}
