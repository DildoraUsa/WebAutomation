package projectWebAutomation;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class checkSearchButtom extends TestBaseMethods {

    @Test(groups = {"smoke"})
    public void verifyTitleOfPage(){

        driver.get("https://www.webstaurantstore.com/");
       Assert.assertEquals(driver.getTitle(),"WebstaurantStore: Restaurant Supplies & Foodservice Equipment");

    }
   @Test
    public void searchAndSort() throws IOException{
      driver.get("https://www.webstaurantstore.com/");
      driver.findElement(By.xpath("//input[@id='searchval']")).click();
       driver.findElement(By.xpath("//input[@id='searchval']")).sendKeys("kitchen ovens");
      driver.findElement(By.xpath("//button[@value='Search']")).sendKeys(Keys.ENTER);
       Select selectSort = new Select(driver.findElement(By.xpath("//select[@id='sort_options']")));
      selectSort.selectByIndex(2);
       File screenShort = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       FileUtils.copyFile(screenShort,new File("screenshot"+System.currentTimeMillis()+".png"));
       List<WebElement> priceTotals =driver.findElements(By.xpath("//p[@data-testid='price']"));
       List<Double> priceSort = new ArrayList<>();
       int num=0;
       for (WebElement eachPrice: priceTotals){
           priceSort.add(Double.valueOf(eachPrice.getText().replaceAll("[eachEACHFROM/$SsPpktTt ]","")));
           Collections.sort(priceSort);
           Assert.assertEquals(Double.valueOf(eachPrice.getText().replaceAll("[eachEACHFROM/$SsPpktTt ]","")),priceSort.get(num));
           File screenShort1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
           FileUtils.copyFile(screenShort1,new File("screenshotPrice"+System.currentTimeMillis()+".png"));
           num+=1;
       }
    }
    @Test(groups = {"regression"})
    public void sortByCapacity() throws IOException {
        driver.get("https://www.webstaurantstore.com/");
        driver.findElement(By.xpath("//input[@id='searchval']")).click();
        driver.findElement(By.xpath("//input[@id='searchval']")).sendKeys("Food storage containers");
        driver.findElement(By.xpath("//button[@value='Search']")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//a[@href='/3087/food-storage-containers.html']")).click();
        driver.findElement(By.xpath("(//a[@class='btn btn-small btn-light'])[2]")).click();
        File screenShort = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShort,new File("screenshotCapacity"+System.currentTimeMillis()+".png"));
    }

    @Test(groups = {"regression"})
    public void sortAndFilter() throws InterruptedException {
        driver.get("https://www.webstaurantstore.com/");
        driver.findElement(By.xpath("//input[@id='searchval']")).click();
        driver.findElement(By.xpath("//input[@id='searchval']")).sendKeys("Frying Pans");
        driver.findElement(By.xpath("//button[@value='Search']")).sendKeys(Keys.ENTER);
        Select selectSort = new Select(driver.findElement(By.xpath("//select[@id='sort_options']")));
        selectSort.selectByIndex(5);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//label[@class='text-shadow-white block font-normal text-sm-1/2 leading-4 mr-0 mb-0 -ml-7 -mt-2-1/2 p-0 absolute top-1/2 left-full text-gray-800 w-4-1/2 xs:-mt-2 xs:-ml-6 xs:pl-0 xs:left-auto xs:w-3-1/4'])[6]")).click();
       List<WebElement> allItemsBrandBon = driver.findElements(By.xpath("//a[@data-testid='itemDescription']"));
       for (WebElement eachItemBrand: allItemsBrandBon){
           //System.out.println(eachItemBrand.getText());
           Assert.assertTrue(eachItemBrand.getText().contains("Bon Chef "));
       }
    }

    @Test(groups = {"regression"})
    public void sortByShape() throws IOException {
        driver.get("https://www.webstaurantstore.com/");
        driver.findElement(By.xpath("//input[@id='searchval']")).click();
        driver.findElement(By.xpath("//input[@id='searchval']")).sendKeys("China Dinnerware");
        driver.findElement(By.xpath("//button[@value='Search']")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//p[@data-target='#type--Brand']")).click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,700)");
        driver.findElement(By.xpath("//div[@data-filter-id='2369']")).click();
        driver.findElement(By.xpath("//a[.='Corona by GET Enterprises Artisan Porcelain Dinnerware']")).click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
        driver.findElement(By.xpath("(//div[@class='list-check rounded-full border-solid border cursor-pointer float-left m-0 overflow-hidden pt-full relative shadow-none w-full transition-all ease-in-check duration-1100 xs:rounded bg-white border-gray-350'])[15]")).click();
        Select selectSort = new Select(driver.findElement(By.xpath("//select[@id='sort_options']")));
        selectSort.selectByIndex(2);
        File screenShort = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShort,new File("screenshotShape"+System.currentTimeMillis()+".png"));
        List<WebElement> elementsByShape = driver.findElements(By.xpath("//a[@data-testid='itemDescription']"));
        for (WebElement eachByShape: elementsByShape){
            Assert.assertTrue(eachByShape.getText().contains("Oval"));
        }
    }

    @Test(groups = {"regression","flaky"})
    public void findAndAddNewest() throws InterruptedException {
        driver.get("https://www.webstaurantstore.com/");
        driver.findElement(By.xpath("//a[@data-type='Smallwares']")).click();
       driver.findElement(By.xpath("//h2[.='Cookware']")).click();
       driver.findElement(By.xpath("//h2[.='Stock Pots & Accessories']")).click();
        Select selectSort = new Select(driver.findElement(By.xpath("//select[@id='sort_options']")));
        selectSort.selectByIndex(5);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,700)");
        driver.findElement(By.xpath("(//input[@data-testid='itemAddCartQty'])[1]")).clear();
        driver.findElement(By.xpath("(//input[@data-testid='itemAddCartQty'])[1]")).sendKeys("100");
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//input[@data-testid='itemAddCart'])[1]")).click();
        String cartNumber = driver.findElement(By.id("cartItemCountSpan")).getText();
        Assert.assertEquals(cartNumber,"100");
    }

    @Test(groups = {"regression"})
    public void findAndAddLowestPrice(){
        driver.get("https://www.webstaurantstore.com/");
        driver.findElement(By.xpath("//a[@data-type='Smallwares']")).click();
        driver.findElement(By.xpath("//h2[.='Baking Supplies']")).click();
        driver.findElement(By.xpath("//h2[.='Decorating Tools']")).click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
        driver.findElement(By.xpath("//span[.='Cake Turntables']")).click();
        Select selectSort = new Select(driver.findElement(By.xpath("//select[@id='sort_options']")));
        selectSort.selectByIndex(1);
        driver.findElement(By.xpath("(//input[@data-testid='itemAddCartQty'])[1]")).clear();
        driver.findElement(By.xpath("(//input[@data-testid='itemAddCartQty'])[1]")).sendKeys("1000");
        driver.findElement(By.xpath("(//input[@data-testid='itemAddCart'])[1]")).click();
        String cartNumber = driver.findElement(By.id("cartItemCountSpan")).getText();
        Assert.assertEquals(cartNumber,"1000");
    }

    @Test(groups = {"smoke"})
    public void chatBoxCheck(){
        driver.get("https://www.webstaurantstore.com/");
        Assert.assertTrue(driver.findElement(By.id("chat-button")).getText().contains("Online"));
    }

    @Test(groups = {"smoke"})
    public void writeOnChatBox() throws IOException, InterruptedException {
        driver.get("https://www.webstaurantstore.com/");
        driver.findElement(By.id("chat-button")).click();
        String nameChat = "WebstaurantStore Online Chat";
        Set<String> windowHandles = driver.getWindowHandles();
        for(String windowHandle:windowHandles){
            driver.switchTo().window(windowHandle);
            if (driver.getTitle().equals(nameChat)){
                break;
            }
        }
        driver.findElement(By.xpath("//input[@ng-keyup='submitChat($event)']")).sendKeys("Jonny");
        driver.findElement(By.xpath("//button[@ng-click='continueToCase()']")).click();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("dfdfww@gmail.com");
        Select selectSort = new Select(driver.findElement(By.xpath("//select[@ng-model='selectedCaseOption']")));
        selectSort.selectByVisibleText("Account Related");
        driver.findElement(By.xpath("//button[@ng-click='startChat()']")).click();
        System.out.println(driver.getTitle());
       Assert.assertEquals(driver.getTitle(),"WebstaurantStore Online Chat");
       Thread.sleep(2000);
        File screenShort = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShort,new File("screenshotChat"+System.currentTimeMillis()+".png"));

    }

}
