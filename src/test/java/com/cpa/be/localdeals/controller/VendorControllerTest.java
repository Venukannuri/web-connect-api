package com.optimus.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

import com.optimus.dao.DealRepository;
import com.optimus.dao.VendorRepository;
import com.optimus.service.DealServiceImpl;
import com.optimus.service.VendorServiceImpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VendorControllerTest {
    /**
     * Test {@link VendorController#equals(Object)}.
     * <ul>
     *   <li>When other is different.</li>
     *   <li>Then return not equal.</li>
     * </ul>
     * <p>
     * Method under test: {@link VendorController#equals(Object)}
     */
    @Test
    @DisplayName("Test equals(Object); when other is different; then return not equal")
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
        // Arrange
        VendorController vendorController = new VendorController(null, new DealServiceImpl(mock(DealRepository.class)));

        // Act and Assert
        assertNotEquals(vendorController, new VendorController(null, new DealServiceImpl(mock(DealRepository.class))));
    }

    /**
     * Test {@link VendorController#equals(Object)}.
     * <ul>
     *   <li>When other is different.</li>
     *   <li>Then return not equal.</li>
     * </ul>
     * <p>
     * Method under test: {@link VendorController#equals(Object)}
     */
    @Test
    @DisplayName("Test equals(Object); when other is different; then return not equal")
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
        // Arrange
        VendorServiceImpl vendorService = new VendorServiceImpl(mock(VendorRepository.class));
        VendorController vendorController = new VendorController(vendorService,
                new DealServiceImpl(mock(DealRepository.class)));
        VendorServiceImpl vendorService2 = new VendorServiceImpl(mock(VendorRepository.class));

        // Act and Assert
        assertNotEquals(vendorController,
                new VendorController(vendorService2, new DealServiceImpl(mock(DealRepository.class))));
    }

    /**
     * Test {@link VendorController#equals(Object)}, and
     * {@link VendorController#hashCode()}.
     * <ul>
     *   <li>When other is same.</li>
     *   <li>Then return equal.</li>
     * </ul>
     * <p>
     * Methods under test:
     * <ul>
     *   <li>{@link VendorController#equals(Object)}
     *   <li>{@link VendorController#hashCode()}
     * </ul>
     */
    @Test
    @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
    void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
        // Arrange
        VendorServiceImpl vendorService = new VendorServiceImpl(mock(VendorRepository.class));
        VendorController vendorController = new VendorController(vendorService,
                new DealServiceImpl(mock(DealRepository.class)));

        // Act and Assert
        assertEquals(vendorController, vendorController);
        int expectedHashCodeResult = vendorController.hashCode();
        assertEquals(expectedHashCodeResult, vendorController.hashCode());
    }

    /**
     * Test {@link VendorController#equals(Object)}.
     * <ul>
     *   <li>When other is {@code null}.</li>
     *   <li>Then return not equal.</li>
     * </ul>
     * <p>
     * Method under test: {@link VendorController#equals(Object)}
     */
    @Test
    @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
    void testEquals_whenOtherIsNull_thenReturnNotEqual() {
        // Arrange
        VendorServiceImpl vendorService = new VendorServiceImpl(mock(VendorRepository.class));

        // Act and Assert
        assertNotEquals(new VendorController(vendorService, new DealServiceImpl(mock(DealRepository.class))), null);
    }

    /**
     * Test {@link VendorController#equals(Object)}.
     * <ul>
     *   <li>When other is wrong type.</li>
     *   <li>Then return not equal.</li>
     * </ul>
     * <p>
     * Method under test: {@link VendorController#equals(Object)}
     */
    @Test
    @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
    void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
        // Arrange
        VendorServiceImpl vendorService = new VendorServiceImpl(mock(VendorRepository.class));

        // Act and Assert
        assertNotEquals(new VendorController(vendorService, new DealServiceImpl(mock(DealRepository.class))),
                "Different type to VendorController");
    }
}
