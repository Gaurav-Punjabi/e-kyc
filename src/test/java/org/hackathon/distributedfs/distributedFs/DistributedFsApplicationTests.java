package org.hackathon.distributedfs.distributedFs;

import org.hackathon.distributedfs.distributedFs.model.FileFragment;
import org.hackathon.distributedfs.distributedFs.model.entity.Server;
import org.hackathon.distributedfs.distributedFs.service.FileDeliveryService;
import org.hackathon.distributedfs.distributedFs.service.FileService;
import org.hackathon.distributedfs.distributedFs.service.FileSplitter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class DistributedFsApplicationTests {

	@Autowired
	FileSplitter fileSplitter;
	@Autowired
	private FileService fileService;
	@Autowired
	FileDeliveryService fileDeliveryService;
	@Autowired
	WebApplicationContext wac;
	MockMvc mockMvc;
	@Before
	public void setup(){
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	@Test
	public void contextLoads() {
	}

	@Test
	public void shouldNotThrowException_FileDeliverySystem(){
		String url = "http://localhost:8080/receiveFragment";
		Server server = new Server();
		server.setUrl(url);
		ArrayList<Server> servers = new ArrayList<>();
		servers.add(server);
		server = new Server();
		server.setUrl(url);
		servers.add(server);

		ArrayList<FileFragment> fragments = new ArrayList<>();
		FileFragment fragment = new FileFragment();
		fragment.setContent("Rahul");
		fragments.add(fragment);

		fragment = new FileFragment();
		fragment.setContent("Mihir");
		fragments.add(fragment);

		fragment = new FileFragment();
		fragment.setContent("Gaurav");
		fragments.add(fragment);

		fragment = new FileFragment();
		fragment.setContent("Rohan");
		fragments.add(fragment);

		try{
			fileDeliveryService.deliverFiles(null,fragments,servers);
		}
		catch (Exception e){
			Assert.assertEquals(0,1);
		}
	}

	@Test
	public void shouldSplit() throws Exception{
		String content = "12345678910";
		List<FileFragment> fragments =fileSplitter.split(content,2);
		for(int i = 0;i<fragments.size();i++){
			System.out.println(fragments.get(i).getContent());
		}
	}

	@Test
	public void shouldReturnNotNullId_FileService(){
		fileService.save("Demo",(long)100);
	}

	@Test public void checkByte(){
	    byte[] arr = {65,66,67};
	    String str = new String(arr);
	    byte[] op = str.getBytes();
	    System.out.println("op.length = "+op.length);
	    for(int i = 0;i<op.length;i++){
	        System.out.println(op[i]);
        }
    }
}
