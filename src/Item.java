import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Item implements HitungTotalBayar {
    private String invoiceNumber;
    private String itemName;
    private double itemPrice;
    private int quantity;

    // Konstruktor
    public Item(String invoiceNumber, String itemName, double itemPrice, int quantity) {
        this.invoiceNumber = invoiceNumber;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

    // Implementasi metode dari interface HitungTotalBayar
    public double hitungTotalBayar() {
        return itemPrice * quantity;
    }

    public void simpanTransaksi() {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO barang (kodeBarang, namaBarang, hargaBarang, jumlahBeli, tanggal_transaksi, waktu_transaksi) " +
                             "VALUES (?, ?, ?, ?, ?, ?)")) {

            statement.setString(1, invoiceNumber);
            statement.setString(2, itemName);
            statement.setDouble(3, itemPrice);
            statement.setInt(4, quantity);
            statement.setDate(5, java.sql.Date.valueOf(getCurrentDate()));
            statement.setTime(6, java.sql.Time.valueOf(getCurrentTime()));

            statement.executeUpdate();
            System.out.println("Barang berhasil disimpan ke database.");

        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    public void bacaTransaksiDariDatabase() {
        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM barang")) {

            while (resultSet.next()) {
                String kodeBarang = resultSet.getString("kodeBarang");
                String namaBarang = resultSet.getString("namaBarang");
                double hargaBarang = resultSet.getDouble("hargaBarang");
                int jumlahBeli = resultSet.getInt("jumlahBeli");
                String tanggalTransaksi = resultSet.getString("tanggal_transaksi");
                String waktuTransaksi = resultSet.getString("waktu_transaksi");

                System.out.println("Kode Barang   : " + kodeBarang);
                System.out.println("Nama Barang   : " + namaBarang);
                System.out.println("Harga Barang  : " + hargaBarang);
                System.out.println("Jumlah Beli   : " + jumlahBeli);
                System.out.println("Tanggal Barang ditambahkan: " + tanggalTransaksi);
                System.out.println("Waktu Barang ditambahkan  : " + waktuTransaksi);
                System.out.println("------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    public void perbaruiTransaksiDiDatabase(String kodeBarang, double newHargaBarang) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE barang SET hargaBarang = ? WHERE kodeBarang = ?")) {

            statement.setDouble(1, newHargaBarang);
            statement.setString(2, kodeBarang);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Data transaksi berhasil diperbarui.");
            } else {
                System.out.println("Tidak ada data transaksi dengan ID " + kodeBarang);
            }

        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    public void hapusTransaksiDariDatabase(String kodeBarang) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM barang WHERE kodeBarang = ?")) {

            statement.setString(1, kodeBarang);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Data transaksi berhasil dihapus.");
            } else {
                System.out.println("Tidak ada data transaksi dengan ID " + kodeBarang);
            }

        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private String getCurrentTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return timeFormat.format(date);
    }

    @Override
    public String toString() {
        return "Nama Barang\t: " + itemName +
                "\nHarga Barang\t: " + itemPrice +
                "\nJumlah Beli\t: " + quantity;
    }
}
