package vn.edu.quanlynhatro.model;

import java.io.Serializable;
import java.util.Objects;

// ✅ Khóa chính phức hợp (soPhong + toa)
public class PhongId implements Serializable {
    private String soPhong;
    private String toa;

    public PhongId() {}

    public PhongId(String soPhong, String toa) {
        this.soPhong = soPhong;
        this.toa = toa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhongId)) return false;
        PhongId that = (PhongId) o;
        return Objects.equals(soPhong, that.soPhong) &&
               Objects.equals(toa, that.toa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(soPhong, toa);
    }
}
