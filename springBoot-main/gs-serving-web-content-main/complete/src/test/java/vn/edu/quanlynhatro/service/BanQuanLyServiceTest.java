package vn.edu.quanlynhatro.service;

import vn.edu.quanlynhatro.model.BanQuanLy;
import vn.edu.quanlynhatro.repository.BanQuanLyRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BanQuanLyServiceTest {

    @Mock
    private BanQuanLyRepository repository;

    @InjectMocks
    private BanQuanLyService service; // service thật, nhưng repository mock

    private BanQuanLy bql;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bql = new BanQuanLy();
        bql.setId(1L);
        bql.setMaNhanVien("NV001");
        bql.setHoTen("Nguyen Van A");
        bql.setToaPhuTrach("A1");
        bql.setSoDienThoai("0901234567");
        bql.setCccd("012345678901");
    }

    @Test
    void testFindByMaNhanVien() {
        when(repository.findByMaNhanVien("NV001")).thenReturn(Optional.of(bql));

        Optional<BanQuanLy> result = repository.findByMaNhanVien("NV001");

        assertTrue(result.isPresent());
        assertEquals("Nguyen Van A", result.get().getHoTen());
    }

    @Test
    void testFindByHoTenContainingIgnoreCase() {
        when(repository.findByHoTenContainingIgnoreCase("nguyen"))
                .thenReturn(List.of(bql));

        List<BanQuanLy> list = repository.findByHoTenContainingIgnoreCase("nguyen");

        assertEquals(1, list.size());
        assertEquals("NV001", list.get(0).getMaNhanVien());
    }

    @Test
    void testFindByToaPhuTrachOrAll() {
        when(repository.findByToaPhuTrachOrAll("A1")).thenReturn(List.of(bql));

        List<BanQuanLy> list = repository.findByToaPhuTrachOrAll("A1");

        assertFalse(list.isEmpty());
        assertEquals("A1", list.get(0).getToaPhuTrach());
    }

    @Test
    void testExistsByMaNhanVienAndIdNot() {
        when(repository.existsByMaNhanVienAndIdNot("NV001", 2L)).thenReturn(true);

        boolean exists = repository.existsByMaNhanVienAndIdNot("NV001", 2L);

        assertTrue(exists);
    }

    @Test
    void testExistsByCccdAndIdNot() {
        when(repository.existsByCccdAndIdNot("012345678901", 2L)).thenReturn(false);

        boolean exists = repository.existsByCccdAndIdNot("012345678901", 2L);

        assertFalse(exists);
    }

    @Test
    void testFindBySoDienThoai() {
        when(repository.findBySoDienThoai("0901234567")).thenReturn(Optional.of(bql));

        Optional<BanQuanLy> result = repository.findBySoDienThoai("0901234567");

        assertTrue(result.isPresent());
        assertEquals("NV001", result.get().getMaNhanVien());
    }

    @Test
    void testExistsBySoDienThoaiAndIdNot() {
        when(repository.existsBySoDienThoaiAndIdNot("0901234567", 1L)).thenReturn(true);

        boolean exists = repository.existsBySoDienThoaiAndIdNot("0901234567", 1L);

        assertTrue(exists);
    }

    @Test
    void testFindByCccd() {
        when(repository.findByCccd("012345678901")).thenReturn(Optional.of(bql));

        Optional<BanQuanLy> result = repository.findByCccd("012345678901");

        assertTrue(result.isPresent());
        assertEquals("Nguyen Van A", result.get().getHoTen());
    }

    @Test
    void testCountByToaPhuTrach() {
        when(repository.countByToaPhuTrach())
                .thenReturn(List.of(new Object[]{"A1", 3L}, new Object[]{"B1", 2L}));

        List<Object[]> result = repository.countByToaPhuTrach();

        assertEquals(2, result.size());
        assertEquals("A1", result.get(0)[0]);
        assertEquals(3L, result.get(0)[1]);
    }
}
