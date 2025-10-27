package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.model.Phong;
import vn.edu.quanlynhatro.model.PhongId;
import vn.edu.quanlynhatro.model.SinhVien;
import vn.edu.quanlynhatro.repository.PhongRepository;
import vn.edu.quanlynhatro.repository.SinhVienRepository;
import vn.edu.quanlynhatro.controller.WriteToFile;
import vn.edu.quanlynhatro.exception.ResourceNotFoundException;
import vn.edu.quanlynhatro.exception.ResourceInUseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PhongServiceTest {

    @Mock
    private PhongRepository phongRepository;

    @Mock
    private SinhVienRepository sinhVienRepository;

    @Mock
    private WriteToFile writeToFile;

    @InjectMocks
    private PhongService phongService;

    private Phong phong1;
    private Phong phong2;
    private PhongId phongId1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        phong1 = new Phong("P101", "A", 1200000.0, 4, true);
        phong2 = new Phong("P102", "A", 1500000.0, 3, false);
        phongId1 = new PhongId("P101", "A");
    }

    // =============================
    // 🔹 TEST GET ALL PHONG
    // =============================
    @Test
    void testGetAllPhong() {
        when(phongRepository.findAll()).thenReturn(Arrays.asList(phong1, phong2));

        List<Phong> result = phongService.getAllPhong();

        assertEquals(2, result.size());
        verify(phongRepository, times(1)).findAll();
    }

    // =============================
    // 🔹 TEST GET PHONG BY ID
    // =============================
    @Test
    void testGetPhongById() {
        when(phongRepository.findBySoPhongAndToa("P101", "A")).thenReturn(Optional.of(phong1));

        Optional<Phong> result = phongService.getPhongById("P101", "A");

        assertTrue(result.isPresent());
        assertEquals("P101", result.get().getSoPhong());
        verify(phongRepository, times(1)).findBySoPhongAndToa("P101", "A");
    }

    // =============================
    // 🔹 TEST CREATE PHONG (thành công)
    // =============================
    @Test
    void testCreatePhong_Success() {
        when(phongRepository.existsBySoPhongAndToa("P101", "A")).thenReturn(false);
        when(phongRepository.save(any(Phong.class))).thenReturn(phong1);

        boolean result = phongService.createPhong(phong1);

        assertTrue(result);
        verify(phongRepository, times(1)).save(any(Phong.class));
        verify(writeToFile, times(1)).exportPhongData();
    }

    // =============================
    // 🔹 TEST CREATE PHONG (đã tồn tại)
    // =============================
    @Test
    void testCreatePhong_AlreadyExists() {
        when(phongRepository.existsBySoPhongAndToa("P101", "A")).thenReturn(true);

        boolean result = phongService.createPhong(phong1);

        assertFalse(result);
        verify(phongRepository, never()).save(any());
    }

    // =============================
    // 🔹 TEST UPDATE PHONG (thành công)
    // =============================
    @Test
    void testUpdatePhong_Success() {
        when(phongRepository.findById(phongId1)).thenReturn(Optional.of(phong1));
        when(phongRepository.save(any(Phong.class))).thenReturn(phong1);

        boolean result = phongService.updatePhong(phong1);

        assertTrue(result);
        verify(phongRepository, times(1)).save(any(Phong.class));
        verify(writeToFile, times(1)).exportPhongData();
    }

    // =============================
    // 🔹 TEST UPDATE PHONG (không tồn tại)
    // =============================
    @Test
    void testUpdatePhong_NotFound() {
        when(phongRepository.findById(phongId1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> phongService.updatePhong(phong1));
    }

    // =============================
    // 🔹 TEST DELETE PHONG (thành công)
    // =============================
    @Test
    void testDeletePhong_Success() {
        phong1.setSinhViens(new HashSet<>());

        when(phongRepository.findById(phongId1)).thenReturn(Optional.of(phong1));

        phongService.deletePhong("P101", "A");

        verify(phongRepository, times(1)).delete(phong1);
        verify(writeToFile, times(1)).exportPhongData();
    }

    // =============================
    // 🔹 TEST DELETE PHONG (có sinh viên)
    // =============================
    @Test
    void testDeletePhong_InUse() {
        SinhVien sv = new SinhVien();
        Set<SinhVien> svs = new HashSet<>();
        svs.add(sv);
        phong1.setSinhViens(svs);

        when(phongRepository.findById(phongId1)).thenReturn(Optional.of(phong1));

        assertThrows(ResourceInUseException.class, () -> phongService.deletePhong("P101", "A"));
    }

    // =============================
    // 🔹 TEST ASSIGN STUDENT (thành công)
    // =============================
    @Test
    void testAssignStudent_Success() {
        SinhVien sv = new SinhVien();
        sv.setId(1L);

        when(phongRepository.findBySoPhongAndToa("P101", "A")).thenReturn(Optional.of(phong1));
        when(sinhVienRepository.findById(1L)).thenReturn(Optional.of(sv));
        when(sinhVienRepository.save(any())).thenReturn(sv);
        when(phongRepository.save(any())).thenReturn(phong1);

        boolean result = phongService.assignStudent(1L, "P101", "A");

        assertTrue(result);
        verify(writeToFile, times(1)).exportPhongData();
    }

    // =============================
    // 🔹 TEST ASSIGN STUDENT (phòng đầy)
    // =============================
    @Test
    void testAssignStudent_FullRoom() {
        SinhVien sv = new SinhVien();
        sv.setId(1L);

        Set<SinhVien> sinhViens = new HashSet<>();
        sinhViens.add(new SinhVien());
        sinhViens.add(new SinhVien());
        sinhViens.add(new SinhVien());
        sinhViens.add(new SinhVien()); // đầy đủ 4 người
        phong1.setSinhViens(sinhViens);

        when(phongRepository.findBySoPhongAndToa("P101", "A")).thenReturn(Optional.of(phong1));
        when(sinhVienRepository.findById(1L)).thenReturn(Optional.of(sv));

        assertThrows(ResourceInUseException.class, () -> phongService.assignStudent(1L, "P101", "A"));
    }

    // =============================
    // 🔹 TEST REMOVE STUDENT (thành công)
    // =============================
    @Test
    void testRemoveStudent_Success() {
        SinhVien sv = new SinhVien();
        sv.setId(1L);
        sv.setPhong(phong1);

        when(sinhVienRepository.findById(1L)).thenReturn(Optional.of(sv));
        when(phongRepository.findBySoPhongAndToa("P101", "A")).thenReturn(Optional.of(phong1));

        boolean result = phongService.removeStudent(1L, "P101", "A");

        assertTrue(result);
        verify(sinhVienRepository, times(1)).save(sv);
        verify(writeToFile, times(1)).exportPhongData();
    }

    // =============================
    // 🔹 TEST REMOVE STUDENT (sai phòng)
    // =============================
    @Test
    void testRemoveStudent_WrongRoom() {
        SinhVien sv = new SinhVien();
        sv.setId(1L);
        sv.setPhong(new Phong("P999", "B", 800000.0, 2, true));

        when(sinhVienRepository.findById(1L)).thenReturn(Optional.of(sv));
        when(phongRepository.findBySoPhongAndToa("P101", "A")).thenReturn(Optional.of(phong1));

        assertThrows(ResourceInUseException.class, () -> phongService.removeStudent(1L, "P101", "A"));
    }
}
