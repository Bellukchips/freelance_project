package id.nesd.umkmdesasambongrejo.model;

import java.util.ArrayList;

public class MenuModel {
    private int id;
    private String foto;
    private String nama;
    private String deskripsi;
    private int harga;

    public MenuModel(int id, String foto, String nama, String deskripsi, int harga) {
        this.id = id;
        this.foto = foto;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public static ArrayList<MenuModel> data() {
        ArrayList<MenuModel> d = new ArrayList<>();
        d.add(new MenuModel(0,
                "https://images.tokopedia.net/img/cache/900/VqbcmM/2021/10/8/dc27774f-e10a-4fcb-81ef-8d8ff18251e8.jpg",
                "Keripik Pisang UMKM Jajan Awet",
                "Keripik Pisang dengan rasa gurih dan manis ala UMKM Jajan Awet",
                18000
        ));
        d.add(new MenuModel(1,
                "https://images.tokopedia.net/img/cache/900/VqbcmM/2021/11/2/48f99f88-ba76-4b5d-ad19-07ae71aa39a8.jpg",
                "Keripik Pisang Coklat UMKM Damar Putra",
                "Rasakan Nikmatnya Keripik Pisang Coklat yang manis ala UMKM Damar Putra",
                9000
        ));
        d.add(new MenuModel(2,
                "https://images.tokopedia.net/img/cache/900/VqbcmM/2021/3/23/18a0830f-681b-477e-8efd-3e7b6b3d601c.jpg",
                "Keripik Singkong Rokky Rasa Gadung",
                "Keripik Singkong dengan Rasa Gadung ala UMKM Rokky",
                14000
        ));
        d.add(new MenuModel(3,
                "https://images.tokopedia.net/img/cache/900/VqbcmM/2020/12/28/b6e9584e-4752-425d-9178-7390681fc443.jpg",
                "Keripik Pisang UMKM Rantau Prapat",
                "Keripik Pisang yang gurih dan garing ala UMKM Rantau Prapat",
                26000
        ));
        d.add(new MenuModel(4,
                "https://images.tokopedia.net/img/cache/900/VqbcmM/2021/2/27/a3a3c576-9b8d-4dd5-baa0-fe339402995f.jpg",
                "Keripik Ikan OISHAK UMKM Nelayan Karawang",
                "Keripik Ikan dengan merek OISHAK hasil olahan dari Nelayan Karawang",
                17000
        ));
        return d;
    }
}
