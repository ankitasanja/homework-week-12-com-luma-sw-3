package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class GearTest extends Utility {

    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldAddProductSuccessFullyToShoppingCart() {
        // Mouse Hover on Gear Menu
        mouseHoverOnElement(By.xpath("//a[@id='ui-id-6']"));

        //Click on Bags
        clickOnMouseHoverOnElement(By.xpath("//span[contains(text(),'Bags')]"));

        //Click on Product Name ‘Overnight Duffle’
        clickOnElement(By.xpath("//a[contains(text(),'Overnight Duffle')]"));

        //Verify the text ‘Overnight Duffle’
        assertVerifyText(By.xpath("//span[contains(text(),'Overnight Duffle')]"), "Overnight Duffle");

        //Change Qty 3
        WebElement element = driver.findElement(By.xpath("//input[@id='qty']"));
        element.clear();
        sendTextToElement(By.xpath("//input[@id='qty']"), "3");

        //Click on ‘Add to Cart’ Button.
        clickOnElement(By.xpath("//span[contains(text(),'Add to Cart')]"));

        //Verify the text ‘You added Overnight Duffle to your shopping cart.’
        assertVerifyText(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']"), "You added Overnight Duffle to your shopping cart.");

        //Click on ‘shopping cart’ Link into message
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));

        //Verify the product name ‘Overnight Duffle’
        assertVerifyText(By.xpath("//td[@class='col item']//a[normalize-space()='Overnight Duffle']"), "Overnight Duffle");

        //Verify the Qty is ‘3’
        WebElement element2 = driver.findElement(By.cssSelector(".input-text.qty"));
        String expectedQuantity = "3";
        String actQuantity = element2.getAttribute("value");
        Assert.assertEquals("Not matched", expectedQuantity, actQuantity);

        //Verify the product price ‘$135.00’
        assertVerifyText(By.xpath("//span[@class='cart-price']//span[@class='price'][normalize-space()='$135.00']"), "$135.00");

        //Change Qty to ‘5’
        WebElement element1 = driver.findElement(By.cssSelector(".input-text.qty"));
        element1.clear();
        sendTextToElement(By.cssSelector(".input-text.qty"), "5");

        //Click on ‘Update Shopping Cart’ button
        clickOnElement(By.xpath("//span[normalize-space()='Update Shopping Cart']"));

        // Verify the product price ‘$225.00’
        assertVerifyText(By.xpath("//span[contains(text(),'$225.00')]"), "$225.00");
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
