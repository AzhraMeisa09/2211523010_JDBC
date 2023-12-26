public class Invoice extends Item {
        private String invoiceNumber;
        private String customerName;
        private String numberPhone;
        private String customerAddress;
    
        //konstruktor
        public Invoice(String invoiceNumber,String customerName,String numberPhone,String customerAddress,String itemName,double itemPrice,int quantity ){
            super(invoiceNumber, itemName, itemPrice, quantity); //memanggil method item dari kelas item
            this.invoiceNumber = invoiceNumber;
            this.customerName = customerName;
            this.numberPhone = numberPhone;
            this.customerAddress = customerAddress;
        }   
    
        //memanggil method hitungTotalBayar dari kelas item
        @Override
        public double hitungTotalBayar(){
            return super.hitungTotalBayar();
        }

        // Representasi string dari objek Invoice
        @Override
        public String toString(){
            return 
            "Nama Pelanggan\t: " + customerName + "\n" + 
            "Nomor HP\t: " + numberPhone + "\n"+ 
            "Alamat\t\t: " + customerAddress + "\n"+
            "++++++++++++++++++++++++++++++++++++++++++++++"+"\n"+
            "\tDATA PEMBELIAN BARANG\t"+"\n"+
            "----------------------------------------------"+
            "\nKode Barang\t: "+ invoiceNumber +"\n"+
            super.toString();
        }
    }

