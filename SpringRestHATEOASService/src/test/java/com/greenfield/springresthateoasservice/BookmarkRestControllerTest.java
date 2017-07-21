package com.greenfield.springresthateoasservice;

//Comment out this class so it won't include in JUnit testing for Maven build
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.greenfield.springresthateoasservice.domain.Account;
import com.greenfield.springresthateoasservice.domain.Bookmark;
import com.greenfield.springresthateoasservice.repository.AccountRepository;
import com.greenfield.springresthateoasservice.repository.BookmarkRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringRestHateoasServiceApplication.class)
@WebAppConfiguration
public class BookmarkRestControllerTest {

	// Define the content type as application/hal+json for hypermedia driven Rest services
	private MediaType contentType = new MediaType("application", "hal+json", Charset.forName("utf8"));

	private MockMvc mockMvc;

	private String userName = "bdussault";

	@Autowired
	private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

	private Account account;

	private List<Bookmark> bookmarkList = new ArrayList<Bookmark>();

	@Autowired
	private BookmarkRepository bookmarkRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private AccountRepository accountRepository;
	
	protected String jsonString(Object object) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();

		mappingJackson2HttpMessageConverter.write(object, contentType, mockHttpOutputMessage);

		return mockHttpOutputMessage.getBodyAsString();
	}

	// Initialize one account and two bookmarks with account
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		bookmarkRepository.deleteAll();
		accountRepository.deleteAll();

		account = accountRepository.save(new Account(userName, "password"));
		bookmarkList.add(bookmarkRepository
				.save(new Bookmark(account, "http://bookmark.com/1/" + userName, "First bookmark description")));
		bookmarkList.add(bookmarkRepository
				.save(new Bookmark(account, "http://bookmark.com/2/" + userName, "Second bookmark description")));
	}
	
	@Test
	public void userNotFound() throws Exception {
		// Pass in not existing userName and should get HTTP status of NOT_FOUND
		mockMvc.perform(post("/george/bookmarks").content(jsonString(new Bookmark())).contentType(contentType))
				.andExpect(status().isNotFound());
	}

	@Test
	public void getBookmark() throws Exception {
		mockMvc.perform(get("/" + userName + "/bookmarks/", bookmarkList.get(0).getId())).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.[0].bookmark.id", is(bookmarkList.get(0).getId().intValue())))
				.andExpect(jsonPath("$.[0].bookmark.uri", is("http://bookmark.com/1/" + userName)))
				.andExpect(jsonPath("$.[0].bookmark.description", is("First bookmark description")));
	}

	@Test
	public void listBookmarks() throws Exception {
		// The user should have two bookmarks.
		mockMvc.perform(get("/" + userName + "/bookmarks/")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(content().contentType("application/json;charset=UTF-8")) // Why is it json not hal+json???
				.andExpect(jsonPath("$.[0].bookmark.id", is(bookmarkList.get(0).getId().intValue())))
				.andExpect(jsonPath("$.[0].bookmark.uri", is("http://bookmark.com/1/" + userName)))
				.andExpect(jsonPath("$.[0].bookmark.description", is("First bookmark description")))
				.andExpect(jsonPath("$.[1].bookmark.id", is(bookmarkList.get(1).getId().intValue())))
				.andExpect(jsonPath("$.[1].bookmark.uri", is("http://bookmark.com/2/" + userName)))
				.andExpect(jsonPath("$.[1].bookmark.description", is("Second bookmark description")));
	}

	@Test
	public void addBookmark() throws Exception {
		String bookmarkJson = jsonString(new Bookmark(account, "http://spring.io", "best spring boot book"));

		mockMvc.perform(post("/" + userName + "/bookmarks").contentType(contentType).content(bookmarkJson))
				.andExpect(status().isCreated());
	}

	@Test
	public void deleteBookmark() throws Exception {
		mockMvc.perform(delete("/" + userName + "/bookmarks/" + bookmarkList.get(1).getId()))
				.andExpect(status().isOk());

		// Validate that user only has one bookmark after the deleting
		mockMvc.perform(get("/" + userName + "/bookmarks/")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)));
	}

}