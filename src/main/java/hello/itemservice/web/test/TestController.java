package hello.itemservice.web.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final TestRepository testRepository;

    @GetMapping("/home")
    public String home(){
        return "test/home";
    }

    @GetMapping("/user/signup")
    public String createUser(Model model){
        model.addAttribute("testDto", new TestDto());
        return "test/signup";
    }

    @PostMapping("/user/signup")
    public String createUser(@Validated @ModelAttribute TestDto test,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "test/signup";
        }
        Test saveTest = new Test();
        saveTest.setAddress(test.getAddress());
        saveTest.setEmail(test.getEmail());
        saveTest.setPassword(test.getPassword());
        saveTest.setUsername(test.getUsername());
        saveTest.setPhoneNumber(test.getPhoneNumber());

        Test saveTest1 = testRepository.save(saveTest);
        redirectAttributes.addAttribute("testId", saveTest1.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/";
    }
}
