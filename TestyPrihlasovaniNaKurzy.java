package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestyPrihlasovaniNaKurzy {

    WebDriver prohlizec;

    @BeforeEach
    public void setUp() {
//      System.setProperty("webdriver.gecko.driver", System.getProperty("user.home") + "/Java-Training/Selenium/geckodriver");
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    String email = "hokus@seznam.cz";
    String heslo = "Pokuston2";
    String noveHeslo = "Pokuston2";
    String jmenoZaka = "Hokus";
    String prijmeniZaka = "Pokuston";
    String datumNarozeniZaka = "08.07.2014";



    @Test
    public void rodicSExistujicimUctemSeMusiPrihlasit() {
        prohlizec.navigate().to("http://cz-test-jedna.herokuapp.com/");
        najitPrihlasovaciButton();
        zadatEmail(email);
        zadatHeslo(heslo);
        odeslatPrihlasovaciUdaje();

        Assertions.assertEquals("http://cz-test-jedna.herokuapp.com/zaci", prohlizec.getCurrentUrl(), "Prihlaseni se nezdarilo");

    }

    @Test
    public void rodicSiVybereKurzPrihlasiSeAPrihlasiNaKurzDite(){
        JavascriptExecutor js = (JavascriptExecutor) prohlizec;

        prohlizec.navigate().to("http://cz-test-jedna.herokuapp.com/");
        WebElement trimesicniKurzWebu = prohlizec.findElement(By.xpath("//a[contains(@href, 'webu')]"));
        trimesicniKurzWebu.click();

        WebElement vytvoritPrihlasku = prohlizec.findElement(By.xpath(
                "//div[contains(@class, 'center')]//a[text() = 'Vytvořit přihlášku']"));

        vytvoritPrihlasku.click();

        zadatEmail(email);
        zadatHeslo(heslo);
        odeslatPrihlasovaciUdaje();


        WebElement terminy = prohlizec.findElement(By.xpath("//*[text() ='Vyberte termín...']"));
        terminy.click();

        WebElement konkretniTermin = prohlizec.findElement(By.id("bs-select-1-0"));
        konkretniTermin.click();

        WebElement vyplnJmeno = prohlizec.findElement(By.id("forename"));
        vyplnJmeno.sendKeys(jmenoZaka);

        WebElement vyplnPrijmeni = prohlizec.findElement(By.id("surname"));
        vyplnPrijmeni.sendKeys(prijmeniZaka);

        WebElement datumNarozeni = prohlizec.findElement(By.id("birthday"));
        datumNarozeni.sendKeys(datumNarozeniZaka);
        prohlizec.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        WebElement platbaHotove = prohlizec.findElement(By.id("payment_cash"));
        js.executeScript("arguments[0].click();", platbaHotove);
        prohlizec.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement souhlasimSeZpracovanim = prohlizec.findElement(By.id("terms_conditions"));
        js.executeScript("arguments[0].click();", souhlasimSeZpracovanim);

        WebElement odeslatPrihlasku = prohlizec.findElement(By.xpath("//input[@value = 'Vytvořit přihlášku']"));
        odeslatPrihlasku.click();

        WebElement prihlasky = prohlizec.findElement(By.xpath("//a[text() = 'Přihlášky']"));
        WebDriverWait cekani = new WebDriverWait(prohlizec, 10);
        prihlasky.click();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        cekani.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text() = 'Upravit']")));
        odhlasitStudenta();

    }

    @Test
    public void rodicSePrihlasiAPrihlasiDiteNaKurz(){
        JavascriptExecutor js = (JavascriptExecutor) prohlizec;

        prohlizec.navigate().to("http://cz-test-jedna.herokuapp.com/");
        najitPrihlasovaciButton();
        zadatEmail(email);
        zadatHeslo(heslo);
        odeslatPrihlasovaciUdaje();
        WebElement vytvoritPrihlaskuZProfilu = prohlizec.findElement(By.xpath("//a[@class = 'btn btn-sm btn-info']"));
        vytvoritPrihlaskuZProfilu.click();

        WebElement trimesicniKurzWebu = prohlizec.findElement(By.xpath("//a[contains(@href, 'webu')]"));
        trimesicniKurzWebu.click();
        WebElement vytvoritPrihlasku = prohlizec.findElement(By.xpath(
                "//a[@href = 'http://cz-test-jedna.herokuapp.com/zaci/pridat/41-html-1']"));
        vytvoritPrihlasku.click();

        WebElement terminy = prohlizec.findElement(By.xpath("//*[text()='Vyberte termín...']"));
        terminy.click();

        WebElement konkretniTermin = prohlizec.findElement(By.id("bs-select-1-0"));
        konkretniTermin.click();

        WebElement vyplnJmeno = prohlizec.findElement(By.id("forename"));
        vyplnJmeno.sendKeys(jmenoZaka);

        WebElement vyplnPrijmeni = prohlizec.findElement(By.id("surname"));
        vyplnPrijmeni.sendKeys(prijmeniZaka);

        WebElement datumNarozeni = prohlizec.findElement(By.id("birthday"));
        datumNarozeni.sendKeys(datumNarozeniZaka);
        prohlizec.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        WebElement platbaHotove = prohlizec.findElement(By.id("payment_cash"));
        js.executeScript("arguments[0].click();", platbaHotove);
        prohlizec.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement souhlasimSeZpracovanim = prohlizec.findElement(By.id("terms_conditions"));
        js.executeScript("arguments[0].click();", souhlasimSeZpracovanim);

        WebElement odeslatPrihlasku = prohlizec.findElement(By.xpath("//input[@value = 'Vytvořit přihlášku']"));
        odeslatPrihlasku.click();

        WebElement prihlasky = prohlizec.findElement(By.xpath("//a[text() = 'Přihlášky']"));
        WebDriverWait cekani = new WebDriverWait(prohlizec, 10);
        prihlasky.click();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        cekani.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text() = 'Upravit']")));
        odhlasitStudenta();

    }

    @Test
    public void funkceZmenaHeslaJeFunkcni(){
        // zkouším pouze, zda funguje zadávání hesla a změna hesla na stejné,
        // poněvadž jsem nepřišla na to, jak pak fungovat se zadaným heslem před testy
        JavascriptExecutor js = (JavascriptExecutor) prohlizec;

        prohlizec.navigate().to("http://cz-test-jedna.herokuapp.com/");

        najitPrihlasovaciButton();
        zadatEmail(email);
        zadatHeslo(heslo);
        odeslatPrihlasovaciUdaje();

        WebElement najitOdhlasovaciButton =
                prohlizec.findElement(By.xpath("//a[@href = '#']"));  ///html/body/div/header/nav/div/div[2]/div/a/strong
        najitOdhlasovaciButton.click();
        prohlizec.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement tlacitkoProfil = prohlizec.findElement(By.xpath("//*[contains(@href, 'profil')]"));
        tlacitkoProfil.click();

        zadatHeslo(noveHeslo);
        WebElement zadaniHeslaKeZmeneni = prohlizec.findElement(By.id("password-confirm"));
        zadaniHeslaKeZmeneni.sendKeys(noveHeslo);
        WebElement buttonZmenyHesla = prohlizec.findElement(By.xpath("//button[@class = 'btn btn-primary']"));
        buttonZmenyHesla.click();

        WebDriverWait cekaniTest4 = new WebDriverWait(prohlizec, 10);
        cekaniTest4.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class = 'toast-message']")));

    }


    @AfterEach
    public void tearDown() {
        JavascriptExecutor js = (JavascriptExecutor) prohlizec;

        js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");

        odhlasitSe();
    }

    private void odhlasitSe() {
        prohlizec.navigate().refresh();
        WebElement najitOdhlasovaciButton =
                prohlizec.findElement(By.xpath("//a[@href = '#']"));  ///html/body/div/header/nav/div/div[2]/div/a/strong
        najitOdhlasovaciButton.click();
        prohlizec.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement klikNaOdhlaseni = prohlizec.findElement(By.id("logout-link"));
        klikNaOdhlaseni.click();
        prohlizec.quit();
    }

    public void najitPrihlasovaciButton(){
        WebElement prihlasitZHlavniStranky = prohlizec.findElement(By.xpath("//a[contains(@href, 'prihlaseni')]"));
        prihlasitZHlavniStranky.click();
    }

    public void zadatEmail (String email){
        WebElement zadaniEmailu = prohlizec.findElement(By.id("email"));
        zadaniEmailu.sendKeys(email);
    }

    public void zadatHeslo (String heslo){
        WebElement zadaniHesla = prohlizec.findElement(By.id("password"));
        zadaniHesla.sendKeys(heslo);
    }
    public void odeslatPrihlasovaciUdaje(){
    WebElement prihlasitSUdaji = prohlizec.findElement(By.xpath("//*[@class = 'btn btn-primary']"));
        prihlasitSUdaji.click();
    }
    public void odhlasitStudenta(){
        JavascriptExecutor js = (JavascriptExecutor) prohlizec;
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        WebElement odhlasitUcast = prohlizec.findElement(By.xpath("//a[contains(@class,'danger')]"));
        odhlasitUcast.click();
        WebElement odhlasitJinyDuvod = prohlizec.findElement(By.xpath("//*[@id=\"logged_out_other\"]"));
        js.executeScript("arguments[0].click();", odhlasitJinyDuvod);
        WebElement odhlasitFinalniSubmit = prohlizec.findElement(By.xpath("//*[@class = 'btn btn-primary']"));
        odhlasitFinalniSubmit.click();
        prohlizec.navigate().refresh();
    }
}
