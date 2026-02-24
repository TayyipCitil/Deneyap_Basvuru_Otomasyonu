🤖 Deneyap / T3 Vakfı Başvuru Otomasyonu

  Bu proje, Deneyap Teknoloji Atölyeleri ve T3 Vakfı başvuru süreçlerini otonom hale getirmek için geliştirilmiş,Selenium WebDriver tabanlı bir masaüstü (RPA) uygulamasıdır.
  Karmaşık web formlarını, birbiriyle bağlantılı dinamik açılır menüleri ve onay kutularını insan müdahalesi olmadan doldurur.

🚀 Özellikler
  * Otonom Form Doldurma: JSON dosyasından okuduğu verileri saniyeler içinde ilgili alanlara işler.
  * Akıllı Yönetim: İnternet hızına ve sunucu yanıtlarına göre dinamik bekleme süreleri (Implicit/Explicit Waits) uygular.Ekranda görünmeyen veya tam eşleşmeyen verileri DOM üzerinden XPath ile tespit ederek esnek seçim yapar.
  * Yapay Zeka (Prompt) Entegrasyonu: Taranmış fiziki başvuru belgelerinin LLM'ler (Gemini, ChatGPT) aracılığıyla doğrudan sisteme uyumlu JSON formatına çevrilmesini sağlanabilir.
  * Otomatik Loglama (CSV): Sisteme girilen her adayın temel bilgilerini arka planda Excel uyumlu `.csv` formatında arşivler.
  * Taşınabilir Mimar (Fat JAR & EXE): Java veya IDE kurulumu gerektirmeyen, Launch4j ile paketlenmiş bağımsız `.exe` formatında çalışır.

📥 Kurulum ve Kullanım
Sistemi kaynak kodlarıyla derlemek yerine doğrudan kullanmak isterseniz:
1.  Linki verilen siteden ve `jdk-25_windows-x64_bin.exe` dosyasını indirin ve kurun. https://www.oracle.com/java/technologies/downloads/#jdk25-windows
2.  GitHub sayfasındaki **Releases** bölümünden `Deneyap_Basvuru_Otomasyonu_V2.zip` dosyasını indirin.
3.  Sağ tık yapıp ZIP dosyasını ayıklayın.
4.  `basvuru.json` dosyasını kendi bilgilerinize göre doldurun ve kaydedin. (aşağıdaki Yapay Zeka promt'unu kullanarak hızlı doldurma yapabilirsiniz).
5.  `Deneyap_Basvuru_Otomasyonu.exe` uygulamasına çift tıklayın ve arkanıza yaslanın!

🧠 Yapay Zeka ile Veri Çıkarma Komutu (Prompt)
Fiziki bir başvuru kağıdının fotoğrafını çekip, ChatGPT veya Gemini'ye aşağıdaki komutla birlikte 
vererek doğrudan otomasyonun okuyabileceği JSON çıktısını alabilirsiniz(Prompt'da kendinize göre güncellemeniz gereken yerler olabilir):

Lütfen ekteki başvuru formunun fotoğrafını dikkatlice incele. Çıkardığın bilgileri bana SADECE en alttaki JSON formatında ver. JSON dışında hiçbir açıklama, selamlama veya yorum yazma.
  DOLDURMA KURALLARI VE SEÇENEKLER:
  * telefon ve veliTelefon: Başında sıfır (0) olmadan 10 hane olarak yazılmalı (Örn: "5003004060").
  * cinsiyet:(cinsiyeti isimden bul) 
  * dogumTarihi: GG/AA/YYYY veya AA/GG/YYYY formatında olmalı (Örn: "11/02/2000").
  * egitimSeviyesi: Sadece şu seçeneklerden biri olmalı: "Lise", "Ortaokul", "İlkokul".
  * okulTuru: Sadece şu seçeneklerden biri olmalı: "Devlet Okulu", "Özel Okul".
  * okulTipi: Sadece şu seçeneklerden biri olmalı: "Anadolu Liseleri", "İlkokul", "Ortaokul", "Fen Liseleri", İmam Hatip Liseleri", "İmam Hatip Ortaokulu", "Meslek Liseleri", "Özel Liseler".
  * veliYakinlik: (veli yakınlık derecesinide veli isminden bul) Sadece şu seçeneklerden biri olmalı : "Anne", "Baba".
  * Fotoğrafta okunmayan veya boş bırakılan yerler olursa değer olarak boş string "" bırak.
  * Her zaman sabit kalacaklar (ulke: Türkiye, il:Sivas, ilçe:Merkez, parola:sivas123).

{
  "ad": "",
  "soyad": "",
  "eposta": "",
  "telefon": "",
  "tckn": "",
  "cinsiyet": "",
  "dogumTarihi": "",
  "egitimSeviyesi": "",
  "okulTuru": "",
  "okulTipi": "",
  "okul": "",
  "sinif": "",
  "veliAdi": "",
  "veliSoyadi": "",
  "veliEposta": "",
  "veliTelefon": "",
  "veliYakinlik": "",
  "ulke": "Türkiye",
  "il": "",
  "ilce": "",
  "adres": "",
  "parola": ""
}


👨‍💻 Geliştirici: Tayyip Çitil

Sivas Cumhuriyet Üniversitesi Bilgisayar Mühendisliği öğrencisi. 
Girişimci Mühendisler Kulübü kurucusu ve T3 Vakfı gönüllüsü olarak, 
süreçleri dijitalleştirip insanlara hız kazandıracak otomasyon sistemleri 
ve Java tabanlı çözümler üretmekteyim.
