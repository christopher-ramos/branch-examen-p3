package com.banquito.core.branches;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.banquito.core.branches.exception.CRUDException;
import com.banquito.core.branches.model.Branch;
import com.banquito.core.branches.repository.BranchRepository;
import com.banquito.core.branches.service.BranchService;

@SpringBootTest
class BranchesApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	private BranchRepository branchRepository;
	
	private BranchService branchService;

    @BeforeEach
    public void setUp(){
        this.branchRepository = Mockito.mock(BranchRepository.class);
		this.branchService = new BranchService(branchRepository);
    }

	@Test
    public void createBranch() throws CRUDException{
        Branch branch = new Branch();
        branch.setId("B01");
        branch.setCode("B3432ER");
        branch.setName("Branch1");
	
        when(branchRepository.save(Mockito.any(Branch.class))).thenReturn(branch);
        // Act
        branchService.create(branch);
        // Assert
        verify(branchRepository).save(branch);
    }

	@Test
    public void testGetAllBranches() {
        // Arrange
        List<Branch> mockBranches = new ArrayList<>();
        mockBranches.add(new Branch());
        mockBranches.add(new Branch());

        // Mock the repository behavior
        when(branchRepository.findAll()).thenReturn(mockBranches);

        // Act
        List<Branch> result = branchService.getAll();

        // Assert
        assertEquals(2, result.size());
        verify(branchRepository).findAll();
    }



}
