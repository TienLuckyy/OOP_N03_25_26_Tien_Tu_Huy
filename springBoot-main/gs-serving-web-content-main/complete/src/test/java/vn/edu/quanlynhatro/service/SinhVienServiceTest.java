package vn.edu.quanlynhatro.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import vn.edu.quanlynhatro.controller.WriteToFile;
import vn.edu.quanlynhatro.model.Phong;
import vn.edu.quanlynhatro.model.SinhVien;
import vn.edu.quanlynhatro.repository.PhongRepository;
import vn.edu.quanlynhatro.repository.SinhVienRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SinhVienServiceTest {

    @Mock
    private SinhVienRepository sinhVienRepository;

    @Mock
    private PhongRepository phongRepository;

    @Mock
    private WriteToFile writeToFile;

    @InjectMocks
    private SinhVienService sinhVienService;

    private SinhVien sv;
    private Phong phong;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sv = new SinhVien();
        sv.setId(1L);
        sv.setHoTen("Nguyen Van A");
        sv.setMssv("SV001");
        sv.setCccd("123456789");
        sv.setSoDienThoai("0909123456");

        phong = new Phong();
        phong.setSoPhong("P101");
        phong.setToa("K01");
    }


    //  1. Test lấy toàn bộ sinh viên
    @Test
    void testGetAll() {
        when(sinhVienRepository.findAll()).thenReturn(List.of(sv));

        List<SinhVien> result = sinhVienService.getAll();

        assertEquals(1, result.size());
        assertEquals("Nguyen Van A", result.get(0).getHoTen());
        verify(sinhVienRepository, times(1)).findAll();
    }

    //  2. Test tìm sinh viên theo ID
    @Test
    void testFindById() {
        when(sinhVienRepository.findById(1L)).thenReturn(Optional.of(sv));

        Optional<SinhVien> result = sinhVienService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("SV001", result.get().getMssv());
        verify(sinhVienRepository, times(1)).findById(1L);
    }

    //  3. Test lưu sinh viên (và ghi file)
    @Test
    void testSave() {
        when(sinhVienRepository.save(sv)).thenReturn(sv);

        SinhVien result = sinhVienService.save(sv);

        assertNotNull(result);
        assertEquals("SV001", result.getMssv());
        verify(sinhVienRepository, times(1)).save(sv);
        verify(writeToFile, times(1)).exportSinhVienData();
    }

    //  4. Test tìm sinh viên theo MSSV
    @Test
    void testFindByMssv() {
        when(sinhVienRepository.findByMssv("SV001")).thenReturn(Optional.of(sv));

        Optional<SinhVien> result = sinhVienService.findByMssv("SV001");

        assertTrue(result.isPresent());
        assertEquals("Nguyen Van A", result.get().getHoTen());
    }

    //  5. Test tìm sinh viên theo CCCD
    @Test
    void testFindByCccd() {
        when(sinhVienRepository.findByCccd("123456789")).thenReturn(Optional.of(sv));

        Optional<SinhVien> result = sinhVienService.findByCccd("123456789");

        assertTrue(result.isPresent());
        assertEquals("SV001", result.get().getMssv());
    }

    //  6. Test tìm sinh viên theo SĐT
    @Test
    void testFindBySoDienThoai() {
        when(sinhVienRepository.findBySoDienThoai("0909123456")).thenReturn(Optional.of(sv));

        Optional<SinhVien> result = sinhVienService.findBySoDienThoai("0909123456");

        assertTrue(result.isPresent());
        assertEquals("SV001", result.get().getMssv());
    }

    //  7. Test tìm theo tên gần đúng
    @Test
    void testSearchByName() {
        when(sinhVienRepository.findByHoTenContainingIgnoreCase("nguyen"))
                .thenReturn(List.of(sv));

        List<SinhVien> result = sinhVienService.searchByName("nguyen");

        assertEquals(1, result.size());
        verify(sinhVienRepository, times(1)).findByHoTenContainingIgnoreCase("nguyen");
    }

    //  8. Test tìm sinh viên chưa có phòng
    @Test
    void testFindByPhongIsNull() {
        when(sinhVienRepository.findByPhongIsNull()).thenReturn(List.of(sv));

        List<SinhVien> result = sinhVienService.findByPhongIsNull();

        assertEquals(1, result.size());
        assertNull(result.get(0).getPhong());
    }

    //  9. Test xóa sinh viên có phòng
    @Test
    void testDelete_WithPhong() {
        sv.setPhong(phong);
        phong.getSinhViens().add(sv);

        when(sinhVienRepository.findById(1L)).thenReturn(Optional.of(sv));

        sinhVienService.delete(1L);

        verify(phongRepository, times(1)).save(phong);
        verify(sinhVienRepository, times(1)).delete(sv);
        verify(writeToFile, times(1)).exportSinhVienData();

        assertTrue(phong.getSinhViens().isEmpty());
    }

    //  10. Test xóa sinh viên không có phòng
    @Test
    void testDelete_NoPhong() {
        when(sinhVienRepository.findById(1L)).thenReturn(Optional.of(sv));

        sinhVienService.delete(1L);

        verify(phongRepository, never()).save(any());
        verify(sinhVienRepository, times(1)).delete(sv);
        verify(writeToFile, times(1)).exportSinhVienData();
    }

    //  11. Test xóa sinh viên không tồn tại
    @Test
    void testDelete_NotFound() {
        when(sinhVienRepository.findById(99L)).thenReturn(Optional.empty());

        sinhVienService.delete(99L);

        verify(sinhVienRepository, never()).delete(any());
        verify(writeToFile, never()).exportSinhVienData();
    }
}
