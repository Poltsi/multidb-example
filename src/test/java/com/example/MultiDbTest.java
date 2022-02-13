package com.example;

import com.example.admin.entity.AdminPage;
import com.example.admin.repository.AdminPageRepository;
import com.example.site.entity.SitePage;
import com.example.site.repository.SitePageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
class MultiDbTest {
	@Autowired
	private AdminPageRepository adminPageRepository;
	@Autowired
	private SitePageRepository  sitePageRepository;

	@BeforeEach
	void setUp() {
		var adminPage = AdminPage.builder()
								 .id(1L)
								 .body("<html><body>Test body</body></html>")
								 .build();
		adminPageRepository.save(adminPage);
		var pages = adminPageRepository.findAll();
		assertNotNull(pages);
		assertEquals(1, StreamSupport.stream(pages.spliterator(), false).count());
	}

	@Test
	void copyFromAdminToSite() {
		var fetchAdminPageOptional = adminPageRepository.findById(1L);
		assertFalse(fetchAdminPageOptional.isEmpty());
		var adminPage = fetchAdminPageOptional.get();
				assertNotNull(adminPage);
		var sitePage = SitePage.builder()
							   .id(adminPage.getId())
							   .body(adminPage.getBody())
							   .build();
		sitePageRepository.save(sitePage);
		var sitePages = sitePageRepository.findAll();
		assertEquals(1L, StreamSupport.stream(sitePages.spliterator(), false).count());
	}
}
