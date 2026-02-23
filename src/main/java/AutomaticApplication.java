import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

static ApplicationData basvuru = null;
static String websiteAddress = "https://t3kys.com/tr/applications/program/11456/apply-auth/";
static WebDriver driver;
static WebDriverWait wait;

void main() {
    Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
    Logger.getLogger("io.github.bonigarcia").setLevel(Level.OFF);
    MyLogger.baslik(" DENEYAP BAŞVURU OTOMASYONU");
    readJson();
    editJson();
    startBrowser();
    try {
        MyLogger.islemBasla("Web sitesine bağlanılıyor");
        driver.get(websiteAddress);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_first_name")));
        MyLogger.islemTamam();

        websiteAutocomplete();
        writeToExcel();
    } catch (Exception e) {
        MyLogger.error("Form doldurma sırasında hata oluştu: " + e.getMessage());
    }

    MyLogger.closingText();

    try (Scanner scanner = new Scanner(System.in)) {
        scanner.nextLine();
    } finally {
        try {
            if (driver != null) driver.quit();
        } catch (Exception ignored) {
        }
    }
    MyLogger.baslik("PROGRAM BAŞARIYLA TAMAMLANDI");
}

public static void readJson() {
    MyLogger.islemBasla("JSON dosyası okunuyor");
    try {
        String jsonMetni = Files.readString(Paths.get("basvuru.json"));
        Gson gson = new Gson();
        basvuru = gson.fromJson(jsonMetni, ApplicationData.class);
    } catch (Exception e) {
        MyLogger.islemBasarisiz(e.getMessage());
    }
    MyLogger.islemTamam();
}

public static void editJson() {
    MyLogger.islemBasla("Veriler düzenleniyor");
    basvuru.adres = basvuru.il + " " + basvuru.ilce;
    if (basvuru.eposta.isEmpty()) basvuru.eposta = basvuru.veliEposta;
    if (basvuru.veliEposta.isEmpty()) basvuru.veliEposta = basvuru.eposta;
    if (basvuru.telefon.isEmpty()) basvuru.telefon = basvuru.veliTelefon;
    if (basvuru.veliTelefon.isEmpty()) basvuru.veliTelefon = basvuru.telefon;
    MyLogger.islemTamam();
}

public static void startBrowser() {
    MyLogger.islemBasla("Tarayıcı başlatılıyor");
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        try {
            if (driver != null) driver.quit();
        } catch (Exception ignored) {
        }//görmezden gelmek için değişken adını ignored yap
        try {
            String[] cmd = {
                    "cmd.exe", "/c",
                    "ping 127.0.0.1 -n 3 > nul && for /d %i in (\"%TEMP%\\e4j*\") do rmdir /s /q \"%i\""
            };
            Runtime.getRuntime().exec(cmd);
        } catch (Exception ignored) {
        }
    }));
    driver.manage().window().maximize();
    MyLogger.islemTamam();
}

public static void websiteAutocomplete() {
    MyLogger.altBaslik("FORM DOLDURULUYOR");
    long baslangic = System.nanoTime();
    int toplamIslem = 24;
    int mevcutIslem = 0;

    // --- METİN KUTULARI ---
    driver.findElement(By.id("id_first_name")).sendKeys(basvuru.ad);
    MyLogger.progress(++mevcutIslem, toplamIslem, "Ad girildi");

    driver.findElement(By.id("id_last_name")).sendKeys(basvuru.soyad);
    MyLogger.progress(++mevcutIslem, toplamIslem, "Soyad girildi");

    driver.findElement(By.id("id_email")).sendKeys(basvuru.eposta);
    MyLogger.progress(++mevcutIslem, toplamIslem, "E-posta girildi");

    driver.findElement(By.id("id_phone_number")).sendKeys(basvuru.telefon);
    MyLogger.progress(++mevcutIslem, toplamIslem, "Telefon girildi");

    driver.findElement(By.id("id_tckn")).sendKeys(basvuru.tckn);
    MyLogger.progress(++mevcutIslem, toplamIslem, "TCKN girildi");

    driver.findElement(By.id("id_dob")).sendKeys(basvuru.dogumTarihi);
    MyLogger.progress(++mevcutIslem, toplamIslem, "Doğum tarihi girildi");

    driver.findElement(By.id("id_parent_name")).sendKeys(basvuru.veliAdi);
    MyLogger.progress(++mevcutIslem, toplamIslem, "Veli adı girildi");

    driver.findElement(By.id("id_parent_surname")).sendKeys(basvuru.veliSoyadi);
    MyLogger.progress(++mevcutIslem, toplamIslem, "Veli soyadı girildi");

    driver.findElement(By.id("id_parent_email")).sendKeys(basvuru.veliEposta);
    MyLogger.progress(++mevcutIslem, toplamIslem, "Veli e-posta girildi");

    driver.findElement(By.id("id_parent_phone")).sendKeys(basvuru.veliTelefon);
    MyLogger.progress(++mevcutIslem, toplamIslem, "Veli telefon girildi");

    driver.findElement(By.id("id_address")).sendKeys(basvuru.adres);
    MyLogger.progress(++mevcutIslem, toplamIslem, "Adres girildi");

    driver.findElement(By.id("id_password1")).sendKeys(basvuru.parola);
    MyLogger.progress(++mevcutIslem, toplamIslem, "Parola 1 girildi");

    driver.findElement(By.id("id_password2")).sendKeys(basvuru.parola);
    MyLogger.progress(++mevcutIslem, toplamIslem, "Parola 2 girildi");


    // --- AÇILIR MENÜLER ---
    selectAndFill("select2-id_gender-container", basvuru.cinsiyet);
    MyLogger.progress(++mevcutIslem, toplamIslem, "Cinsiyet seçildi");

    selectAndFill("select2-id_country-container", basvuru.ulke);
    MyLogger.progress(++mevcutIslem, toplamIslem, "Ülke seçildi");

    selectAndFill("select2-id_province-container", basvuru.il);
    MyLogger.progress(++mevcutIslem, toplamIslem, "İl seçildi");

    selectAndFill("select2-id_district-container", basvuru.ilce);
    MyLogger.progress(++mevcutIslem, toplamIslem, "İlçe seçildi");

    selectAndFill("select2-id_education_level-container", basvuru.egitimSeviyesi);
    MyLogger.progress(++mevcutIslem, toplamIslem, "Eğitim seviyesi seçildi");

    selectAndFill("select2-id_school_type-container", basvuru.okulTuru);
    MyLogger.progress(++mevcutIslem, toplamIslem, "Okul türü seçildi");

    selectAndFill("select2-id_school_branch-container", basvuru.okulTipi);
    MyLogger.progress(++mevcutIslem, toplamIslem, "Okul tipi seçildi");

    selectAndFill("select2-id_school-container", basvuru.okul);
    MyLogger.progress(++mevcutIslem, toplamIslem, "Okul seçildi");

    selectAndFill("select2-id_grade-container", basvuru.sinif);
    MyLogger.progress(++mevcutIslem, toplamIslem, "Sınıf seçildi");

    selectAndFill("select2-id_parent_affinity-container", basvuru.veliYakinlik);
    MyLogger.progress(++mevcutIslem, toplamIslem, "Veli yakınlık seçildi");


    // --- ONAY KUTULARI ---
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].click();", driver.findElement(By.id("id_toc")));
    js.executeScript("arguments[0].click();", driver.findElement(By.id("id_icf")));
    js.executeScript("arguments[0].click();", driver.findElement(By.id("id_commercial_message_consent")));
    MyLogger.progress(++mevcutIslem, toplamIslem, "Onay kutuları işaretlendi");

    long sure = (System.nanoTime() - baslangic) / 1_000_000_000;
    MyLogger.info(String.format("Form doldurma tamamlandı! (Süre: %d saniye)", sure));
}

public static void selectAndFill(String containerId, String secilecekDeger) {
    if (secilecekDeger == null || secilecekDeger.trim().isEmpty()) return;
    try {
        if (!containerId.equals("select2-id_school-container") && !containerId.equals("select2-id_grade-container")) {
            //Listenin dolu olmasını bekle
            wait.until(d -> {
                try {
                    d.findElement(By.id(containerId)).click();
                    List<WebElement> options = d.findElements(By.cssSelector(".select2-results__option"));
                    boolean dolu = options.size() > 1;
                    try {
                        d.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ESCAPE);
                    } catch (Exception ignored) {
                    }
                    return dolu;
                } catch (Exception e) {
                    return false;
                }
            });
        }

        //Kutuyu ekranda ortala ve aç
        WebElement container = driver.findElement(By.id(containerId));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", container);
        wait.until(ExpectedConditions.elementToBeClickable(container)).click();

        //Arama kutusuna yaz
        WebElement aramaKutusu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("select2-search__field")));
        aramaKutusu.clear();
        aramaKutusu.sendKeys(secilecekDeger);

        //Sonuçların yüklenmesini bekle (okul gibi büyük listeler için kritik)
        wait.until(d -> {
            try {
                List<WebElement> opts = d.findElements(
                        By.cssSelector(".select2-results__option:not(.select2-results__option--disabled)"));
                return !opts.isEmpty()
                        && !opts.getFirst().getText().contains("Aranıyor")
                        && !opts.getFirst().getText().contains("Yükleniyor");
            } catch (Exception e) {
                return false;
            }
        });

        //Highlighted seçeneği tıkla, yoksa eşleşeni bul
        boolean secildi = false;
        try {
            driver.findElement(By.cssSelector("li.select2-results__option--highlighted")).click();
            secildi = true;
        } catch (Exception e) {
            List<WebElement> sonuclar = driver.findElements(By.className("select2-results__option"));
            for (WebElement sonuc : sonuclar) {
                if (sonuc.getText().toUpperCase().contains(secilecekDeger.toUpperCase())) {
                    sonuc.click();
                    secildi = true;
                    break;
                }
            }
        }
        if (!secildi) aramaKutusu.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("select2-results__option")));
    } catch (Exception e) {
        System.out.println("UYARI: " + containerId + " menüsünde '" + secilecekDeger + "' seçilemedi!");
        try {
            driver.findElement(By.className("select2-search__field")).sendKeys(Keys.ESCAPE);
        } catch (Exception ignored) {
        }
    }
}

public static void writeToExcel() {
    MyLogger.islemBasla("Excel'e kaydediliyor");
    String dosyaYolu = "basvurular.csv";
    File dosya = new File(dosyaYolu);
    try (PrintWriter pw = new PrintWriter(
            new OutputStreamWriter(
                    new FileOutputStream(dosyaYolu, true),
                    StandardCharsets.UTF_8))) {
        if (dosya.length() == 0) {
            pw.println("Ad Soyad;TCKN;Telefon");
        }
        String tamAd = basvuru.ad + " " + basvuru.soyad;
        pw.println(tamAd + ";" + basvuru.tckn + ";" + basvuru.telefon);
    } catch (Exception e) {
        MyLogger.islemBasarisiz(e.getMessage());
    }
    MyLogger.islemTamam();
}

