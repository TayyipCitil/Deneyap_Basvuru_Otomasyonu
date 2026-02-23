public class MyLogger {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private static final String ANSI_BOLD = "\u001B[1m";

    // Başlık gösterimi
    public static void baslik(String mesaj) {
        System.out.println("\n" + ANSI_BOLD + ANSI_CYAN + "╔═════════════════════════════════╗");
        System.out.println("║  " + padRight(mesaj, 30) + " ║");
        System.out.println("╚═════════════════════════════════╝" + ANSI_RESET + "\n");
    }

    // Bilgi mesajı
    public static void info(String mesaj) {
        System.out.println(ANSI_GREEN + "[✓] " + ANSI_RESET + mesaj);
    }

    // Uyarı mesajı
    public static void warn(String mesaj) {
        System.out.print(ANSI_YELLOW + "[!] " + ANSI_RESET + mesaj);
    }

    // Hata mesajı
    public static void error(String mesaj) {
        System.err.println(ANSI_RED + "[✗] " + ANSI_RESET + mesaj);
    }

    // İşlem başlangıcı
    public static void islemBasla(String islem) {
        System.out.print(ANSI_BLUE + "[→] " + ANSI_RESET + islem + "... ");
    }

    // İşlem tamamlandı
    public static void islemTamam() {
        System.out.println(ANSI_GREEN + "Tamamlandı" + ANSI_RESET);
    }

    // İşlem başarısız
    public static void islemBasarisiz(String hata) {
        System.out.println(ANSI_RED + "Başarısız" + ANSI_RESET);
        System.out.println(ANSI_RED + "    └─ " + hata + ANSI_RESET);
    }

    // Progress bar
    public static void progress(int current, int total, String mesaj) {
        int percent = (current * 100) / total;
        int bars = percent / 2; // 50 karakterlik bar için

        StringBuilder bar = new StringBuilder();
        bar.append(ANSI_CYAN + "[" + ANSI_RESET);
        bar.append(ANSI_GREEN).append("█".repeat(bars)).append(ANSI_RESET);
        bar.append(ANSI_WHITE).append("░".repeat(50 - bars)).append(ANSI_RESET);
        bar.append(ANSI_CYAN + "]" + ANSI_RESET);
        bar.append(String.format(" %d%%", percent));

        System.out.print("\r" + bar + " " + mesaj);
        if (current == total) System.out.println();
    }

    // Alt başlık
    public static void altBaslik(String mesaj) {
        System.out.println(ANSI_PURPLE + "─── " + mesaj + " ───" + ANSI_RESET);
    }

    // Yardımcı metod
    private static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

   // kapanış
    public static void closingText(){
        warn( ANSI_RESET + "Tarayıcıyı kapatmak için " +
                ANSI_YELLOW + ANSI_BOLD + "ENTER" + ANSI_RESET + " tuşuna basın");
    }

}