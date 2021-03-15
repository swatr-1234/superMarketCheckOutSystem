
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SuperMarketSystemTest {
    SuperMarketSystem superMarketSystem;

    @BeforeAll
    static void setUp() {
        System.out.println("This is setup method");
    }

    @BeforeEach
    void init(TestInfo testInfo) {
        superMarketSystem = new SuperMarketSystem();
        System.out.println("Start..." + testInfo.getDisplayName());
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        System.out.println("End..." + testInfo.getDisplayName());
        superMarketSystem=null;
        System.gc();
    }

    @Test
    @Order(1)
    @DisplayName("Testing item selection method")
    public void selectItems() {
        int selectionCount = 0;
        Assumptions.assumeTrue(selectionCount >= 0);

    }

    @ParameterizedTest
    @ValueSource(strings = {"null", "", "asdf", "@#$%$#%", "QWERR", "1267"})
    @Order(2)
    @DisplayName("Testing validateSelectedItems method for invalid items")
    public void validateSelectedItemsForInvalidItems(String selectedItems) {
        boolean expectedValidateFlag = false;
        boolean actualValidateFlag = superMarketSystem.validateSelectedItems(selectedItems);
        assertEquals(expectedValidateFlag, actualValidateFlag);

    }

    @ParameterizedTest
    @ValueSource(strings = {"AAAD", "ABCD", "AACC", "ADBC"})
    @DisplayName("Testing validateSelectedItems method for valid items")
    @Order(3)
    public void validateSelectedItemsForValidItems(String selectedItems) {
        boolean expectedValidateFlag = true;
        boolean actualValidateFlag = superMarketSystem.validateSelectedItems(selectedItems);
        assertEquals(expectedValidateFlag, actualValidateFlag);

    }

    @ParameterizedTest
    @ValueSource(strings = {"AAAD", "ABCD", "AACC", "ADBC"})
    @DisplayName("Testing countingOfItems method for valid items")
    @Order(4)
    public void countingOfItems(String selectedItems) {

        Map<Character, Integer> itemMap = superMarketSystem.countingOfItems(selectedItems);
        assertTrue(itemMap instanceof Map);

    }

    @Test
    @DisplayName("Testing calculatingPricingAndDiscountOfferOnAllItems for valid items")
    @Order(5)
    public void calculatingPricingAndDiscountOfferOnAllItems() {
        Map<Character, Integer> itemMap = new HashMap<>();
        String selectedItems = "ABCD";
        int totalPriceOfItems = 110;
        itemMap = superMarketSystem.countingOfItems(selectedItems);
        assertEquals(totalPriceOfItems, superMarketSystem.calculatingPricingAndDiscountOfferOnAllItems(itemMap), "Item total values are not matching");

    }

    @ParameterizedTest
    @DisplayName("Testing calculatingPricingAndDiscountOfferOnItemA for valid item A")
    @Order(6)
    @MethodSource("calculatingPricingAndDiscountOfferOnItemA")
    public void calculatingPricingAndDiscountOfferOnItemsA(char item, int countOfItem, int offerAmountOnItem, int divisor, int fixedPriceOnItem) {

        int totalPriceOfItemA = 130;
        assertEquals(totalPriceOfItemA, superMarketSystem.calculatingPricingAndDiscountOfferOnItemA_B(item, countOfItem, offerAmountOnItem, divisor, fixedPriceOnItem), "Item A total value is not matching.");

    }

    private static Stream<Arguments> calculatingPricingAndDiscountOfferOnItemA() {
        return Stream.of(
                arguments(SuperMarketConstant.ITEM_A, 3, SuperMarketConstant.OFFER_AMOUNT_ON_ITEM_A, SuperMarketConstant.DIVISOR_OF_A, SuperMarketConstant.FIXED_PRICE_ON_ITEM_A)
        );
    }

    @ParameterizedTest
    @DisplayName("Testing calculatingPricingAndDiscountOfferOnItemB for valid item B")
    @Order(7)
    @MethodSource("calculatingPricingAndDiscountOfferOnItemB")
    public void calculatingPricingAndDiscountOfferOnItemsB(char item, int countOfItem, int offerAmountOnItem, int divisor, int fixedPriceOnItem) {

        int totalPriceOfItemB = 37;
        assertEquals(totalPriceOfItemB, superMarketSystem.calculatingPricingAndDiscountOfferOnItemA_B(item, countOfItem, offerAmountOnItem, divisor, fixedPriceOnItem), "Item B total value is not matching.");

    }

    private static Stream<Arguments> calculatingPricingAndDiscountOfferOnItemB() {
        return Stream.of(
                arguments(SuperMarketConstant.ITEM_B, 2, SuperMarketConstant.OFFER_AMOUNT_ON_ITEM_B, SuperMarketConstant.DIVISOR_OF_B, SuperMarketConstant.FIXED_PRICE_ON_ITEM_B)
        );
    }
}
