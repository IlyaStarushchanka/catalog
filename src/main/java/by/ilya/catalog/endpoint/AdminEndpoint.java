package by.ilya.catalog.endpoint;

import by.ilya.catalog.domain.Manager;
import by.ilya.catalog.dto.AuthorDTO;
import by.ilya.catalog.dto.ContestDTO;
import by.ilya.catalog.dto.ManagerDTO;
import by.ilya.catalog.dto.SubGovernanceDTO;
import by.ilya.catalog.dto.SubmissionDTO;
import by.ilya.catalog.facade.AdminContestFacade;
import by.ilya.catalog.facade.AdminManagerPageFacade;
import by.ilya.catalog.facade.AdminPageFacade;
import by.ilya.catalog.facade.AdminSubGovernanceFacade;
import by.ilya.catalog.facade.AdminSubmissionFacade;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;


@Controller
@RequestMapping("admin")
public class AdminEndpoint {

    private AdminPageFacade adminPageFacade;
    private AdminManagerPageFacade adminManagerPageFacade;
    private AdminSubGovernanceFacade adminSubGovernanceFacade;
    private AdminContestFacade adminContestFacade;
    private AdminSubmissionFacade adminSubmissionFacade;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("managers", adminManagerPageFacade.getList());
        return "admin/manager/managers-list";
    }

    @GetMapping("/currentUserName")
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    @PostMapping("/managers/checkUserName")
    @ResponseBody
    public Boolean checkUserName(@RequestParam(value = "userName") String userName){
        return adminManagerPageFacade.isUserNameExist(userName);
    }

    @GetMapping("/managers/openAdd")
    public String openAddPage(Model model){
        return "admin/manager/manager-create";
    }

    @GetMapping("/managers/openEdit")
    public String openEditPage(@RequestParam(value = "id") long id, Model model, Principal principal){
        ManagerDTO manager = adminManagerPageFacade.getById(id);
        if (manager == null || manager.getNickName().equals(principal.getName())){
            model.addAttribute("errorMessage", "You cannot edit current user.");
            return "error";
        }
        model.addAttribute("manager", manager);

        return "admin/manager/manager-edit";
    }

    @GetMapping("/managers/list")
    public String getManagersList(Model model) {
        model.addAttribute("managers", adminManagerPageFacade.getList());
        return "admin/manager/managers-list";
    }

    @PostMapping("/managers/add")
    public String createManager(@ModelAttribute Manager manager, Model model){
        model.addAttribute("managers", adminManagerPageFacade.create(manager));
        return "admin/manager/managers-list";
    }

    @PostMapping("/managers/edit")
    public String editManager(@ModelAttribute ManagerDTO managerDTO, Model model){
        try {
            model.addAttribute("managers", adminManagerPageFacade.edit(managerDTO));
        } catch (NotFoundException e) {
            return "error";
        }
        return "admin/manager/managers-list";
    }

    @GetMapping("/managers/delete")
    public String deleteManager(@RequestParam(value = "id") long id, Model model, Principal principal){
        model.addAttribute("managers", adminManagerPageFacade.delete(id, principal.getName()));
        return "admin/manager/managers-list";
    }

    @GetMapping("/subGovernances/list")
    public String subGovernancesList(Model model){
        model.addAttribute("subGovernances", adminSubGovernanceFacade.getList());
        return "admin/subGovernance/subgov-list";
    }

    @GetMapping("/subGovernances/openAdd")
    public String openSubGovAddPage(Model model){
        return "admin/subGovernance/subgov-create";
    }

    @GetMapping("/subGovernances/openEdit")
    public String openSubGovEditPage(@RequestParam(value = "id") long id, Model model, Principal principal){
        SubGovernanceDTO subGovernance = adminSubGovernanceFacade.getById(id);
        if (subGovernance == null){
            model.addAttribute("errorMessage", "You cannot edit current user.");
            return "error";
        }
        model.addAttribute("subGov", subGovernance);

        return "admin/subGovernance/subgov-edit";
    }

    @PostMapping("/subGovernances/add")
    public String createSubGovernance(@ModelAttribute SubGovernanceDTO subGovernance, Model model){
        adminSubGovernanceFacade.create(subGovernance);
        model.addAttribute("subGovernances", adminSubGovernanceFacade.getList());
        return "admin/subGovernance/subgov-list";
    }

    @GetMapping("/subGovernances/delete")
    public String deleteSubGovernance(@RequestParam(value = "id") long id, Model model){
        model.addAttribute("subGovernances", adminSubGovernanceFacade.delete(id));
        return "admin/subGovernance/subgov-list";
    }

    @PostMapping("/subGovernances/edit")
    public String editSubGovernance(@ModelAttribute SubGovernanceDTO subGovernance, Model model) throws NotFoundException {
        model.addAttribute("subGovernances", adminSubGovernanceFacade.edit(subGovernance));
        return "admin/subGovernance/subgov-list";
    }

    @GetMapping("/subGovernances/view")
    public String viewSubGovernance(@RequestParam(value = "id") long id,Model model){
        model.addAttribute("subGov", adminSubGovernanceFacade.getById(id));
        return "admin/subGovernance/subgov-view";
    }

    @GetMapping("/contests/list")
    public String contestsList(Model model){
        model.addAttribute("contests", adminContestFacade.getList());
        return "admin/contest/contest-list";
    }

    @GetMapping("/contests/openAdd")
    public String openContestAddPage(Model model){
        model.addAttribute("subGovernances", adminSubGovernanceFacade.getList());
        return "admin/contest/contest-create";
    }

    @GetMapping("/contests/openEdit")
    public String openContestEditPage(@RequestParam(value = "id") long id, Model model, Principal principal){
        ContestDTO contest = adminContestFacade.getById(id);
        if (contest == null){
            model.addAttribute("errorMessage", "You cannot edit current user.");
            return "error";
        }
        model.addAttribute("contest", contest);
        model.addAttribute("subGovernances", adminSubGovernanceFacade.getList());
        return "admin/contest/contest-edit";
    }

    @PostMapping("/contests/add")
    public String createContest(@ModelAttribute ContestDTO contest, Model model){
        adminContestFacade.create(contest);
        model.addAttribute("contests", adminContestFacade.getList());
        return "admin/contest/contest-list";
    }

    @GetMapping("/contests/delete")
    public String deleteContest(@RequestParam(value = "id") long id, Model model){
        model.addAttribute("contests", adminContestFacade.delete(id));
        return "admin/contest/contest-list";
    }

    @PostMapping("/contests/edit")
    public String editContest(@ModelAttribute ContestDTO contest, Model model) throws NotFoundException {
        model.addAttribute("contests", adminContestFacade.edit(contest));
        return "admin/contest/contest-list";
    }

    @GetMapping("/contests/view")
    public String viewContest(@RequestParam(value = "id") long id,Model model){
        model.addAttribute("contest", adminContestFacade.getById(id));
        return "admin/contest/contest-view";
    }

    @PostMapping("/submissions/add")
    public String createSubmission(@ModelAttribute SubmissionDTO submissionDTO, Model model){
        adminSubmissionFacade.create(submissionDTO);
        model.addAttribute("submissions", adminSubmissionFacade.getList());
        return "admin/submission/submission-list";
    }

    @GetMapping("/submissions/delete")
    public String deleteSubmission(@RequestParam(value = "id") long id, Model model){
        model.addAttribute("submissions", adminSubmissionFacade.delete(id));
        return "admin/submission/submission-list";
    }

    @PostMapping("/submissions/edit")
    public String editSubmission(@ModelAttribute ContestDTO contest, Model model) throws NotFoundException {
        model.addAttribute("contests", adminSubmissionFacade.edit(contest));
        return "admin/submission/submission-list";
    }

    @GetMapping("/submissions/upload")
    public String viewSubmission(@RequestParam(value = "id") long id, Model model){
        model.addAttribute("submission", adminSubmissionFacade.getById(id));
        return "admin/submission/submission-view";
    }

    @GetMapping("/submissions/view")
    public String viewSubmission(@RequestParam(value = "id") long id, Model model){
        model.addAttribute("submission", adminSubmissionFacade.getById(id));
        return "admin/submission/submission-view";
    }

    @PostMapping("/authors")
    @ResponseBody
    public AuthorDTO createAuthor(@RequestBody AuthorDTO author){
        return adminPageFacade.createAuthor(author);
    }

    /*@DeleteMapping("/managers/{id}")
    @ResponseBody
    public List<ManagerDTO> deleteManager(@PathVariable long id){
        return adminPageFacade.deleteManager(id);
    }
*/

    @Autowired
    public void setAdminPageFacade(AdminPageFacade adminPageFacade) {
        this.adminPageFacade = adminPageFacade;
    }

    @Autowired
    public void setAdminManagerPageFacade(AdminManagerPageFacade adminManagerPageFacade) {
        this.adminManagerPageFacade = adminManagerPageFacade;
    }

    @Autowired
    public void setAdminSubGovernanceFacade(AdminSubGovernanceFacade adminSubGovernanceFacade) {
        this.adminSubGovernanceFacade = adminSubGovernanceFacade;
    }

    @Autowired
    public void setAdminContestFacade(AdminContestFacade adminContestFacade) {
        this.adminContestFacade = adminContestFacade;
    }

    @Autowired
    public void setAdminSubmissionFacade(AdminSubmissionFacade adminSubmissionFacade) {
        this.adminSubmissionFacade = adminSubmissionFacade;
    }
}
