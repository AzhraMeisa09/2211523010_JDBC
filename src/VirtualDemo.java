import java.text.SimpleDateFormat;
import java.util.*;

public class VirtualDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Item barang = null;
        boolean inputValid = false;

        Date hariSekarang = new Date();
        SimpleDateFormat tanggal = new SimpleDateFormat("E, dd/MM/yyyy");
        SimpleDateFormat waktu = new SimpleDateFormat("hh:mm:ss a zzz");
        String supermarket = "AB MART";

        try {
            int menu;
            do {
                System.out.println("Menu:");
                System.out.println("1. Input Barang");
                System.out.println("2. Tampilkan Barang");
                System.out.println("3. Perbarui Barang");
                System.out.println("4. Hapus Barang");
                System.out.println("5. Transaksi");
                System.out.println("0. Keluar");
                System.out.print("Pilih menu: ");
                menu = scanner.nextInt();

                switch (menu) {
                    case 1:
                        System.out.println("\nInput Data Barang");
                        System.out.println("------------------------");
                        scanner.nextLine(); // Membersihkan buffer input sebelum membaca string
                        System.out.print("Kode Barang   : ");
                        String kodeBarang = scanner.nextLine();
                        System.out.print("Nama Barang   : ");
                        String namaBarang = scanner.nextLine();
                        System.out.print("Harga Barang  : ");
                        double hargaBarang = scanner.nextDouble();
                        System.out.print("Jumlah Barang   : ");
                        int jumlahBeli = scanner.nextInt();

                        barang = new Item(kodeBarang, namaBarang, hargaBarang, jumlahBeli);
                        barang.simpanTransaksi();
                        break;
                    case 2:
                        if (barang != null) {
                            System.out.println("Data Barang:");
                            barang.bacaTransaksiDariDatabase();
                        } else {
                            System.out.println("Belum ada barang yang dimasukkan.");
                        }
                        break;
                    case 3:
                        if (barang != null) {
                            scanner.nextLine(); // Membersihkan buffer input sebelum membaca string
                            System.out.print("Masukkan Kode Barang yang akan diperbarui: ");
                            String idUpdate = scanner.nextLine();
                            System.out.print("Masukkan Harga Barang Baru: ");
                            double newHarga = scanner.nextDouble();
                            barang.perbaruiTransaksiDiDatabase(idUpdate, newHarga);
                        } else {
                            System.out.println("Belum ada barang yang dimasukkan.");
                        }
                        break;
                    case 4:
                        if (barang != null) {
                            scanner.nextLine(); // Membersihkan buffer input sebelum membaca string
                            System.out.print("Masukkan Kode Barang yang akan dihapus: ");
                            String idHapus = scanner.nextLine();
                            barang.hapusTransaksiDariDatabase(idHapus);
                        } else {
                            System.out.println("Belum ada transaksi yang dimasukkan.");
                        }
                        break;
                    case 5:
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
                                kodeBarang = scanner.next();

                                System.out.print("Masukkan Nama Barang\t\t: ");
                                namaBarang = scanner.next();

                                System.out.print("Masukkan Harga Barang\t\t: ");
                                hargaBarang = scanner.nextDouble();

                                System.out.print("Masukkan Jumlah Beli\t\t: ");
                                jumlahBeli = scanner.nextInt();

                                Invoice faktur = new Invoice(kodeBarang, namaPelanggan, nomorHpPelanggan, alamatPelanggan, namaBarang, hargaBarang, jumlahBeli);

                                // Menampilkan detail Invoice dan total bayar
                                System.out.println("==============================================");
                                System.out.println("\t" + supermarket.toUpperCase());
                                System.out.println("----------------------------------------------");
                                System.out.println("Tanggal\t\t: " + tanggal.format(hariSekarang));
                                System.out.println("Waktu\t\t: " + waktu.format(hariSekarang));
                                System.out.println("==============================================");
                                System.out.println("\t\tDATA PELANGGAN\t");
                                System.out.println("----------------------------------------------");
                                System.out.println(faktur);
                                System.out.println("TOTAL BAYAR\t: " + faktur.hitungTotalBayar());
                                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
                                System.out.println("Kasir\t\t: MEISA");

                                inputValid = true;
                            } catch (Exception e) { //exception
                                System.out.println("Terjadi kesalahan input \n");
                                System.out.println("========== Masukan Ulang ==========\n");
                                scanner.nextLine(); // Membersihkan buffer input
                            }
                        }
                        while (!inputValid);

                        scanner.close();

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
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
