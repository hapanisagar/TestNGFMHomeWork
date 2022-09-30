import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class FirstTestNGScript {
    WebDriver driver;


    @Test(priority = 1)
    void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
    }

    @Test(priority = 2)
    void selectComputer() {
        driver.findElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[1]/a")).click();
        driver.findElement(By.xpath("//img[@title='Show products in category Desktops']")).click();
        driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[3]/div/div[2]/h2/a")).click();
    }

    @Test(priority = 3)
    void addT0Cart() {
        driver.findElement(By.id("add-to-cart-button-3")).click();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart-label")));
        driver.findElement(By.className("cart-label")).click();
    }

    @Test(priority = 4)
    void updateQty() {
        driver.findElement(By.className("qty-input")).clear();
        driver.findElement(By.className("qty-input")).sendKeys("3");
        driver.findElement(By.id("updatecart")).click();
    }

    @Test(priority = 5)
    void checkout() {
        driver.findElement(By.id("termsofservice")).click();
        driver.findElement(By.name("checkout")).click();
        driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]/div[1]/div[3]/button[1]")).click();
    }

    @Test(priority = 6)
    void fillBillingInf() {
        driver.findElement(By.id("BillingNewAddress_FirstName")).sendKeys("Sachin");
        driver.findElement(By.id("BillingNewAddress_LastName")).sendKeys("Nayak");
        driver.findElement(By.name("BillingNewAddress.Email")).sendKeys("Unifytest@gmail.com");

        WebElement Selectcountry = driver.findElement(By.id("BillingNewAddress_CountryId"));
        Select select = new Select(Selectcountry);
        select.selectByValue("233");

        driver.findElement(By.id("BillingNewAddress_City")).sendKeys("London");
        driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("10,North road");
        driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("ab34cd");
        driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("01234567890");
        driver.findElement(By.xpath("//*[@id=\"billing-buttons-container\"]/button[4]")).click();

    }

    @Test(priority = 7)
    void shippingMethod() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shippingoption_1")));
        driver.findElement(By.id("shippingoption_1")).click();
        driver.findElement(By.cssSelector("button[onclick=\"ShippingMethod.save()\"]")).click(); //custom css

    }

    @Test(priority = 8)
    void paymentMethod() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("paymentmethod_1"))).click();

        driver.findElement(By.xpath("//button[@onclick='PaymentMethod.save()']")).click(); //custom X path
    }

    @Test(priority = 9)
    void paymentInfo() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dropdownlists")));

        WebElement SelectCardType = driver.findElement(By.xpath("//*[@id=\"CreditCardType\"]"));
        Select select = new Select(SelectCardType);
        select.selectByValue("MasterCard");
        // driver.findElement(By.className("dropdownlists")).sendKeys("Amex"); //it is another approch
        driver.findElement(By.name("CardholderName")).sendKeys("sachin Nayak");
        driver.findElement(By.id("CardNumber")).sendKeys("1234567890123456");
        driver.findElement(By.id("ExpireMonth")).sendKeys("05");
        driver.findElement(By.id("ExpireYear")).sendKeys("2027");
        driver.findElement(By.name("CardCode")).sendKeys("123");
        driver.findElement(By.cssSelector("button[onclick=\"PaymentInfo.save()\"]")).click();

    }
    @Test(priority = 10)
    void CloseBrowser() {
        driver.close();

    }
}