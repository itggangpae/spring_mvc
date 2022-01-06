package kr.co.adamsoft;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardCotroller {
	@RequestMapping("/boardlist")
	public void boardlist() {
	}

}
