import java.text.SimpleDateFormat;
import java.util.*;

public class VirtualDemo {

    private static final String SUPERMARKET = "AB MART";
    private static final SimpleDateFormat TANGGAL_FORMAT = new SimpleDateFormat("E, dd/MM/yyyy");
    private static final SimpleDateFormat WAKTU_FORMAT = new SimpleDateFormat("hh:mm:ss a zzz");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Item barang = null;

        try {
            int menu;
            do {
                tampilkanMenu();
                menu = scanner.nextInt();

                switch (menu) {
                    case 1:
                        inputBarang(scanner);
                        break;
                    case 2:
                        tampilkanDataBarang(barang);
                        break;
                    case 3:
                        perbaruiDataBarang(scanner, barang);
                        break;
                    case 4:
                        hapusDataBarang(scanner, barang);
                        break;
                    case 5:
                        lakukanTransaksi(scanner);
                        break;
                    case 0:
                        System.out.println("Keluar dari program.");
                        break;
                    default:
                        System.out.println("Menu tidak valid.");
                        break;
                }

            } while (menu != 0);

        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void tampilkanMenu() {
        System.out.println("Menu:");
        System.out.println("1. Input Barang");
        System.out.println("2. Tampilkan Barang");
        System.out.println("3. Perbarui Barang");
        System.out.println("4. Hapus Barang");
        System.out.println("5. Transaksi");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    private static void inputBarang(Scanner scanner) {
        System.out.println("\nInput Data Barang");
        System.out.println("------------------------");
        System.out.print("Kode Barang   : ");
        String kodeBarang = scanner.nextLine();
        kodeBarang = scanner.nextLine();
        System.out.print("Nama Barang   : ");
        String namaBarang = scanner.nextLine();
        System.out.print("Harga Barang  : ");
        double hargaBarang = scanner.nextDouble();
        System.out.print("Jumlah Barang   : ");
        int jumlahBeli = scanner.nextInt();

        Item barang = new Item(kodeBarang, namaBarang, hargaBarang, jumlahBeli);
        barang.simpanTransaksi();
    }

    private static void tampilkanDataBarang(Item barang) {
        if (barang != null) {
            System.out.println("\nData Barang:");
            barang.bacaTransaksiDariDatabase();
        } else {
            System.out.println("\nBelum ada barang yang dimasukkan.");
        }
    }

    private static void perbaruiDataBarang(Scanner scanner, Item barang) {
        if (barang != null) {
            System.out.print("\nMasukkan Kode Barang yang akan diperbarui: ");
            String idUpdate = scanner.nextLine();
            idUpdate = scanner.nextLine();
            System.out.print("Masukkan Harga Barang Baru: ");
            double newHarga = scanner.nextDouble();
            barang.perbaruiTransaksiDiDatabase(idUpdate, newHarga);
        } else {
            System.out.println("\nBelum ada barang yang dimasukkan.");
        }
    }

    private static void hapusDataBarang(Scanner scanner, Item barang) {
        if (barang != null) {
            System.out.print("\nMasukkan Kode Barang yang akan dihapus: ");
            String idHapus = scanner.nextLine();
            idHapus = scanner.nextLine();
            barang.hapusTransaksiDariDatabase(idHapus);
        } else {
            System.out.println("\nBelum ada transaksi yang dimasukkan.");
        }
    }

    private static void lakukanTransaksi(Scanner scanner) {
        boolean inputValid = false;

        do {
            try {
                System.out.println("===================================");
                System.out.print("Masukkan nama pelanggan\t\t: ");
                String namaPelanggan = scanner.next();

                System.out.print("Masukkan nomor HP pelanggan\t: ");
                String nomorHpPelanggan = scanner.next();

                System.out.print("Masukkan alamat pelanggan\t: ");
                String alamatPelanggan = scanner.next();

                System.out.print("Masukkan Kode Barang\t\t: ");
                String kodeBarang = scanner.next();

                System.out.print("Masukkan Nama Barang\t\t: ");
                String namaBarang = scanner.next();

                System.out.print("Masukkan Harga Barang\t\t: ");
                double hargaBarang = scanner.nextDouble();

                System.out.print("Masukkan Jumlah Beli\t\t: ");
                int jumlahBeli = scanner.nextInt();

                Invoice faktur = new Invoice(kodeBarang, namaPelanggan, nomorHpPelanggan, alamatPelanggan, namaBarang, hargaBarang, jumlahBeli);

                // Menampilkan detail Invoice dan total bayar
                System.out.println("==============================================");
                System.out.println("\t" + SUPERMARKET.toUpperCase());
                System.out.println("----------------------------------------------");
                System.out.println("Tanggal\t\t: " + TANGGAL_FORMAT.format(new Date()));
                System.out.println("Waktu\t\t: " + WAKTU_FORMAT.format(new Date()));
                System.out.println("==============================================");
                System.out.println("\t\tDATA PELANGGAN\t");
                System.out.println("----------------------------------------------");
                System.out.println(faktur);
                System.out.println("TOTAL BAYAR\t: " + faktur.hitungTotalBayar());
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("Kasir\t\t: MEISA");

                inputValid = true;
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan input \n");
                System.out.println("========== Masukan Ulang ==========\n");
                scanner.nextLine(); // Membersihkan buffer input
            }
        } while (!inputValid);
    }
}
